import java.util.*;

public class HeapSort
{
    protected Object[ ] queue;
    
    protected int size = 0;

    protected final Comparator comparator;

    public HeapSort (Comparator comp)
    {
        comparator = comp;
    } // one-parameter constructor

    public HeapSort()
    {
        this (null);
    } // default constructor

    /**
     *  Sorts a specified array into the order given by the comparator in the 
     *  constructor (natural, that is, Comparable order if default constructor; 
     *  unnatural, that is, Comparator order if constructor specifies non-null
     *  comparator).
     *  The worstTime(n) is O(n log n) and worstSpace(n) is constant.   
     *
     *  @param a the array object to be sorted.
     *
     */   
    public void heapSort (Object[ ] a) 
    {
        queue = a;
        int length = queue.length;
        size = length;
        
        // Convert queue into a heap:
        for (int i = (size >> 1) - 1; i >= 0; i--)        
            siftDown (i, queue [i]); 
          
        Object x;
        for (int i = 0; i < length; i++)
        {                     
            x = queue [--size];
            queue [size] = queue [0];            
            siftDown (0, x);
        } 
        for (int i = 0; i < length / 2; i++)
        {
            x = queue [i];
            queue [i] = queue [length - i - 1];
            queue [length - i - 1] = x;
        }         
    } // method heapSort
    
     protected void siftDown(int k, Object x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    protected void siftDownComparable(int k, Object x) {
        Comparable key = (Comparable)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Comparable) c).compareTo(queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo(c) <= 0)
                break;
            queue[k] = c;
            k = child;
        } // while
        queue[k] = key;
    } // method siftDownComparable
    
    protected void siftDownUsingComparator(int k, Object x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                comparator.compare(c, queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, c) <= 0)
                break;
            queue[k] = c;
            k = child;
        } // while
        queue[k] = x;
    } // method siftDownUsingComparator
    
} // class HeapSort
