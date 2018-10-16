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
import java.io.BufferedReader; 
import java.io.InputStreamReader;

public class CopyFolder {

    public static void main(String[] args)
	{
		CopyOption[] options = new CopyOption[]
		{
			StandardCopyOption.REPLACE_EXISTING,
			StandardCopyOption.COPY_ATTRIBUTES
		};
		
		System.out.println("Please input the name of the folder you wish to copy: ");
		
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		
			String in = reader.readLine();
			String output = "output/" + in;
		
			Path source = Paths.get(in);
			Path out = Paths.get(output);
			Files.copy(source, out, options);
			System.out.println("");
			System.out.println("The folder you wished to copy has been placed in output/");
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
    }
}
