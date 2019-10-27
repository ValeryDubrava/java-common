package ru.ddg.java.commons.utils;

/**
 * Simple type utils.
 */
public class TypeUtils {
    /**
     * Detects if the specified object is array.
     * @param obj any object.
     * @return True if object is an array, false otherwise.
     */
    public static boolean isArray(final Object obj) {
        return obj instanceof Object[] || obj instanceof boolean[] ||
                obj instanceof byte[] || obj instanceof short[] ||
                obj instanceof char[] || obj instanceof int[] ||
                obj instanceof long[] || obj instanceof float[] ||
                obj instanceof double[];
    }
}
