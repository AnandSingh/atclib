/**********************************************
*  CS401 Lab Assignment 9                     *
*  File : CS401FairPQ.java                    *
*                                             *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 7-Apr-2012                         *
***********************************************/

import java.util.*;

public class CS401FairPQ<E>
{
	public static final int DEFAULT_INITIAL_CAPACITY = 11;
	// Sequence number to be appended to 
	// each element added in Fair  Priority Queue
	protected long sequence;

	private CS401PriorityQueue<E> pq;
	//Comparator<E> cp = new Natural<E>();
	
	public CS401FairPQ()
	{
		pq = new CS401PriorityQueue(DEFAULT_INITIAL_CAPACITY, new Natural<E>());
		sequence = 0;
	}

	public CS401FairPQ(int size, Comparator<E> comp)
	{
		pq = new CS401PriorityQueue(DEFAULT_INITIAL_CAPACITY, new Unnatural<E>(comp));
		sequence = 0;
	}

	/**
	 * add an element to the Fair Priority Queue
	 */
	public boolean add(E element)
	{
		// increment the sequence
		sequence++;
		// create element with sequence number
		SeqElement<E> sElement = new SeqElement<E>(element, sequence);
		return pq.add((E)sElement);

	}

	public E remove()
	{
		//sequence--;
		SeqElement<E> sElement = (SeqElement<E>) pq.remove();
		return (E)sElement.element;
	}

	public boolean isEmpty()
	{
		return pq.isEmpty();
	}

	public String toString()
	{
		return pq.toString();
	}


	/**
	 * Append the sequence number with the element 
	 */
	class SeqElement<E>
	{
		E element;
		long seq;

		public SeqElement(E element, long seq)
		{
			this.element = element;
			this.seq = seq;
		}

		public String toString()
		{
			String s = "";
			s += "[ " +element + " ]";
			return s;
		}
	}

	/**
	 * The natural class is used when elements are ordered "naturally"
	 * i.e according to the compareTo method
	 */
	class Natural<E> implements Comparator<E>
	{
	    public int compare (E e1, E e2)
	    {
	            int result = ((Comparable)(((SeqElement)e1).element)).compareTo(((SeqElement)e2).element);
	            if (result != 0)
	                      return result;
		    // compare with the sequence number 
	            return (int)(((SeqElement)e1).seq - ((SeqElement)e2).seq);
	     } 
        } 

	/**
	 * The Unnatural class used when the elements are ordered "unnaturally"
	 * i.e according to compare method
	 */
	class Unnatural<E> implements Comparator<E>
	{
		protected Comparator<E> comp;
		public Unnatural (Comparator<E> comp)
		{
			this.comp = comp;
			System.out.println(comp);
		} 
	        public int compare (E e1, E e2)
	        {
	 	         int result = comp.compare((E)(((SeqElement)e1).element), (E)(((SeqElement)e2).element));
		         if (result != 0)
		                  return result;
			 // compare with the sequence number
		         return (int)(((SeqElement)e1).seq - ((SeqElement)e2).seq);
		} 
	} 

}
