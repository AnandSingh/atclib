/**********************************************
*  CS401 Lab Assignment 10                    *
*  File : FileOp.java                         *
*       Class to be used for file operartion  *
* performed while sorting an file             *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 13-Apr-2012                        *
***********************************************/

import java.io.*;

/**
 * Class to be used for file operartion 
 * performed while sorting an file
 */
public class FileOp {
	
	String FileName;
        FileOutputStream out;
        FileInputStream inStream;
       // DataInputStream in = new DataInputStream(instream);
        BufferedReader br;// = new BufferedReader(new InputStreamReader(in));
	BufferedWriter bw;	

	String ERROR_MESG = "ERROR: (FileOp.java) ";

	/**
	 * Constructure
	 */
	FileOp(String FileName, int newFile)
	{
		this.FileName = FileName;
		try {
			File file = new File(FileName);
			if (!file.exists()) 
			{
				file.createNewFile();
			}else
			{
				if(newFile == 1)
				{
					file.delete();
					file.createNewFile();
				}
			}

			// initillaizte for reading 
			initReader();
			// initiliase for writing
			initWriter();

		} catch (Exception e)
		{
			System.out.println( ERROR_MESG +  e.getMessage());
		}

	}

	/**
	 * delet and file
	 */
	void deleteFile()
	{
		try {
			File file = new File(FileName);
			file.delete();
		} catch (Exception e)
		{
			System.out.println( ERROR_MESG +  e.getMessage());
		}
	}


	/**
	 * resetimg the reader index so that it 
	 * reads from the begnining
	 */
	void initReader()
	{
		try {
			File file = new File(FileName);
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			br = new BufferedReader(new InputStreamReader(in));
		}catch (Exception e){
			System.out.println( ERROR_MESG +  e.getMessage());
		}
	}

	/**
	 * delete the old file and create an new file
	 * to add new content
	 *
	 */
	void initWriter()
	{
		try {
		/*	File file = new File(FileName);
			if (!file.exists())
			{
			    file.createNewFile();
		        }else
		        {
		            file.delete();
		            file.createNewFile();
		        }
		*/
			bw = new BufferedWriter(new FileWriter(FileName, true));
		}catch (Exception e){
			System.out.println( ERROR_MESG +  e.getMessage());
		}
	}
	
	/**
	 * Start a empty file whioch is used for writing sorted 
	 * strings
	 */
	void cleanFile()
	{
		try {
			File file = new File(FileName);
			if (!file.exists())
			{
			    file.createNewFile();
		        }else
		        {
		            file.delete();
		            file.createNewFile();
		        }

			initReader();
			initWriter();
	
		}catch (Exception e){
			System.out.println( ERROR_MESG +  e.getMessage());
		}
		
	}

	/**
	 * checks if file is empty
	 */
	Boolean isEmpty()
	{
		int count = 0;
		// save the state
		BufferedReader temp = br;
		try {
			while (temp.readLine() != null)
			{
				count++;
			}
		} catch (Exception e)
		{
			System.out.println( ERROR_MESG +  e.getMessage());
		}
		// save the state
	//	br = temp;
		if(count == 0)
			return true;
		else 
			return false;
	}

	/**
	 * read file line by line
	 */
	String readFile()
	{
		try {
			return br.readLine();
		} catch (Exception e) {
			System.out.println( ERROR_MESG +  e.getMessage());
		}
		return null;
	}

	/**
	 * wriet file line by line (apend the line)
	 */
	int writeFile(String line)
	{
		try {
		//	FileWriter fstream = new FileWriter(FileName,true);
		//	  BufferedWriter out = new BufferedWriter(fstream);
			  
		       	bw.append(line);
		        bw.newLine();
			bw.flush();
			      //Close the output stream
			       // out.close();
	                //  bw.write(line);
	                //  bw.newLine();
                } catch (Exception e) {
			System.out.println( ERROR_MESG +  e.getMessage());
		}

		return 0;
	}

	/**
	 * Print the contemnt of file
	 */
	void printFile()
	{
		System.out.println("  ==> "+ FileName + " file contains:");
		// save the curret state
	        BufferedReader temp = br;
		initReader();
	        
		while (true){
			String line = readFile();
			if (line == null){
				// restore the current state
				br = temp;
				System.out.println("  <==");
				return;
			}else{
				System.out.println("  " +line);
			}

		}
		     

	}

	/**
	 * Copy one file contenst to an new file 
	 */
        void copyFile(String destFileName)
	{
  		try{
  			File f1 = new File(FileName);
  			File f2 = new File(destFileName);
  			InputStream in = new FileInputStream(f1);
  
 	 		OutputStream out = new FileOutputStream(f2);

  			byte[] buf = new byte[1024];
  			int len;
  			while ((len = in.read(buf)) > 0){
  				out.write(buf, 0, len);
  			}
  			in.close();
  			out.close();
			//System.out.println("File copied.");
 	 	}
 		catch(Exception e){
			System.out.println("ERROR! " + e.getMessage());
		}
	}

}
