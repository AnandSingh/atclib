/**********************************************
*  CS401 Lab Assignment 10                    *
*  File : GenerateRandomPersonFile.java       *
*        Program to genrate test random number*
* to be test in Lab10SortPerson program       *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 13-Apr-2012                         *
***********************************************/
import java.io.*;
import java.util.*;


public class GenerateRandomPersonFile {
	public static void main(String[] args)
	{
		new GenerateRandomPersonFile().run();
	}

	void run()
	{
		String line = "aaa aaa aaa ";
		Random rand = new Random();

		FileOp temp = new FileOp("temp", 1);
		Boolean t1 = temp.isEmpty();
		printf(String.valueOf(temp.isEmpty()));
		for(int i =0; i<99; i++)
		{
		int randomInt = rand.nextInt(99999);
		String ln = line + String.valueOf(randomInt);
		printf(ln);
		temp.writeFile(ln);

		printf(" " + temp.readFile());
		}
		temp.initReader();
		printf(String.valueOf(temp.isEmpty()));

	}
	
	void printf(String str)
	{
		System.out.println(str);
	}
}
