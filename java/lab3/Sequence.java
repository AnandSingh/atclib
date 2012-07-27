/**********************************************
*  CS401 Lab Assignment 3                     *
*  File : Sequence.java                       *
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
** Sequence class 
*/
public class Sequence<E> {

	protected E [] data;
	protected int size;  // 
	protected int arrLimit;
	
	public static void main(String [] args)
	{
                //new Sequence<E>().run();
		System.out.println("Hello world !!");
	}

	/**
	 * method for program execution
	 */
	public void run()
	{
		System.out.println("Hello world !!");
	}
        
	/**
         * Initilizes this sequence object to be empty, with an initial capacity of ten
         * elements
         */
	public Sequence()
	{
		data = (E[])new Object[10];
		arrLimit = 10;
	 	size = 0;		
	}
        
	/**
         * Initilizes this sequence object to be empty, with a specified initial capacity
         */
	public Sequence (int n)
	{
		data = (E[])new Object[n];
		arrLimit = n;
		size = 0;
	}
        
	/**
         * Returns the number of element in this Sequence object
         */
	public int size()
	{
		return size;
	}
       
	 /**
         * Appends a specified element to this Sequence object
         */
	public void append (E element)
	{
		// check if array is currently full, then increase the capacity
		if(size == arrLimit) {
			E[] tmp = (E[])new Object[arrLimit+10]; // 
			System.arraycopy(data, 0, tmp, 0, arrLimit);
			data = tmp; 
		}
		if(size < arrLimit) {
			data[size] = element;
			size++;
		}
		
	}

	/**
	 * Returns the element at a specified index in this Sequence object.
	 */
	public E get (int k)
	{
		if(k < size) {
			return data[k];	
		} else {
			throw new IllegalArgumentException ("Entered index "+k+" is greater then array size of "+size);
		}
	}

	/**
	 * Changes the element at a specified index in this Sequence Object
	 */
	public void set (int k, E newElement)
	{
		if(k < size) {
			data[k] = newElement;
		}
		else {
			throw new IllegalArgumentException ("Entered index "+k+" is greater then array size of "+size);
		}
		
	}

}

