package step_definitions;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import static org.junit.Assert.*;

import java.io.FileInputStream;

import copyFile.*;

public class copy
{
	@When("^I open the input$")
	public void i_open_the_input() throws Throwable 
	{
		// Write code here that turns the phrase above into concrete actions
		FileInputStream in = null;
		
		try 
		{
           in = new FileInputStream("input.txt");
		}
		finally 
		{
           if (in != null)
               in.close();
		}
	}

	@Then("^the input should be copied to output$")
	public void the_input_should_be_copied_to_output() throws Throwable 
	{
		// Write code here that turns the phrase above into concrete actions
		boolean areTheSame = true;
		
        FileInputStream in = null;
		FileInputStream out = null;
		
		int a = 0;
		int b = 0;
		
		try
		{
			in = new FileInputStream("input.txt");
			out = new FileInputStream("output.txt");
			
			while (a != -1 && b != -1)
			{
				a = in.read();
				b = out.read();
				if (a != b)
					areTheSame = false;
			}
			if (in.read() != out.read())
			{
				areTheSame = false;
			}
			assertEquals (true, areTheSame);
			system.out.println("input = output");
		}
		finally 
		{
           if (in != null)
               in.close();
		   if (out != null)
               out.close();
		}
	}
}