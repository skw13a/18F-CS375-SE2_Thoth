/* Program: copyAll
 * Author: Brent Reeves
 * Date: Fall 2018
 * Course: CS375 Software Engineering II
 * Note: Copy a folder and all of it's contents
 */
 
import java.nio.file.*;
import java.io.File;
import java.io.IOException; 
import java.io.BufferedReader; 
import java.io.InputStreamReader;

public class CopyAll {

	private static void stopHere()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
			String in = reader.readLine();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	private static void copyDirectory(File in, Path out, CopyOption[] options)
	{
		File[] fileList = in.listFiles();
		for (File file : fileList)
		{
			//System.out.println("file: " + file.getAbsolutePath());
			
			Path filePath = file.toPath();
			
			//System.out.println("file path = " + filePath.toString());
			
			String newOut = out.toString();
			newOut = newOut + "\\" + file.getName();
			Path outPath = Paths.get(newOut);
			
			try
			{
				//stopHere();
				File outputFile = outPath.toFile();
				if (file.listFiles() == null || !(outputFile.exists()))
					Files.copy(filePath, outPath, options);
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
			
			if (file.listFiles() != null)
			{
				//System.out.println("new directory. out path = " + outPath.toString());
				copyDirectory(file, outPath, options);
			}
		}
	}

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
		
			Path inPath = Paths.get(in);
			Path outPath = Paths.get(output);
			
			//System.out.println("in path = " + inPath.toString());
			//System.out.println("out path = " + outPath.toString());
			
			File inFile = inPath.toFile();
			
			Files.copy(inPath, outPath, options);
			copyDirectory(inFile, outPath, options);
			
			System.out.println("");
			System.out.println("The folder you wished to copy has been placed in output/");
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
    }
}
