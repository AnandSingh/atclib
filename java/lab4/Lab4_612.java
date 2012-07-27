/**********************************************
*  CS401 Lab Assignment 4                     *
*  File : Lab4_612.java                       *
*         Print out elements of an Array      *
*         having exactly four letters         *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 10-Feb-2012                        *
***********************************************/

/*
** import java util library
*/
import java.util.*;


/*
** Main class 
*/
public class Lab4_612 {

	/*
	** main subroutine 
	*/
	public static void main (String[] args){
		new Lab4_612().run();
        }	

	public void run() {
		ArrayList<String> words = new ArrayList<String> ();
                // for reciving input from standard input (stdin)
		Scanner in = new Scanner(System.in);     
                String choice;
		Boolean check = true;
		Boolean prompt = true;
                while(check)
		{
			if(prompt == true)
			{
	      			System.out.print("Enter words to add into array : ");
                		words.add(in.nextLine());
			}
	        	System.out.print("Keep adding words [y/n] : ");
                	choice = in.nextLine();
                	if(choice.equalsIgnoreCase("y") ){
                                prompt = true;
	        	//	System.out.println("yes");
			}
			else if(choice.equalsIgnoreCase("n")) {
	        	//	System.out.println("no");
                		check = false;
			}
			else {
	        		System.out.println("ERROR! wrong choice");
				prompt = false;
			}
				
		}
		displayArray(words);
		System.out.println("=============================================================");
                System.out.println("Displaying elements of Array having exactly four letters");
		System.out.println("");
		System.out.println("a. using an index");
		displayArrayUsingIndex(words);
		System.out.println("b. using an explicit iterator");
		displayArrayIterartor(words);
		System.out.println("c. using an enhanced for statement");
		displayArrayEnhancedFor(words);
		
	}
        
        /*
	* Display the Original Array Elements  
	*/
        public void displayArray(ArrayList<String> arr)
	{
		int size = arr.size();
		System.out.println("");
		System.out.println("Total Array Size is : " + size);
		System.out.println("Array Elements :");
		System.out.print("{ ");
		for(int i = 0; i< size; i++)
		{
			System.out.print(arr.get(i)+" ");
		}
		System.out.println("}");
		System.out.println("");
	}
	
	/*
        * Display the Array Elements having four letters using an idenx 
        */
        public void displayArrayUsingIndex(ArrayList<String> arr)
	{
		System.out.print("{ ");
		for(int i = 0; i< arr.size(); i++)
		{
			if(arr.get(i).length() == 4)
			{
				System.out.print(arr.get(i)+" ");
			}
		}
		System.out.println("}");
		System.out.println("");
	}

	/*
        * Display the Array Elements having four letters using an explicit iterator 
        */
	public void displayArrayIterartor(ArrayList<String> words)
	{
                Iterator<String> iter = words.iterator();
		System.out.print("{ ");
		while(iter.hasNext())
		{
      			String tmp = iter.next();
			if (tmp.length() == 4)
			{
				System.out.print(tmp+"");
			}	
		}
		System.out.println("}");
                System.out.println("");

	}

	/*
        * Display the Array Elements having four letters using an enhanced for statement 
        */
	public void displayArrayEnhancedFor(ArrayList<String> arr)
	{
		System.out.print("{ ");
		for(Object i : arr)
		{
			if(i.toString().length() ==4)
			{
				System.out.print(i.toString()+" ");
			}
		}
		System.out.println("}");
                System.out.println("");
	}
}
