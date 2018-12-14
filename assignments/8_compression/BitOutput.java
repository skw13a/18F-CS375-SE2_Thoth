import java.io.BufferedOutputStream;
import java.io.IOException;

public final class BitOutput {
    private static BufferedOutputStream out = new BufferedOutputStream(System.out);

    private static int buffer;
    private static int bitNumber;

    // Making this a singleton
    private BitOutput() { }

    private static void writeBit(boolean bit)
    {
        buffer <<= 1;

        if (bit) buffer |= 1;
          bitNumber++;

        if (bitNumber == 8)
          clearBuffer();
    }

    private static void writeByte(int x) {
        assert x >= 0 && x < 256;

        if (bitNumber == 0) {
            try { out.write(x); }
            catch (IOException e) { e.printStackTrace(); }
            return;
        }

        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    private static void clearBuffer() {
        if (bitNumber == 0) return;
        if (bitNumber > 0) buffer <<= (8 - bitNumber);
        try { out.write(buffer); }
        catch (IOException e) { e.printStackTrace(); }
        bitNumber = 0;
        buffer = 0;
    }

    public static void flush() {
        clearBuffer();
        try { out.flush(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void close() {
        flush();
        try { out.close(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void write(boolean x) {
        writeBit(x);
    }

    public static void write(byte x) {
        writeByte(x & 0xff);
    }

    public static void write(int x) {
        writeByte((x >>> 24) & 0xff);
        writeByte((x >>> 16) & 0xff);
        writeByte((x >>>  8) & 0xff);
        writeByte((x >>>  0) & 0xff);
    }

    public static void write(int x, int r) {
        if (r == 32) write(x);
        if (r < 1 || r > 32)        throw new RuntimeException("Illegal value for r = " + r);
        if (x < 0 || x >= (1 << r)) throw new RuntimeException("Illegal " + r + "-bit char = " + x);
        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(double x) {
        write(Double.doubleToRawLongBits(x));
    }

    public static void write(long x) {
        writeByte((int) ((x >>> 56) & 0xff));
        writeByte((int) ((x >>> 48) & 0xff));
        writeByte((int) ((x >>> 40) & 0xff));
        writeByte((int) ((x >>> 32) & 0xff));
        writeByte((int) ((x >>> 24) & 0xff));
        writeByte((int) ((x >>> 16) & 0xff));
        writeByte((int) ((x >>>  8) & 0xff));
        writeByte((int) ((x >>>  0) & 0xff));
    }

    public static void write(float x) {
        write(Float.floatToRawIntBits(x));
    }

    public static void write(short x) {
        writeByte((x >>>  8) & 0xff);
        writeByte((x >>>  0) & 0xff);
    }

    public static void write(char x) {
        if (x < 0 || x >= 256) throw new RuntimeException("Illegal 8-bit char = " + x);
        writeByte(x);
    }

    public static void write(char x, int r) {
        if (r == 8) write(x);
        if (r < 1 || r > 16)        throw new RuntimeException("Illegal value for r = " + r);
        if (x < 0 || x >= (1 << r)) throw new RuntimeException("Illegal " + r + "-bit char = " + x);
        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(String s) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i));
    }

    public static void write(String s, int r) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i), r);
    }
}
