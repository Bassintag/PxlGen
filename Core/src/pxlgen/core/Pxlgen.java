package pxlgen.core;

import pxlgen.core.command.Command;
import pxlgen.core.command.PartialCommand;
import pxlgen.core.command.parser.CommandParser;
import pxlgen.core.command.parser.PartialCommandParser;
import pxlgen.core.exception.*;
import pxlgen.core.function.Function;
import pxlgen.core.function.FunctionCall;
import pxlgen.core.image.ImageBuffer;
import pxlgen.core.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pxlgen.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class Pxlgen {

    private final ImageBuffer  image;
    private final List<Plugin> plugins;

    public Pxlgen() {
        this(64, 64);
    }

    public Pxlgen(int size) {
        this(size, size);
    }

    public Pxlgen(int width, int height) {
        image = new ImageBuffer(width, height);
        plugins = new ArrayList<>();
    }

    public ImageBuffer getImage() {
        return image;
    }

    public void loadPluginFolder(String path) throws InvalidPluginException {
        File directory = new File(path);
        File[] files = directory.listFiles();
        System.out.println(directory.getPath());
        if (files == null)
            return;
        for (File f : files) {
            if (f.getName().endsWith(".jar"))
                loadPlugin(f.getAbsolutePath());
        }
    }

    public void loadPlugin(String path) throws InvalidPluginException {
        try {
            System.out.println("Loading plugin: " + path);
            plugins.add(new Plugin(path));
        } catch (InvalidFunctionHandler | IOException | ClassNotFoundException e) {
            throw new InvalidPluginException(e);
        }
    }

    private List<Function> getFunction(String name) {
        List<Function> ret = new ArrayList<>();
        for (Plugin p : plugins) {
            for (Function f : p.getFunctions()) {
                if (f.getName().equals(name))
                    ret.add(f);
            }
        }
        return ret;
    }

    public List<Function> getFunctions() {
        List<Function> ret = new ArrayList<>();
        for (Plugin p : plugins) {
            for (Function f : p.getFunctions()) {
                ret.add(f);
            }
        }
        return ret;
    }

    public Function[] getMatchingFunction(String text) {
        PartialCommand partialCommand;
        try {
            partialCommand = new PartialCommandParser(text).parse();
        } catch (CommandParsingException e) {
            return new Function[0];
        }
        return getFunctions().stream().filter(partialCommand::match).toArray(Function[]::new);
    }

    public void run(String cmd) throws InvalidCommandException {
        CommandParser parser = new CommandParser(cmd);
        Command command;
        try {
            command = parser.parse();
            System.out.println("Command = " + command.toString());
        } catch (CommandParsingException e) {
            throw new InvalidCommandException(e);
        }
        List<Function> functions = getFunction(command.getName());
        if (functions.size() == 0)
            throw new UnknownCommandException(command.getName());
        FunctionCall functionCall = command.toFunctionCall();
        for (Function f : functions) {
            if (command.getDomain() != null && !f.getDomain().equals(command.getDomain()))
                continue;
            if (f.isCalleableBy(functionCall)) {
                try {
                    f.run(image, functionCall.getParams());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        throw new NoMatchingDefinitionException(command);
    }
}
