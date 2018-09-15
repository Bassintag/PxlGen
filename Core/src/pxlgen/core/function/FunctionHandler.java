package pxlgen.core.function;

import pxlgen.core.exception.InvalidFunctionHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * FunctionHandler.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class FunctionHandler {

    private List<Function> functions;

    public FunctionHandler(Class<?> c, String domain) throws InvalidFunctionHandler {
        Object instance;
        try {
            instance = c.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new InvalidFunctionHandler(e.getMessage(), e.getCause());
        }
        functions = new ArrayList<>();
        for (Method m : c.getMethods()) {
            pxlgen.core.annotation.Function annotation = m.getAnnotation(pxlgen.core.annotation.Function.class);
            if (annotation != null) {
                String name = annotation.name();
                if (name.length() == 0)
                    name = m.getName();
                Function f = new Function(instance, domain, name, annotation.description(), m);
                System.out.println("Found function: " + f.toString());
                functions.add(f);
            }
        }
    }

    public Iterable<Function> getFunctions() {
        return functions;
    }
}
