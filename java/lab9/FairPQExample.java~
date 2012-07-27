/**********************************************
*  CS401 Lab Assignment 9                     *
*  File : FairPQExample.java                  *
*        This programs provide interface to   *
* form the Fair Priority Queue, and hence     *
* user can validate it fairness               *
*                                             *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 7-Apr-2012                         *
***********************************************/

import java.util.*;

public class FairPQExample
{
    public static void main (String[ ] args)
    {
        new FairPQExample().run();
    } // method main
    
    
    public void run()
    {
         final String PROMPT1 = "Please enter student's name and GPA, or " ;

         final String PROMPT2 = " to quit: ";

         final String SENTINEL = "***";

         final String RESULTS1 = "\nHere are the student names and GPAs, " +
                                 "in increasing order of GPAs:";

         final String RESULTS2 = "\nHere are the student names and GPAs, " +
                                 "in alphabetical order of names:";
	 final String RESULTS3 = "\nThe Fair Priority queue in order os GPA: ";
	 final String RESULTS4 = "\nThe Fair Priority queue in order of Name: ";
         
         CS401FairPQ<Student> pq1 = new CS401FairPQ<Student>(),
                         pq2 = new CS401FairPQ<Student> (11, new ByName());

         Scanner sc = new Scanner (System.in);

         String line;

         while (true)
         {
             System.out.print (PROMPT1 + SENTINEL + PROMPT2);
             line = sc.nextLine();
             if (line.equals (SENTINEL))
                 break;
	     Scanner sb = new Scanner (line);
             String name = sb.next();
	     Double gpa = sb.nextDouble();

             pq1.add (new Student (name, gpa));
             pq2.add (new Student (name, gpa));
         } // while

	 System.out.println(RESULTS3);
	 System.out.println(pq1);
	 System.out.println(RESULTS4);
	 System.out.println(pq2);

         System.out.println (RESULTS1);
         while (!pq1.isEmpty())
             System.out.println (pq1.remove());
         
         System.out.println (RESULTS2);
         while (!pq2.isEmpty())
             System.out.println (pq2.remove());                        
     } // method run

} // class HeapExample
