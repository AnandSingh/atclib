import java.util.Iterator;

public class CS401LinkedListImpl<E> implements CS401CollectionInterface<E>,
						Iterable<E>
{
	private LinkEntry<E> head;
	private LinkEntry<E> tail;  // added by anand
	private int s = 1;

	public CS401LinkedListImpl()
	{	
		head = tail = null;
	}
	
	public boolean is_empty()
	{
		if(head == null)
			return true;

		return false;
	}

	public boolean is_full() { return false;}

	public int size()
	{
		return size_r(head) - 1;
		/**
		 * Iterative solution
		 *
		 * LinkEntry<E> temp;
		 * int i = 0;
		 * for (temp = head; temp != null; temp = temp.next)
		 * 	i++;
		 *  return i;
		 */
	}

	public boolean add(int index, E e)
	{
		throw new UnsupportedOperationException();
	}
	
	public boolean add(E e)
	{
		LinkEntry<E> ne = new LinkEntry<E> ();
		ne.element = e;

		if(head == null && tail == null)
		{
			head = tail = ne;			
		}
		else
		{
			tail.next = ne;
			tail = ne;
		}
		return true;
	}


	public boolean add_sorted(E e)
	{
		LinkEntry<E> ne = new LinkEntry<E>();
		ne.element = e;

		if(head ==null && tail ==null)
		{
			head = tail = ne;
		}
		else
		{
			LinkEntry<E> prev = null;
			LinkEntry<E> temp;

			for (temp = head; temp != null; temp = temp.next)
			{
				int comp = ((Comparable)e).compareTo(temp.next);
				if(comp < 0)
				{
					break;
				}
				prev = temp;
			}
			
			if(prev == null)
			{
				ne.next = head;
				head = ne;
			}
			else if (temp == null)
			{
				tail.next = ne;
				tail = ne;
			}
			else 
			{
				ne.next = prev.next;
				prev.next = ne;
			}

		}
		return true;
	}

	public E remove(int index)
	{
		throw new IndexOutOfBoundsException();
	}

	public E remove()
	{
		throw new UnsupportedOperationException();
	}
	
	public E get(int index)
	{
		throw new UnsupportedOperationException();
	}
	
	public boolean contains(E e)
	{
		throw new UnsupportedOperationException();
	}
	

	public Iterator<E> iterator()
	{
		return new CS401LinkedListIterator();
	}

	private int size_r(LinkEntry<E> head)
	{
		if(head != null)
			s = s + size_r(head.next);
		return s;
	}

	/************************************************************/
	/* Inner Class */
	protected class LinkEntry<E>
	{
		protected E element;
		protected LinkEntry<E> next;
	}
	/************************************************************/

	protected class CS401LinkedListIterator implements Iterator<E>
	{
		protected LinkEntry<E> next;
		protected LinkEntry<E> prev;

		protected CS401LinkedListIterator() { next = head; prev = null; }

		public E next()
		{
			E e = next.element;
                        prev = next;
			next = next.next;
			return e;
		}
		
		public boolean hasNext() { return next != null; }

/**********************************************************************************************/
/**********************************************************************************************/
		/*remove the element under the iterator*/
                /*
		 * Implemented for the CS201 Lab5 Assingment
                 * Author : Anand Singh
		 * email  : asingh83@hawk.iit.edu
		 * CWID   : A20280101 
  		 */
		public void remove()
		{
			if (next == head)		
			{
				next = next.next;
				head = next;
			}
			else if(next == tail)
			{
				prev.next = null;
				next = null;
			}
			else
			{	
				LinkEntry<E> temp = next.next;
				prev.next = temp;
				next = temp;
			}
			return;
		}
	}
/**********************************************************************************************/
/**********************************************************************************************/

}
