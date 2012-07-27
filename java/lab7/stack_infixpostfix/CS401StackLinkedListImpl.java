/**********************************************
*  CS401 Lab Assignment 6                     *
*  File : CS401StackLinkedListImpl.java       *
*         Implemented the following methdso of*
*         this class :                        *                        
*          push				      *
*          pop                                *
*          isEmpty                            *
*          printStack                         *
*                                             *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 24-Feb-2012                        *
***********************************************/

import java.util.Iterator;

public class CS401StackLinkedListImpl<E> implements CS401StackInterface<E> 
{
   private LinkEntry<E> head;
   private int num_elements;

   public CS401StackLinkedListImpl()
   {
      head = null;
      num_elements = 0;
   }

  /*
   * add and element on the top of stack
   */
   public void push(E e)
   {
      /** Add code here **/
	LinkEntry<E> ne = new LinkEntry<E> ();
	ne.element = e;
	
	if(head == null)
	{
		 head = ne;
		 head.next = null;
	}
	else 
	{
		ne.next = head;
		head = ne;
	}
	num_elements++;
	return;
   }

  /*
   * Return the element from top of stack
   */
   public E pop()
   {
      /** Add code here **/
	if(head != null)
	{
		LinkEntry<E> tmp = head;
		head = tmp.next;
		num_elements--;
		return tmp.element;
	}else
	{
		throw new UnsupportedOperationException();
	}
   }

  /*
   * Checks if the stack is empty 
   */
  public boolean isEmpty()
  {
	  if(head == null)
		  return true;
	  else
		  return false;
  }

  /*
   *  Iterate throgh the stack and prints the element of stack 
   */
  public void printStack()
  {
	  LinkEntry<E> tmp = head;
	  while(tmp != null)
	  {
		  System.out.print(tmp.element+" ");
		  tmp = tmp.next;
	  }
  }
   public int size()
   {
      /** Add code here **/
      return num_elements;      
   }

   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<E>
   {
      protected E element;
      protected LinkEntry<E> next;

      protected LinkEntry() { element = null; next = null; }
   }
} /* CS401StackLinkedListImpl<E> */
