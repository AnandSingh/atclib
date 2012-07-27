import java.util.*;

public class SinglyLinkedList<E> extends AbstractCollection<E> 
  					   implements List<E>
{
  protected Entry<E> head;
  
  
  /**
   * Initializes this SinglyLinkedList object to be empty, with elements to be of 
   * type E.
   */
  public SinglyLinkedList()
  {
    head = null;
  } // constructor
  
  
  /**
   *  Determines if this SinglyLinkedList object has no elements.
   *
   *  @return true -  if this SinglyLinkedList object has no elements; otherwise,
   *                          false.  
   */
  public boolean isEmpty ()  
  {
    return head == null;
  } // method isEmpty
  
  
  /**
   *  Adds a specified element to the front of this SinglyLinkedList object.
   *
   *  @param e - the element to be prepended.
   *
   */
  public void addToFront (E e) 
  {
    Entry<E> temp = new Entry<E>();
    temp.element = e;
    temp.next = head;
    head = temp;
  } // method addToFront
  
  
  /**
   *  Returns a SinglyLinkedListIterator object to iterate over this
   *  SinglyLinkedList object.
   *
   */  
  public Iterator<E> iterator()
  {
    return new SinglyLinkedListIterator();
  } // method iterator
  
  
  /**  
   *  Determines the number of elements in this SinglyLinkedList object.
   *  The worstTime(n) is O(n).
   *
   *  @return the number of elements.
   */
  public int size() 
  {
    int count = 0;
    
    for (Entry<E> current = head; current != null; current = current.next)
      count++;
    return count;
  } // method size
  
  
  /** 
   *  Determines if this SinglyLinkedList object contains a specified element.
   *  The worstTime(n) is O(n).
   *
   *  @param element - the specified element being sought.
   *
   *  @return true - if this SinglyLinkedList object contains element; otherwise,
   *                false. 
   *
   */
  public boolean contains (Object element) 
  {
    if (element == null)
    {
      for (Entry<E> current = head; current != null; current = current.next)
        if (current.element == null)
          return true;
    } // if element == null
    else   
      for (Entry<E> current = head; current != null; current = current.next)
        if (element.equals (current.element))
          return true;
    return false;
  } // method contains
  

  public ListIterator<E> listIterator(int index)
  {
    throw new UnsupportedOperationException( );
  }  
 
  public ListIterator<E> listIterator()
  {
    throw new UnsupportedOperationException( );
  }

  public int lastIndexOf(Object o)
  {
    throw new UnsupportedOperationException( );
  }

  public int indexOf(Object o)
  {
    throw new UnsupportedOperationException( );
  }

  public E remove(int index)
  {
    throw new UnsupportedOperationException( );
  }

  public void add(int index, E element)
  {
    throw new UnsupportedOperationException( );
  } 

  public E set(int index, E element)
  {
    throw new UnsupportedOperationException( );
  }

  public E get(int index)
  {
    throw new UnsupportedOperationException( );
  }

/******************************************************************************************************/
/******************************************************************************************************/
  /*
   * Implemented for CS401 Lab5 Assignment
   * Author : Anand Singh
   * email  : asingh83@hawk.iit.edu
   8 CWID   : A20280101
   */
  public boolean addAll(Collection<? extends E> c)
  {
    
    if(c == null)
	throw new NullPointerException();
   
    Iterator<? extends E> itr = c.iterator();
    // head and tail for the list (generated from Collection) to be apended to existing list and 
    
    Entry<? extends E> addAll_head = new Entry<E>();
    Entry<? extends E> addAll_tail = new Entry<E>();
    addAll_head = addAll_tail = null;

    // iterate to the end of the existing list
    Entry<? extends E> list = this.head;
    while(list.next != null)
	list = list.next;

    // iterate through the collection and form a link list so that that it can be added to the existing list
    while (itr.hasNext())
    {
	Entry<? extends E> en = new Entry<E>();
        en = (Entry<? extends E>)itr.next();
	
 	if(addAll_head == null && addAll_tail == null)
	{
		addAll_head = en;
	}
	else
	{
		Entry<? extends E> tmp = addAll_tail.next; // typecasting tail.next to the <? extends E>
		tmp = en;
		addAll_tail = en;
	}
    }

    // apend the new list to the existing list;
    Entry<? extends E> tmp = list.next; // typecasting list. next to the <? extends E>
    tmp = addAll_head;
    return true;
  } 

/******************************************************************************************************/
/******************************************************************************************************/
  public boolean addAll(int index, Collection<? extends E> c)
  {
    throw new UnsupportedOperationException( );
  }

  public Object[] toArray() 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public <E>E[] toArray(E[] a)
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public boolean remove(Object o) 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public boolean containsAll(Collection<?> c) 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  
  public boolean removeAll(Collection<?> c) 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public boolean retainAll(Collection<?> c) 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public List<E> subList(int fromIndex, int toIndex)
  { 
    throw new UnsupportedOperationException( ); 
  }
  public void clear() 
  {
    head = null; 
  }
  
  public boolean equals(Object obj) 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public int hashCode() 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  
  protected class SinglyLinkedListIterator implements Iterator<E> 
  {
    protected Entry<E> next;
    
    /**
     *  The iterator has been initialized.
     */
    protected SinglyLinkedListIterator() 
    {
      next = head;
    } // constructor
    
    
    /** 
     *  Returns the element this Iterator object was (before this call) 
     *  positioned at, and advances this Iterator object.
     *                    
     *  @return - the element this Iterator object was positioned at.
     *
     *  @throws NoSuchElementException if this Iterator object was
     *                 not postioned at an element before this call.
     */                            
    public E next() 
    {
      E theElement = next.element;
      next = next.next;
      return theElement;
    } // method next
    
    
    /**
     *  Determines if this Iterator object is positioned at an element in this
     *  Collection.
     *
     *  @return true - if this Iterator object is positioned at an element; 
     *                otherwise, false.                        
     */                   
    public boolean hasNext() 
    {       
      return next != null;
    } // method hasNext
    
    /**
     *  Removes the element returned by the most recent call to next().
     *  The behavior of this Iterator object is unspecified if the underlying 
     *  collection is modified ( while this iteration is in progress) other than 
     *   by calling this remove() method.
     *
     *  @throws IllegalStateException - if next() had not been called before
     *                 this call to remove(), or if there had been an intervening call 
     *                 to remove() between the most recent call to next() and this 
     *                 call.
     **/
    public void remove() 
    { 
      throw new UnsupportedOperationException( ); 
    }
    
  } // class SinglyLinkedListIterator
  
  
  protected class Entry<E>
  {
    protected E element;
    protected Entry<E> next;
    
  } // class Entry

} // class SinglyLinkedList
