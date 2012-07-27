/**********************************************
*  CS401 Lab Assignment 10                    *
*  File : Lab10SortPerson.java                *
*        Main program                         *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 13-Apr-2012                         *
***********************************************/

import java.util.*;
import java.io.*;


/**
 * main Program which acept input an 
 * Person file which consist of - last name followed
 *  by a blank name follwed by first name follwed by
 *  middle name and social security number
 * Teh file will sorted by name, and social security number
 */
public class Lab10SortPerson
{
	final String PROMPT1 = "Please enter the path for the file to be sorted (Enter *** to quit): ";

	final String PROMPT2 = " file consist of\n";
	final String PROMPT3 = " file has been sorted";
	final String PROMPT4 = " file will now consist of";
	final String MENU_PROMPT = "\n===== MENU =====";
	final String MENU_PROMPT1 = "1. To sort file using Selection Sort.";
	final String MENU_PROMPT2 = "2. To sort file using Insertion Sort.";
	final String MENU_PROMPT3 = "3. To sort file using Bubble Sort.";
	final String MENU_PROMPT4 = "99. To EXIT.";
	final String MENU_PROMPT5 = "\n Enter Your Choice : ";
	final String BYE_PROMPT = "\n Bye Bye !! \n";

	final int NUM_BLOCK = 2;

	String personFile;

	String leftTopFile;
	String leftBottomFile;
	String rightBottomFile;
	
	int LineCount = 0;
	
	FileOp leftTop;// = new FileOp("leftTop.txt");
	FileOp leftBottom;// = new FileOp("leftBottom.txt");
	FileOp rightBottom;// = new FileOp("rightBottom.txt");
	FileOp rightTop;// = new FileOp(personFile);
	
	FileOp srcTop;
	FileOp srcBottom;
	FileOp destTop;
	FileOp destBottom;


	Comparator comp;

	/**
	 * Main method
	 */
	public static void main (String[ ] args)
	{
		new Lab10SortPerson().run();
	} // method main

	/**
	 * Main program excution method
	 */
	public void run()
	{
		while(true) {
			// Take the input File from user
			Scanner sc = new Scanner (System.in);
		
			System.out.print(PROMPT1);
			personFile = sc.nextLine();
			if(personFile.compareTo("***") == 0)
			{
				//exit from main program
				System.out.println(BYE_PROMPT);
				return;
			}
		
			// Print the content of the file 
			try{
				// Open the file  
				FileInputStream fstream = new FileInputStream(personFile);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				System.out.println("\n" + personFile + PROMPT2);
				//Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					// Print the content on the console
			 		System.out.println (strLine);
					LineCount++;
				}
				//Close the input stream
		  	 	in.close();
				break;
			}catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		}

		// wait for the user input to select the 
		// sorting method, and then process the sorting usingt the 
		// seleceted method
		while(true) {
		
			System.out.println(MENU_PROMPT);
			System.out.println(MENU_PROMPT1);
			System.out.println(MENU_PROMPT2);
			System.out.println(MENU_PROMPT3);
			System.out.println(MENU_PROMPT4);
			System.out.print(MENU_PROMPT5);
			try {
				Scanner sc = new Scanner (System.in);
				int choice = sc.nextInt();
				if(choice == 99)
				{
					System.out.println(BYE_PROMPT);
					break;
				}
				if(choice == 1 || choice == 2 || choice == 3 ) {
					
					// sort the file 
					Boolean left = sortFile(personFile, choice);
					// copy the sorted file to a new file
					// based on the return state of sortFile method
					if(left == true)
						leftTop.copyFile("sort_"+personFile);
					else
						rightTop.copyFile("sort_"+personFile);

					FileOp sortFile = new FileOp("sort_"+personFile, 0);
					System.out.println("\n\t Finised Sorting of file " + "\""+personFile+"\"");
					System.out.println("\t Sorted File is "+ "\""+"sort_"+personFile+"\"\n");
					// display the content of the sorted files
					sortFile.printFile();
					// restore the original file
					FileOp bkupFile = new FileOp(personFile+"~", 0);
					bkupFile.copyFile(personFile);
					// delete the bakup file
					bkupFile.deleteFile();
					
					choice = 0;
				}else {
					System.out.println("ERROR ! Wrong Input\n");
				}

			}catch (Exception e) {
				System.out.println(" :(:(:( ERROR: " + e.getMessage());
			}

		}

	    
	}

	/**
	 * Main method to be called for sorting an file
	 */
	Boolean sortFile(String personFile, int sortChoice)
	{
		// create teh tempory files 
		// which will be used for moving 
		// sorted string array
		leftTop = new FileOp("leftTop.txt", 1);
		leftBottom = new FileOp("leftBottom.txt", 1);
		rightBottom = new FileOp("rightBottom.txt", 1);
		rightTop = new FileOp(personFile, 0);

		// create an bakup of the original file
		// so that it can be restored to be used for
		// testing with diffrent sort slection
		rightTop.copyFile(personFile+"~");	
		
		// set the comparator method, specific to the file contens
		comp = new ComparePersonFile();

		// first Pass, i.e based on the NUM_BLOCKS, 
		// read NUM_BLOCKS from input filem, sort it and then 
		// store it to leftTop and leftBottom file alternative
		srcTop = rightTop;
		srcBottom = rightBottom;
		destTop = leftTop;
		destBottom = leftBottom;
		// first Pass
		SortFirstPass(sortChoice);


		//print("Final Pass\n");
		//Final sorting using recursion
		// This method will keep on moving string arrays
		// from left to right set fof files, for each stage it merge 
		// top and bottom pair of files, with the resulting double sized 
		// blocks on the other top an dbottom pair
		return SortFinalPass(sortChoice, true, 2*NUM_BLOCK);

	}

	/**
	 * Final sorting using recursion
	 * This method will keep on moving string arrays
	 * from left to right set fof files, for each stage it merge 
	 * top and bottom pair of files, with the resulting double sized 
	 * blocks on the other top an dbottom pair
	 */
	Boolean SortFinalPass(int sortChoice, Boolean leftToRight, int readNumBlocks)
	{
		if (leftToRight == true)
		{
			//System.out.println(" left to right movement");
			//its an left to right movement of files
			srcTop = leftTop;
			srcBottom = leftBottom;
			destTop = rightTop;
			destBottom = rightBottom;
			leftToRight = false;

		}else{
			//System.out.println(" right to left movement");
			//its an right to left movement of files
			srcTop = rightTop;
			srcBottom = rightBottom;
			destTop = leftTop;
			destBottom = leftBottom;
			leftToRight = true;
		}
		

		// initialize the top and bootom file
		// for read and write respectively
		srcTop.initReader();
		srcBottom.initReader();
		destTop.cleanFile();
		destBottom.cleanFile();

	        //System.out.println("\tCHECK +");	
		//srcTop.printFile();
		//srcTop.printFile();
	        //System.out.println("\tCHECK -");	
		int firstLoop;
		if(readNumBlocks < LineCount) {
			 firstLoop = LineCount / readNumBlocks;
		}else
		{
			firstLoop = 1;
			readNumBlocks = LineCount;
		}
		// get the string arry from both  
		// top and bottom files and then sort it 
		// and placed its counter files in an alternative way
		SortAndTransfer(firstLoop, readNumBlocks, sortChoice);

		// if destBottom file is empty then 
		// it means sorting is complete 
		// based on the return type, the calling 
		// method will select the rightTop or leftTop file 
		// which contains the sorte dfiles
		if(destBottom.isEmpty())
		{
			// sorting is complete 
			//rint("Sorting is complete");
			//System.out.println("===> " + leftToRight);
			return leftToRight;
		}else{
			// call the next iteration through recursion
			return SortFinalPass(sortChoice, leftToRight, readNumBlocks * 2 );
		}

	}
	

	/**
	 * get the string arry from both  
	 * top and bottom files and then sort it 
	 * and placed double sized content in 
	 * its counter files in an alternative way
	 */
	void SortAndTransfer(int loop, int readNumBlocks, int sortChoice)
	{
		int count = -1;
		//System.out.println("SortAndTransfer "+ loop + " " + readNumBlocks);
		while(loop-- > 0)
		{
			count++;
			//System.out.println("   COUNT 111 "+ count);
	                String[] line = new String[readNumBlocks];
			int j = 0;

			//srcTop.printFile();
			//srcBottom.printFile();
			//System.out.println("\n");
			int maxLoop;
			if(readNumBlocks == LineCount )
				maxLoop = readNumBlocks;
			else
				maxLoop = readNumBlocks/2;

			for(int i=0; i < maxLoop; i++)
			{
				String temp;
				temp = srcTop.readFile();
				//System.out.println("t1: "+ temp);
				if (temp != null)
					line[j++] = temp;
				//System.out.println(line[j-1]);
				temp = srcBottom.readFile();
				//System.out.println("t2: "+temp);
				if(temp != null)
					line[j++] = temp;
				//System.out.println(line[j-1]);
				
			}
			//System.out.println("");

			Sort(line, sortChoice);
			WriteSortedLine(count, line, j);
		}
			
		if(LineCount % (readNumBlocks) > 0)
		{
			// remaining sort and transfer to 
			int cnt = LineCount % readNumBlocks;
			String[] line = new String[cnt];
			count++;
			//System.out.println("   COUNT 222 "+ count);
			int j = 0;
			for(int i =0; i< cnt; i++)
			{
				String temp;
			        temp = srcTop.readFile();
				if(temp != null){
					line[j++] = temp;
				}
				temp =srcBottom.readFile();
				if (temp != null) {
					line[j++] = temp;
				}
			}
			Sort(line, sortChoice);
			WriteSortedLine(count, line, j);

		}

	}

	/**
	 * this method is used in only for first pass
	 * i.e, moving from userinput file to leftTop and 
	 *  rightBottom files
	 */
	void SortFirstPass(int sortChoice)
	{
		int firstLoop = LineCount / NUM_BLOCK;
		//print("first loop " + firstLoop);
		int count = -1;
		while(firstLoop-- > 0) 
		{
			count++;
			// read from the first File
	                String[] line = new String[NUM_BLOCK];
			//print("//*");
	                for (int i=0; i<NUM_BLOCK; i++)
	                {
	                        line[i] =  rightTop.readFile();
	                  //      print(line[i]);
	                }
			//print("*/");
			Sort(line, sortChoice);
			WriteSortedLine(count, line, NUM_BLOCK);
		}
		if(LineCount % NUM_BLOCK > 0)
		{
			int loop = LineCount % NUM_BLOCK;
	                String[] line = new String[loop];
			//print("seconfloop " + loop);
			count++;
			//print("//*");
			for(int i=0; i<loop; i++)
			{
				line[i] = rightTop.readFile();
				//print(line[i]);
			}
			//print("*/");
			Sort(line, sortChoice);
			WriteSortedLine(count, line, loop);
		}
	}

	/**
	 * This method will write the sorted String array 
	 * to the destination file. i.e. either Top or Bottom files
	 */
	void WriteSortedLine(int topOrBottom, String[] line, int lineLen)
	{
		FileOp w;
		boolean top = false;
		//System.out.println(" ++ w ++");
		if(topOrBottom % 2 == 0)
			top = true;
		// select where to write either 
		// top file or bottom file
		if(top == true)
			w = destTop;
		else
			w = destBottom;	

		//w.printFile();
		for(int i=0; i<lineLen; i++)
		{
			w.writeFile(line[i]);
			//print(line[i]);
		}
		//w.initReader();
		//w.printFile();
		//System.out.println(" -- w --");

	}

	/**
	 * Sort selcetion method based on user input
	 */
	void Sort(String[] x, int sortChoice)
	{
		//System.out.println("SORT : "+x.length);
		switch (sortChoice)
		{
			case 1:
				SelectionSort(x);
				break;
			case 2:
				InsertionSort(x);
				break;
			case 3:
				BubbleSort(x);
				break;
			default:
				break;
		}
		//System.out.println("\tAfter sorting");
		//for(int i=0; i < (x.length); i++)
		//{
		//	System.out.println(x[i]);
		//}

	}

	/**
	 * Selction Sort
	 */
	void SelectionSort(String[] x)
	{
		for(int i=0; i < (x.length -1 ); i++)
		{
			int pos = i;
			for(int j = (i + 1); j < x.length; j++)
				if(comp.compare(x[j], x[pos]) < 0)
					pos = j;
			swap(x, i, pos);
		}
	}

	/**
	 * Swap method
	 */
	void swap (String[] x, int a, int b)
	{
		String temp = x[a];
		x[a] = x[b];
		x[b] = temp;
	}

	/**
	 * Insertion Sort
	 */
	void InsertionSort(String[] x)
	{
		for(int i=1; i< x.length; i++)
			for(int j=i; ( (j > 0) && (comp.compare(x[j-1], x[j]) > 0) ); j--)
				swap(x, j, j-1);

	}

	/**
	 * Bubble Sorting
	 */
	void BubbleSort(String[] x)
	{
		int finalSwapIdx = x.length - 1;
		int swapIdx = 0;

		while(finalSwapIdx > 0)
		{
			swapIdx = 0;
			for(int i=0; i< finalSwapIdx; i++)
				if(comp.compare(x[i], x[i+1] ) > 0)
				{
					swap(x, i, i+1);
					swapIdx = i;
				}
			finalSwapIdx = swapIdx; 

		}

	}

	/**
	 * used for quick debuging 
	 */
	void print(String l)
	{
		System.out.println(l);
	}


/*
	class ParsePersonData{
	//	String FirstName;
	//	String MiddleName;
	//	String LastName;
	//	Integer SecurityNum;

		ParsePersonData(String line)
		{
			String[] temp;
			temp = line.split(" ");
			LastName = temp[0];
			FirstName = temp[1];
			MiddleName = temp[2];
			SecurityNum = Integer.valueOf(temp[3]);
			System.out.println(">>>" + line);
			System.out.println(">>>" + FirstName+ " " + MiddleName +" " +LastName+ " " + SecurityNum );
		}
	
		public int compare (String s1, String s2)
		{
			String[] temp_1;
			String[] temp_2;
			int ret = 0;
			temp_1 = s1.split(" ");
			temp_2 = s2.split(" ");
			// comaper first name
			ret = temp_1[1].compareTo(temp_2[1]);
			if(ret == 0)
			{
				//compare sencond name
				ret = temp_1[2].compareTo(temp_2[2]);
				if (ret == 0)
				{
					// compare last name 
					ret = temp_1[0].compareTo(temp_2[0]);
					if (ret == 0)
					{
						// comapre the security key
						ret = temp_1[3].compareTo(temp_2[3]);
					}
				}	
			}
			return ret;

		}
	}
	*/
}
