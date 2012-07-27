/**********************************************
*  CS401 Lab Assignment 8                     *
*  File : BinarySearchTree.java               *
*         Array based implementation for      *
*        Binary serach tree                   *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 28-Mar-2012                        *
***********************************************/

import java.util.*;


// Array based implementation for the binary search tree
// ArrayList is being to form tree, so that to not get involved 
// in managemnet of array size if the array reaches it's maximum boundary
// ArraList manages it size dynamically
// ArrayList method like add, get, set are used with the index to simulate
// array type behavoiur
public class BinarySearchTreeArray<E> {

	final static int NULL = -1;
	//private ArrayList<E> arrayTree;
	//protected TreeEntry<E> [] arrayTree;
	protected ArrayList<TreeEntry<E>> arrayTree;
	protected int root = NULL;
	protected int size = 0;

	/*
    	* Constructor
    	* */
        public BinarySearchTreeArray () {
		size = 0;
		root = NULL;
      		arrayTree = new ArrayList<TreeEntry<E>> ();
        }

	/**
	 * Binary tree node with following fileds
	 *  element : node data
	 *  left    : index for the left childre node
	 *  right   : index for the right children node
	 *  parent  : index for the parent node 
	 */
	protected static class TreeEntry<E>
	{
		protected E element;

		protected int left = NULL;
		protected int right = NULL;
		protected int parent;

		public TreeEntry()
		{

		}

		public TreeEntry (E element, int parent)
		{
			this.element = element;
			this.parent = parent;
			this.left = NULL;
			this.right = NULL;
		}

	}

	/**
	 * recursive add method to add an element in binary tree
	 */
	private boolean addElement(E element, int rootIdx)
	{
		TreeEntry root = arrayTree.get(rootIdx);
		//int comp = ((Comparable)element).compareTo(arrayTree[rootIdx].element);
		int comp = ((Comparable)element).compareTo(root.element);
		//System.out.println(root.element + " " + root.left + " " + root.right + " " + root.parent);
		//System.out.println(size + " " + comp + " " + rootIdx);
		
		//System.out.println("\n++++++++++++++++");
		//printTree();
		//System.out.println("\n----------------");
		if(comp == 0)
		{
			return false;
		}
		if(comp < 0)
		{
			//if(arrayTree[rootIdx].left != NULL)
			if(root.left != NULL)
				return addElement(element, root.left);
				//return addElement(element, arrayTree[rootIdx].left);
			else
			{
				//arrayTree[rootIdx].left = size
				//System.out.println("\t\t left:" + rootIdx + " " + size);
				root.left = size;
				arrayTree.set(rootIdx, root);
				TreeEntry<E> temp = new TreeEntry<E> (element, rootIdx);
				arrayTree.add(root.left, temp);
				//arrayTree[arrayTree[rootIdx].left] = new TreeEntry<E> (element, rootIdx);
				size++;
				return true;
			}

		}
		else if(comp > 0)
		{
			//if(arrayTree[rootIdx].right != NULL)
			if(root.right != NULL)
				return addElement(element, root.right);
				//return addElement(element, arrayTree[rootIdx].right);
			else
			{

			//	arrayTree[rootIdx].right= size;
			//	p
				//System.out.println("\t\t right:" + rootIdx + " " + size);
				root.right= size;
				arrayTree.set(rootIdx, root);
				TreeEntry<E> temp = new TreeEntry<E> (element, rootIdx);
				arrayTree.add(root.right, temp);
			//	arrayTree[arrayTree[rootIdx].right] = new TreeEntry<E> (element, rootIdx);
				size++;
				return true;
			}
		}
		return false;

	}

	/**
	 * Expose method to add an element to binary tree
	 */
	public boolean add(E element)
	{
	
		//System.out.println("\t\t***** add : " + element + " " + size + "*****");
		//printTree();
		//System.out.println("\n***************\n");

		if (root == NULL)
		{
			if(element == null)
			{
				System.out.println("Exception");
				throw new NullPointerException();
			}
			root = 0;
			TreeEntry<E> temp = new TreeEntry<E> (element, NULL);
			arrayTree.add(root, temp);
			//arrayTree[root] = new TreeEntry<E> (element, NULL);
			size++;
			return true;
		}else
		{
			addElement(element, root);
		}
		//System.out.println("\t\t EXIt");
		//printTree();
		return true;

	}

	/**
	 * return the current size of binary tree
	 */
	public int size()
	{
		return size;
	}

	/**
	 * expose method to delet an node from Binary tree
	 */
	public boolean remove(E element)
	{
		if (element == null)
			throw new NullPointerException();
		int i = 0;
		int tempIndex = NULL;
		TreeEntry temp = arrayTree.get(root);
		tempIndex = root;
		// search the element in tree
		while(i++ < size)
		{
			int comp = ((Comparable)element).compareTo(temp.element);
		        if(comp == 0){
				//System.out.println("break: " + tempIndex);
				break;
			}
			else if(comp < 0) {
				tempIndex = temp.left;
				temp = arrayTree.get(temp.left);
			}
			else if(comp > 0) {
				tempIndex = temp.right;
				temp = arrayTree.get(temp.right);
			}

		}
		//System.out.println("remove: " + tempIndex + ". " + temp.element + " " + temp.left + " " + temp.right + " " + temp.parent);
		if (tempIndex == NULL)
			return false;
		else
		{
			// temp is node which has to be deleted
			// check if has two children then replece it with a new node
			// if it has only one children node then replace with one node
			// if it's an leaf node then dlete the node itself
			
			if(temp.left != NULL && temp.right != NULL) {
				// it's has both children, 
				//System.out.println("remove: Two children");
				// replacement node is leftmost Entry in right subtree
				TreeEntry removeNode = temp;
				int removeNodeIdx = tempIndex;
				tempIndex = temp.right;
				temp = arrayTree.get(temp.right);
				//tempIndex = temp.right;
				while(temp.left != NULL)
				{
					tempIndex = temp.left;
					temp = arrayTree.get(temp.left);
				}
				//System.out.println("relacement node :" + "."+ tempIndex  + " " + temp.element + " " + temp.left + " " + temp.right + " " + temp.parent  );
				removeNode.element = temp.element;
				arrayTree.set(removeNodeIdx, removeNode);
				// the temp node will get deleted in the below
			}
			
		
			if((temp.left == NULL) && (temp.right == NULL))
			{
				System.out.println("remove: Leaf Node");
				// it's an leaf node
				TreeEntry parent = arrayTree.get(temp.parent);
				if(parent.left == tempIndex) {
				
					parent.left = NULL;
					arrayTree.set(temp.parent, parent);
					return true;
				}else if(parent.right == tempIndex) {
					parent.right = NULL;
					arrayTree.set(temp.parent, parent);
					return true;
				}else {
					return false;
				}
			}else
			{
				// it has only one children
				System.out.println("remove: one children");
				int replaceNodeIdx = NULL;
				if(temp.left != NULL)
					replaceNodeIdx = temp.left;
				else 
					replaceNodeIdx = temp.right;
				TreeEntry replaceNode = arrayTree.get(replaceNodeIdx);

				replaceNode.parent = temp.parent;
				//replacement.parent = p.parent;
				
				if(replaceNode.parent == NULL)
				{
					replaceNodeIdx = root;
				}else if(tempIndex == replaceNode.left)
				{
					replaceNode.left = replaceNodeIdx;

				}else if(tempIndex == replaceNode.right)
				{
					replaceNode.right = replaceNodeIdx;
				}
				//arrayTree.set(replaceNodeIdx,replaceNode);
				arrayTree.set(tempIndex, replaceNode);
				
				// update the remaining nodes in array
				// i.e. moving nodes one index above and updating the parent node 
				// left and right index
				for(int ii = (tempIndex+1); ii < size; ii++)
				{
					TreeEntry node = arrayTree.get(ii);
					TreeEntry nodeParent = arrayTree.get(node.parent);
					if(nodeParent.left == ii)
						nodeParent.left = ii - 1;
					else if(nodeParent.right == i)
						nodeParent.right = ii - 1;
					arrayTree.set(node.parent, nodeParent);
					arrayTree.set(ii - 1, node);
				}
				
			}

		}
		size--;
		System.out.println("Remove: " + element + " " + temp.element);
			return false;
	}

	/**
	 * return true if binary tree conatins the element else return false
	 */
	public boolean contains (E element)
	{
		int i = 0;

		TreeEntry temp = arrayTree.get(0);
		while(i++ < size)
		{
			int comp = ((Comparable)element).compareTo(temp.element);
			if(comp ==0)
			{
				return true;
			} else if( comp < 0)
			{
				temp = arrayTree.get(temp.left);
			}else if(comp > 0)
			{
				temp = arrayTree.get(temp.right);
			}

		}
		return false;

	}
	/**
	 * Print the tree as it's stored in an array 
	 */
	public void printTree()
	{
		System.out.println("\tSize of Tree :" + size);
		System.out.println("\t    | Element  |  parent  |  left |  right |" + size);

		for(int i =0 ;i< size; i++)
		{
			TreeEntry temp = arrayTree.get(i);
			System.out.print("\t"+i+". ");
			System.out.print(temp.element + "  ");
			System.out.print(temp.parent+ "  ");
			System.out.print(temp.left+ "  ");
			System.out.println(temp.right+"  ");
		}
		System.out.println();
		System.out.println();
	}


}
