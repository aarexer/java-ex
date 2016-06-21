package utils;

import org.junit.Assert;
import org.junit.Test;

public class ByteArrayUtilsTest extends Assert {
    @Test
    public void testIntegerConverting() {
        byte[] arr = {0x00, 0x00, 0x00, 0x01};
        assertEquals(ByteArrayUtils.byteArrayToInteger(arr), 1);
    }
}
