/**********************************************
*  CS401 Lab Assignment 8                     *
*  File : BinarySearchTreeTesT.java           *
*         Optional test file to test height   *
* height method defined in BinarySearchTree.java *         
*          Assignment for Lab17               *
*                                             *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 28-Mar-2012                        *
***********************************************/

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.Result;
import static org.junit.runner.JUnitCore.runClasses;
import java.util.*;

public class BinarySearchTreeTest extends BinarySearchTree
{
    public static void main(String[ ] args)
    {  
        Result result = runClasses (BinarySearchTreeTest.class);
        System.out.println ("Tests run = " + result.getRunCount() +
                            "\nTests failed = " + result.getFailures());
    } // method main

    protected BinarySearchTree<String> tree;          
    protected BinarySearchTree<Integer> itree;          
                       
    @Before    
    public void RunBeforeEachTest()
    {
        //tree = new BinarySearchTree<String>();      
        itree = new BinarySearchTree<Integer>();      
    } // method RunBeforeEachTest     
                 
    @Test
    public void testHeight1()
    {                      
        tree = new BinarySearchTree<String>();      
            tree.add ("b");
            tree.add ("a"); 
            tree.add ("c");
            tree.add ("e");
            tree.add ("c");
            tree.add ("d");        
            assertEquals (4, tree.height());        
    } // method testAdd    

    @Test
    public void testHeight2()
    {
        tree = new BinarySearchTree<String>();      
	    tree.add("dog");
	    tree.add("turtle");
	    tree.add("cat");
	    tree.add("ferret");
	    tree.add("shark");
	    tree.add("whale");
	    tree.add("porpoise");
    
            assertEquals (5, tree.height());        
    }

   @Test
   public void testHeight3()
   {
        itree = new BinarySearchTree<Integer>();      
	itree.add(3);
	itree.add(18);
	itree.add(4);
	itree.add(99);
	itree.add(50);
	itree.add(23);
	itree.add(5);
	itree.add(101);
	itree.add(77);
	itree.add(87);
        assertEquals (6, tree.height());        
   } 
} // class BinarySearchTreeTest
