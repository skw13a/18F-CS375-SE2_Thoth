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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class  {

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
	
	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
			IOException {

		FileInputStream fis = new FileInputStream(file);

		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		//System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}
	
	private static void zipFolder(File in, Path out)
	{
		File[] fileList = in.listFiles();
		try
		{
			FileOutputStream fos = new FileOutputStream(out.toString() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			for (File file : fileList)
			{
				System.out.println("file: " + file.getAbsolutePath());
				
				Path filePath = file.toPath();
				
				System.out.println("file path = " + filePath.toString());
				
				String newOut = out.toString();
				newOut = newOut + "\\" + file.getName();
				Path outPath = Paths.get(newOut);

				stopHere();
				if (file.listFiles() == null)
					addToZip(in, file, zos);	
				
				zos.close();
				fos.close();
			}
		}
			catch(IOException e)
			{
				System.out.println(e);
			}
	}

    public static void main(String[] args)
	{
		
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
			zipFolder(inFile, outPath);
			
			System.out.println("");
			System.out.println("The folder you wished to copy has been placed in output/");
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
    }
}
