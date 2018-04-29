package pxlgen.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * TypeUtils.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public final class TypeUtils {

    private static Map<Class<?>, Class<?>> types = new HashMap<>();

    static {
        types.put(int.class, Integer.class);
        types.put(float.class, Float.class);
        types.put(boolean.class, Boolean.class);
    }

    public static boolean isCompatible(Class<?> dest, Class<?> from) {
        boolean assignable = dest.isAssignableFrom(from);
        if (assignable || !dest.isPrimitive())
            return assignable;
        return types.containsKey(dest) && from.equals(types.get(dest));
    }

}
