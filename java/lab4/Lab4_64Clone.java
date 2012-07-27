/**********************************************
*  CS401 Lab Assignment 4                     *
*  File : Lab4_64Clone.java                   *
*         To illustrates why the copy         *
*         constructure is superior to the     *
*         clone() method.                     *
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
** Main class for calculating Fule Economy
*/
public class Lab4_64Clone {

	/*
	** main subroutine 
	*/
	public static void main (String[] args){
	
		ArrayList<String> original = new ArrayList<String> ();
		original.add("yes");
		ArrayList<Integer> copy = (ArrayList<Integer>)original.clone();
		System.out.println(copy.get(0));
	}

}
