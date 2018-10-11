/* Program: CopyByte
 * Author: Brent Reeves
 * Date: Fall 2018
 * Course: CS375 Software Engineering II
 * Note: Copy 1 byte from a file to another file
 * Needs: try/catch part for missing files
 *      : command line arguments file1 file2
 */
 
import java.nio.file.*;
import java.io.IOException;

public class CopyFolder {

    public static void main(String[] args)
	{
		CopyOption[] options = new CopyOption[]
		{
			StandardCopyOption.REPLACE_EXISTING,
			StandardCopyOption.COPY_ATTRIBUTES
		};
		
		Path source = Paths.get("input");
		Path out = Paths.get("output");
		try 
		{
			Files.copy(source, out, options);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
    }
}
