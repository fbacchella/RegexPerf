package loghub;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeUtils {
    private static final Unsafe UNSAFE;
    public static final long STRING_VALUE_FIELD_OFFSET;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
            STRING_VALUE_FIELD_OFFSET = getFieldOffset("value");
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private static long getFieldOffset(String fieldName) throws NoSuchFieldException, SecurityException {
        return UNSAFE.objectFieldOffset(String.class.getDeclaredField(fieldName));
    }

    public static char[] toCharArray(String string) {
        return (char[]) UNSAFE.getObject(string, STRING_VALUE_FIELD_OFFSET);
    }

    private UnsafeUtils() {
        // Empty
    }

}
