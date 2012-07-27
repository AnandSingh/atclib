/**********************************************
*  CS401 Lab Assignment 12                    *
*  File : DepthFirst.java      	              *
*        			              *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 28-Apr-2012                        *
***********************************************/

import java.util.*;
import java.io.*;
import java.util.PriorityQueue;
import java.util.Iterator;

public class DepthFirst
{
	/*                      18
	 * 	        B +---------------+ F
	 * 	       + +                 + +
	 * 	   12 /   \ 8             /   \
	 * 	     /     \             /     \2
	 * 	    +       +         8 /       +      10
	 * 	   E         A         /         C +--------+ H
	 * 	    +       +  +      /                      + 
	 * 	     \   12/    \10  /                      / 
	 * 	   24 \   /      \  /                      /2  
	 * 	       + +        + +                     /
	 * 	        G          D                     +
	 * 	                                        2
	 * 	        
	 */

	static int graph[][] = {
		/* A   B   C   D   E   F   G   H   I */
		{ -1,  8, -1, 10, -1, -1, 12, -1, -1 }, // A
		{  8, -1, -1, -1, 12, 18, -1, -1, -1 }, // B
		{ -1, -1, -1, -1, -1,  2, -1, 10, -1 }, // C
		{ 10, -1, -1, -1, -1,  8, -1, -1, -1 }, // D
		{ -1, 12, -1, -1, -1, -1, 24, -1, -1 }, // E
		{ -1, 18,  2, 10, -1, -1, -1, -1, -1 }, // F
		{ 12, -1, -1, -1, 24, -1, -1, -1, -1 }, // G
		{ -1, -1, 10, -1, -1, -1, -1, -1, -1 }, // H
		{ -1, -1, -1, -1, -1, -1, -1,  2, -1 }  // I
	};
	static String nodeLabel[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
     
  	final static int MAX_NODE = 9;
        final static int YES = 1;
        final static int NO = 0;

	final static String PROMPT_1 = "\nEnter starting vertex (q to Quit): ";
	final static String PROMPT_2 = "\n\t Bye Bye !!\n ";
	final static String PROMPT_3 = "\n\t Wrong Node !!\n ";
	final String TIME_MESSAGE = "\n\nThe elapsed time in seconds is ";


	CS401Graph g;// = new CS401Graph();

	final double NANO_FACTOR = 1000000000.0;  // nanoseconds per second 
	long startTime,
	     finishTime,
	     elapsedTime;
	public static void main(String[] args)
	{
		new DepthFirst().run();
	}

	void printf(String line)
	{
		System.out.println(line);
	}
   	void run()
   	{
		while(true) {
			// create an graph based on matrix array
			g = new CS401Graph();
      			System.out.print(PROMPT_1);
			Scanner sc = new Scanner (System.in);
			String start_node = sc.nextLine();

			if( start_node.compareToIgnoreCase("q") == 0 ) {
      				System.out.println(PROMPT_2);
				return;
			}

			if(validateNode(start_node) == false) {
				System.out.println(PROMPT_3);
			} else {
				System.out.print("From starting vertex "+ "\"start_node\"" + " the Depth First travesel : " );
				startTime = System.nanoTime();
				String result = DepthFirstTraversal(start_node);
				finishTime = System.nanoTime();
				elapsedTime = finishTime - startTime;
				System.out.println(result);
				System.out.println (TIME_MESSAGE + (elapsedTime / NANO_FACTOR));
			}
		}
   	}

	boolean validateNode(String node)
	{
		for(int i =0; i< MAX_NODE; i++) {
			if(node.compareToIgnoreCase(nodeLabel[i]) == 0)
				return true;
		}
		return false;

	}

	String DepthFirstTraversal(String start_node)
	{
		String result = new String();
		// if a node is tranvered then set the flag
		int traversed_node_flags[] = { NO, NO, NO, NO, NO, NO, NO, NO, NO};

		CS401StackLinkedListImpl<String> stack = new CS401StackLinkedListImpl<String>();
		stack.push(start_node);
		result += start_node + ", ";
		setTraversedFlag(traversed_node_flags, start_node);
	
		String node = start_node;
		while(stack.isEmpty() == false){
			g.setCurrentVertex(node);
			String next_node = g.getVertexNextBestNeighbour(node);
			//printf(">>>>> next_node: "+ next_node);
			if(next_node != null) {
				if(checkNodeTraversed(traversed_node_flags, next_node) == false) {
					stack.push(next_node);
					result += next_node + ", ";
					setTraversedFlag(traversed_node_flags, next_node);
					node = next_node;
				} else {

				}	
			} else{
				// all the neighbour is being tranversed
				node = stack.pop();
				if(node != null) {
					g.setCurrentVertex(node);
					if(g.getVertexNeighbourCount(node) > 0)
						stack.push(node);
				}
				//printf("\t Staic pop : " + node);	
			}
		

		}
		return result;
		
	}
	
	void setTraversedFlag(int flags[], String node)
	{
		for(int i=0; i<MAX_NODE; i++){

			if(nodeLabel[i].compareToIgnoreCase(node) == 0) {
				flags[i] = YES;
				return;
			}
		}
	}

	boolean checkNodeTraversed(int flags[], String node)
	{
		for(int i=0; i<MAX_NODE; i++) {
			if(node.compareToIgnoreCase(nodeLabel[i]) == 0) {
			       if(flags[i] == YES)
					return true;
			       else
				       return false;
			}
			
		}
		return false;
	}

/*
   static void createVertex()
   {
	   for(int i=0; i< MAX_NODE; i++) {
		
		v[i] = new Vertex(nodeLabel[i], i);
		
		for(int j=0; j < MAX_NODE; j++) {
			
			if(graph[i][j] != -1) {
				Neighbour n = new Neighbour(nodeLabel[i], nodeLabel[j], graph[i][j]);
				v[i].add_neighbour(n);
			}
		}
      		System.out.println(v[i]);

	   }
   }
   */


	class CS401Graph
	{

		private Vertex v[] = new Vertex[MAX_NODE];
		int current_vertex_index = -1;
		String current_vertex_label = "";
   		
		public CS401Graph()
   		{
	       
	       		for(int i=0; i< MAX_NODE; i++) {
			
				v[i] = new Vertex(nodeLabel[i], i);
		
				for(int j=0; j < MAX_NODE; j++) {
				
					if(graph[i][j] != -1) {
						Neighbour n = new Neighbour(nodeLabel[i], nodeLabel[j], graph[i][j]);
						v[i].add_neighbour(n);
					}
				}
	   		}
   		}

		public String getVertexNextBestNeighbour(String node)
		{
			if((node.compareToIgnoreCase(current_vertex_label) != 0) && (current_vertex_index != -1) ) {
				setCurrentVertex(node);
			}
			return v[current_vertex_index].getNextBestNeighbour();
		}

		public void setCurrentVertex(String node)
		{
			for(int i=0; i<MAX_NODE; i++) {
				if(node.compareToIgnoreCase(v[i].get_label()) == 0) {
					current_vertex_index = i;
					current_vertex_label = v[i].get_label();
					break;
				}
			}
		}
		public int getVertexNeighbourCount(String node)
		{
			if((node.compareToIgnoreCase(current_vertex_label) != 0) && (current_vertex_index != -1) ) {
				setCurrentVertex(node);
			}
			return v[current_vertex_index].getNeighbourCount();
		}
	}

}



class Vertex  
{
   private String label;
   private int label_idx;
   private int total_neighbour;
   private PriorityQueue<Neighbour> neighbours;
   private boolean visited;

   public Vertex(String l, int i) 
   { 
      label = l;
      label_idx = i;
      neighbours = null;
      visited = false;
      total_neighbour = 0;
   }

   public void add_neighbour(Neighbour n) 
   {
      if (neighbours == null)
          neighbours = new PriorityQueue<Neighbour>();
      neighbours.add(n);
      total_neighbour++;
   }

   public Neighbour remove_neighbour() 
   {
      Neighbour n = null;

      if (neighbours.size() > 0)  {
          n = neighbours.peek();
          neighbours.remove(n);
      	  total_neighbour--;
      }
      if ( n== null)
	      total_neighbour = 0;
      //System.out.println(n.label());   
      return n;
   }

   /*
    * return the ttaol number of neighbours
    * */
   public int getNeighbourCount()
   {
	   return total_neighbour;
   }

   public String getNextBestNeighbour()
   {
	   Neighbour neigh = remove_neighbour();
	   if(neigh != null)
		   return neigh.label();
	   else
		   return null;
   }

   public String get_label() { return label; }

   public String toString()
   {
      String s = "Vertex: " + label + "\n";
      
      s += " Neighbours: \n";

      Iterator<Neighbour> itr = neighbours.iterator();
      while (itr.hasNext())  {
         Neighbour n = itr.next();
         s += n;
      }

      return s;
   }
}

class Neighbour implements Comparable<Neighbour>
{
   private String source;
   private String destination;
   private Integer weight;

   public Neighbour(String s, String d, int w)
   {
      source = s; destination = d; weight = w;
   }

   public int compareTo(Neighbour o)
   {
      if (this.weight < o.weight) return -1;
      else if (this.weight > o.weight) return 1;
      else return 0;
   }

   public String label()
   {
	   return destination;
   }
   public String toString()
   {
      return "  " + destination + ", weight: " + weight + "\n";
   }
}
