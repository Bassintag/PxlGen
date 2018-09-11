package pxlgen.core.function;

import pxlgen.core.image.ImageBuffer;
import pxlgen.core.util.TypeUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Function.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class Function {

    private final String domain;

    private final String name;

    private final Method run;

    private final Class<?>[] paramTypes;

    private final Object instance;

    public Function(Object instance, String domain, String name, Method run) {
        this.domain = domain;
        this.instance = instance;
        this.name = name;
        this.run = run;
        paramTypes = run.getParameterTypes();
    }

    public boolean isCalleableBy(FunctionCall call) {
        return name.equals(call.getName()) && isCalleableBy(call.getParams());
    }

    public boolean isCalleableBy(Object... params) {
        if (paramTypes.length != params.length + 1)
            return false;
        for (int i = 0; i < params.length; i += 1) {
            if (!TypeUtils.isCompatible(paramTypes[i + 1], params[i].getClass()))
                return false;
        }
        return true;
    }

    public void run(ImageBuffer buffer, Object... params) throws InvocationTargetException, IllegalAccessException {
        List<Object> paramsBuffer = new ArrayList<>();
        paramsBuffer.add(buffer);
        Collections.addAll(paramsBuffer, params);
        run.invoke(instance, paramsBuffer.toArray());
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }
}
