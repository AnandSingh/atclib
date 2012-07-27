public interface CS401CollectionInterface<E>
{

	/**
	 * Deterine if this data structure is at its capacity
	 */
	public boolean is_full();
	
	/**
	 * Determine if this data structure is empty
	 */
	public boolean is_empty();


	/**
	 * Determine the number of elements in this data structure 
	 */
	public int size();


	/**
	 * Add a new element at the specified index 
	 */
	public boolean add(int index, E e);


	/**
	 * Add a new element at the end of the data structure. 
	 */
	public boolean add(E e);

	/**
	 * Remove the element at specified index
	 */
	public E remove(int index);

	/**
	 * Remove the visited element
	 */
	public E remove();

	/**
	 * Get the element at specified index
	 */
	public E get(int index);

	/**
	 * Determine if the element is contained in this list
	 */
	public boolean contains(E e);

}
