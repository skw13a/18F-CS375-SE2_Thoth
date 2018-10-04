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

public class CopyFile {

    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("fileInput/input.txt");
            out = new FileOutputStream("fileOutput/output.txt");
            int b = 0;


		for (b = in.read(); b != -1; b = in.read())
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
