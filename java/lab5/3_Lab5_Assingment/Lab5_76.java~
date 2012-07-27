/*******************************************************************************
*  CS401 Lab5                                                                  *
*  File : Lab5_76.java                                                         *
*   By hoypothesize, as itr.add will added                                     *
*   the elements where is has visited the                                      *
*   list, so all of the messages would be                                      *
*    legal. But after testing this hypothesize                                 *
*    all of message is throwing below error                                    *
*  Exception in thread "main" java.lang.IllegalStateException                  *
*	at java.util.LinkedList$ListItr.remove(LinkedList.java:751)            *
*  Auther: Anand Singh                                                         *
*  CWID  : A20280101                                                           *
*  Email : asingh83@hawk.iit.edu                                               *
*  Date  : 17-feb-2012                                                         *
********************************************************************************/
import java.util.*;
import java.io.*;

public class Lab5_76
{

	public static void main (String[] args)
	{
		new Lab5_76().run();
	}

	public void run()
	{
		iterate_a();
		iterate_b();
		iterate_c();
		iterate_d();
		iterate_e();
		iterate_f();
		
	}

	public void iterate_a()
	{
		LinkedList<Double> weights = new LinkedList<Double>();
		
		ListIterator<Double> itr;
     		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		
		itr.add(8.8);
		itr.next();
		itr.remove();

		itr = weights.listIterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().toString());
		}

				
	}

	public void iterate_b()
	{
		LinkedList<Double> weights = new LinkedList<Double>();
		
		ListIterator<Double> itr;
     		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		
		itr.add(8.8);
		itr.remove();
		itr.next();
		
		itr = weights.listIterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().toString());
		}

	}

	public void iterate_c()
	{
		LinkedList<Double> weights = new LinkedList<Double>();
		
		ListIterator<Double> itr;
     		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();

		itr.next();
		itr.add(8.8);
		itr.remove();
		
		
		itr = weights.listIterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().toString());
		}


		
	}

	public void iterate_d()
	{
		LinkedList<Double> weights = new LinkedList<Double>();
		
		ListIterator<Double> itr;
     		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();

		itr.next();
		itr.remove();
		itr.add(8.8);
		
		itr = weights.listIterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().toString());
		}

		
	}

	public void iterate_e()
	{
		LinkedList<Double> weights = new LinkedList<Double>();
		
		ListIterator<Double> itr;
     		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();

		itr.remove();
		itr.add(8.8);
		itr.next();	

		itr = weights.listIterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().toString());
		}

	}

	public void iterate_f()
	{
		LinkedList<Double> weights = new LinkedList<Double>();
		
		ListIterator<Double> itr;
     		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		
		itr.remove();
		itr.next();		
		itr.add(8.8);	
		
		itr = weights.listIterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next().toString());
		}

	}
} 

