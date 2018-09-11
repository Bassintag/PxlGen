package pxlgen.core.plugin;

import pxlgen.core.exception.InvalidFunctionHandler;
import pxlgen.core.function.Function;
import pxlgen.core.function.FunctionHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Plugin.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class Plugin {

    private List<FunctionHandler> functionHandlers;

    public Plugin() {
        functionHandlers = new ArrayList<>();
    }

    public Plugin(String path) throws IOException, ClassNotFoundException, InvalidFunctionHandler {
        JarFile jarFile = new JarFile(path);
        Enumeration<JarEntry> entries = jarFile.entries();
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(
                new URL[]{new URL("file:/" + path)},
                getClass().getClassLoader());
        functionHandlers = new ArrayList<>();
        String fileName = new File(path).getName();
        int pos = fileName.lastIndexOf('.');
        fileName = fileName.substring(0, pos);
        System.out.println("FILE NAME: " + fileName);
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.isDirectory() || !entry.getName().endsWith(".class"))
                continue;
            String className = entry.getName().substring(0, entry.getName().length() - 6);
            className = className.replace('/', '.');
            System.out.println("Loading class: '" + entry.getName() + "'");
            Class c = urlClassLoader.loadClass(className);
            if (c.getAnnotation(pxlgen.core.annotation.FunctionHandler.class) != null) {
                FunctionHandler functionHandler = new FunctionHandler(c, fileName);
                functionHandlers.add(functionHandler);
            }
        }
    }

    public void addFunctionHandler(FunctionHandler handler) {
        functionHandlers.add(handler);
    }

    public Iterable<FunctionHandler> getFunctionHandlers() {
        return functionHandlers;
    }

    public Iterable<Function> getFunctions() {
        List<Function> ret = new ArrayList<>();
        for (FunctionHandler h : functionHandlers) {
            for (Function f : h.getFunctions()) {
                ret.add(f);
            }
        }
        return ret;
    }
}
