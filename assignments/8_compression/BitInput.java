import java.io.BufferedInputStream;
import java.io.IOException;


public final class BitInput
{
    private static BufferedInputStream in = new BufferedInputStream(System.in);
    private static final int EOF = -1;

    private static int buffer;
    private static int bitNumber;

    // static initializer
    static { fillBuffer(); }

    private BitInput() { }

    private static void fillBuffer()
    {
        try
        {
          buffer = in.read();
          bitNumber = 8;
        }
        catch (IOException e)
        {
          System.out.println("EOF");
          buffer = EOF;
          bitNumber = -1;
        }
    }

    public static void close()
    {
        try
        {
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Could not close BinaryStdIn");
        }
    }

    public static boolean isEmpty() {
        return buffer == EOF;
    }

    public static boolean readBoolean() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");
        bitNumber--;
        boolean bit = ((buffer >> bitNumber) & 1) == 1;
        if (bitNumber == 0) fillBuffer();
        return bit;
    }


    public static char readChar() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        if (bitNumber == 8) {
            int x = buffer;
            fillBuffer();
            return (char) (x & 0xff);
        }

        int x = buffer;
        x <<= (8-bitNumber);
        int oldbitNumber = bitNumber;
        fillBuffer();
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");
        bitNumber = oldbitNumber;
        x |= (buffer >>> bitNumber);
        return (char) (x & 0xff);
    }

    public static char readChar(int r) {
        if (r < 1 || r > 16) throw new RuntimeException("Illegal value of r = " + r);

        if (r == 8) return readChar();

        char x = 0;
        for (int i = 0; i < r; i++) {
            x <<= 1;
            boolean bit = readBoolean();
            if (bit) x |= 1;
        }
        return x;
    }

    public static String readString() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        StringBuilder sb = new StringBuilder();
        while (!isEmpty()) {
            char c = readChar();
            sb.append(c);
        }
        return sb.toString();
    }

    public static int readInt() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    public static int readInt(int r) {
        if (r < 1 || r > 32) throw new RuntimeException("Illegal value of r = " + r);

        if (r == 32) return readInt();

        int x = 0;
        for (int i = 0; i < r; i++) {
            x <<= 1;
            boolean bit = readBoolean();
            if (bit) x |= 1;
        }
        return x;
    }
}
