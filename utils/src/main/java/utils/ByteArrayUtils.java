package utils;

import java.nio.ByteBuffer;

public class ByteArrayUtils {
    /**
     * Convert byte array to integer
     * @param bytes array of bytes
     * @return integer
     */
    public static int byteArrayToInteger(byte[] bytes) {
        // big-endian by default
        ByteBuffer wrapped = ByteBuffer.wrap(bytes);
        return wrapped.getInt();
    }

    /**
     * Convert byte array to short
     * @param bytes array of bytes
     * @return short
     */
    public static short byteArrayToShort(byte[] bytes) {
        // big-endian by default
        ByteBuffer wrapped = ByteBuffer.wrap(bytes);
        return wrapped.getShort();
    }

    //// TODO: 14.06.16 byte to integer without bytebuffer
}
