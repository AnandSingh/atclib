/**********************************************
*  CS401 Lab Assignment 8                     *
*  File : HeightEstimator.java                *
*         Main program to do the run time     *
* analysis of height method                   *
*         Assignment for Lab17                *
*         added height method                 *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 28-Mar-2012                        *
***********************************************/

import java.util.*;

public class HeightEstimator
{  
  public static void main (String[] args)
  {
    new HeightEstimator().run();
  } // method main 

  
   public void run() 
   {  
     final String PROMPT = "\nPlease enter the number of " + 
       "elements to be inserted for the first trial: ";
  
     final String SIZE_MESSAGE = "The tree size is ";
    
     final String RATIO_MESSAGE =
         "The ratio of the average height to the log, " +
         "base 2, of the tree size is ";
    
     final int SIZES = 5;
    
     final int TRIALS_PER_SIZE = 20;
  
     BinarySearchTree<Double> myTree = new BinarySearchTree<Double>();

     Random rand = new Random();
    
     Scanner sc = new Scanner (System.in);
    
     int n,
         totalHeight;

     double averageHeight;
     double ratio; 
     while (true)
     {
       try
       {
          System.out.print (PROMPT);
          n = sc.nextInt();
    
          for (int i = 1; i <= SIZES; i++) 
          {  

	     totalHeight = 0;
              for (int j = 0; j < TRIALS_PER_SIZE; j++) 
              {
		 // creating the tree of size n
            	 while(myTree.size() < n)
	     		myTree.add(Math.random());

	         //System.out.println("Size :" + myTree.size());
		 // calculate the hieght
		 totalHeight = totalHeight + myTree.height();
		 // clear the tree after every trial
		 myTree.clear();
              } // for each trial
              System.out.println (SIZE_MESSAGE + n);
              averageHeight = (double)totalHeight / TRIALS_PER_SIZE;
	      // detremine the ration of average Height and log 2 (n)
              ratio = averageHeight / (double)(Math.log(n) / Math.log(2));
	      System.out.println ("The Average Height is " + averageHeight);                         
	      System.out.println (RATIO_MESSAGE + ratio + "\n");       
	      	      
              n *= 2;
          } // for each size
          break;
       }//try
       catch (InputMismatchException e)
       {
          System.out.println (e);
          sc.nextLine();
       }//catch InputMismatchException       
     } // while
   } // method run
  
} // class HeightEstimator
