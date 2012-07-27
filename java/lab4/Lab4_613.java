/**********************************************
*  CS401 Lab Assignment 4                     *
*  File : Lab4_613.java                       *
*         Test and define uniquefy method     *
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
public class Lab4_613<T> {

	/*
	** main subroutine 
	*/
	public static void main (String[] args){
		new Lab4_613<Integer> ().run();
        }	

	public void run() {
		ArrayList<Integer> intArray = new ArrayList<Integer> ();
                // for reciving input from standard input (stdin)
		Scanner in = new Scanner(System.in);     
		Boolean check = true;
	      	System.out.println("Keep entering Integer to add into an array ");
                System.out.println("Press non-integer value to exit");
		while(check)
		{	
		     try {
                	intArray.add(in.nextInt());
		     } catch (Exception e)
		     {
			check = false;
		     }		
		}
		displayArray(intArray);
                ArrayList<Integer> uniqueIntArray = uniquefy(intArray);
		System.out.println("==============================");
	        System.out.println("       After uniquefy         ");
		System.out.println("==============================");
		displayArray(uniqueIntArray);
	}
        
        public void displayArray(ArrayList<Integer> arr)
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

        public static <T> ArrayList <T> uniquefy(ArrayList <T> arr)
	{
		ArrayList<T> uniqueArr = new ArrayList<T> ();
		int size = arr.size();
		// add the first element of array into unique array
		uniqueArr.add(arr.get(0));
		
		for(int i = 1; i< size; i++)
		{
			Boolean foundUniqueElement = true;
			// get the element 
			T tmp = arr.get(i);
			// check if this element is already present in unique array
			for(int j = 0; j< uniqueArr.size(); j++)
			{
				
				if(tmp == uniqueArr.get(j))
				{
					// no need to add this element into unique array
		                        foundUniqueElement = false;
					break;
				}
			
			}
			if(foundUniqueElement)
			{
				//add to the unique qrray
				uniqueArr.add(tmp);
			}
		}
		return uniqueArr;
	}	
}
