package io.colligence.api.util;

import io.colligence.api.config.CommonDefs;

import java.nio.charset.Charset;
import java.util.Arrays;

public class ByteArrayUtils {
    /**
     * Convert whole byte array to String
     *
     * @param ba byte array
     * @return value
     */
    public static String byteArrayToString(byte[] ba) {
        return new String(ba, Charset.forName(CommonDefs.CHARSET)).trim();
    }

    /**
     * Convert byte array to String by length
     *
     * @param ba     byte array
     * @param offset offset of byte array
     * @param length length to return
     * @return value
     */
    public static String byteArrayToString(byte[] ba, int offset, int length) {
        return new String(Arrays.copyOfRange(ba, offset, offset + length), Charset.forName(CommonDefs.CHARSET)).trim();

    }

    /**
     * Convert byte array to byte (no ordering)
     *
     * @param ba     byte array
     * @param offset offset of byte array
     * @return value
     */
    public static byte byteArrayToByte(byte[] ba, int offset) {
        return ba[offset];
    }

    /**
     * Convert byte array to short (no ordering)
     *
     * @param ba     byte array
     * @param offset offset of byte array
     * @return value
     */
    public static short byteArrayToShort(byte[] ba, int offset) {
        int value = 0;
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        return (short) value;
    }

    /**
     * Convert byte array to int (no ordering)
     *
     * @param ba     byte array
     * @param offset offset of byte array
     * @return value
     */
    public static int byteArrayToInt(byte[] ba, int offset) {
        int value = 0;
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        return value;
    }

    /**
     * Convert byte array to long (no ordering)
     *
     * @param ba     byte array
     * @param offset offset of byte array
     * @return value
     */
    public static long byteArrayToLong(byte[] ba, int offset) {
        long value = 0;
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        value = (value << 8) | (ba[offset++] & 0xFF);
        return value;
    }

    /**
     * Convert long to byte array (no ordering)
     *
     * @param value  value
     * @param buffer buffer to store
     * @param offset offset of buffer
     */
    public static void longToByteArray(long value, byte[] buffer, int offset) {
        buffer[offset] = (byte) (value >> 56 & 0xFF);
        buffer[offset + 1] = (byte) (value >> 48 & 0xFF);
        buffer[offset + 2] = (byte) (value >> 40 & 0xFF);
        buffer[offset + 3] = (byte) (value >> 32 & 0xFF);
        buffer[offset + 4] = (byte) (value >> 24 & 0xFF);
        buffer[offset + 5] = (byte) (value >> 16 & 0xFF);
        buffer[offset + 6] = (byte) (value >> 8 & 0xFF);
        buffer[offset + 7] = (byte) (value & 0xFF);
    }

    /**
     * Convert int to byte array (no ordering)
     *
     * @param value  value
     * @param buffer buffer to store
     * @param offset offset of buffer
     */
    public static void intToByteArray(int value, byte[] buffer, int offset) {
        buffer[offset] = (byte) (value >> 24 & 0xFF);
        buffer[offset + 1] = (byte) (value >> 16 & 0xFF);
        buffer[offset + 2] = (byte) (value >> 8 & 0xFF);
        buffer[offset + 3] = (byte) (value & 0xFF);
    }


    /**
     * Convert short to byte array (no ordering)
     *
     * @param value  value
     * @param buffer buffer to store
     * @param offset offset of buffer
     */
    public static void shortToByteArray(short value, byte[] buffer, int offset) {
        buffer[offset] = (byte) (value >> 8 & 0xFF);
        buffer[offset + 1] = (byte) (value & 0xFF);
    }

    /**
     * Convert byte to byte array (no ordering)
     *
     * @param value  value
     * @param buffer buffer to store
     * @param offset offset of buffer
     */
    public static void byteToByteArray(byte value, byte[] buffer, int offset) {
        buffer[offset] = (byte) (value & 0xFF);
    }

    public static short swap(short x) {
        return (short)
                (
                        (x << 8) | ((x >> 8) & 0xff)
                );
    }

    public static int swap(int x) {
        return
                (
                        (swap((short) x) << 16) | (swap((short) (x >> 16)) & 0xffff)
                );
    }

    public static long swap(long x) {
        return
                (
                        ((long) swap((int) (x)) << 32) | ((long) swap((int) (x >> 32)) & 0xffffffffL)
                );
    }

    public static float swap(float x) {
        return Float.intBitsToFloat(swap(Float.floatToRawIntBits(x)));
    }

    public static double swap(double x) {
        return Double.longBitsToDouble(swap(Double.doubleToRawLongBits(x)));
    }
}
