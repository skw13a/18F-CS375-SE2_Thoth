/* Program: CopyByte
 * Author: Brent Reeves
 * Date: Fall 2018
 * Course: CS375 Software Engineering II
 * Note: Copy 1 byte from a file to another file
 * Needs: try/catch part for missing files
 *      : command line arguments file1 file2
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyByte {

    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("input.txt");
            out = new FileOutputStream("output.txt");
            byte b[] = new byte[2];

	    in.read(b, 0, 2);
	    out.write(b);

        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
