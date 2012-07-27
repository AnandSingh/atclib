/**********************************************
*  CS401 Lab Assignment 12                    *
*  File : PrimAlgorithm.java      	      *
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

public class PrimAlgorithm
{
	/*

                          A
                          / \
                         / | \
                      6 /  |  \ 5
                       /   |   \
                      /    |    \
                     B     | 1   C 
                    | \    |    / |
                    |  \   |   /  |
                    |   \  |  /   |
                  3 |  5 \ | / 5  | 2
                    |     \|/     |
                    |      D      |
                    |     / \     |
                    +  6 /   \ 4  +
                    \   /     \  /
                     \ /       \/
                      E+-------+F
                           5

	*/

	static int graph[][] = {
		/* A   B   C   D   E   F */
		{ -1,  6,  5,  1, -1, -1 }, // A
		{  6, -1, -1,  5,  3, -1 }, // B
		{  5, -1, -1,  5, -1,  2 }, // C
		{  1,  5,  5, -1,  6,  4 }, // D
		{ -1,  3, -1,  6, -1,  5 }, // E
		{ -1, -1,  2,  4,  5, -1 }  // F
	};
	static String nodeLabel[] = {"A", "B", "C", "D", "E", "F" };
     
  	final static int MAX_NODE = 6;
        final static int YES = 1;
        final static int NO = 0;

	final static String INTRO = "\n ===== Begin Spanning Tree Prim =====\n";
	final static String PROMPT_1 = "\n\tEnter start vertex (q to Quit): ";
	final static String PROMPT_2 = "\n\t Bye Bye !!\n ";
	final static String PROMPT_3 = "\n\t Wrong Node !!\n ";
	final String TIME_MESSAGE = "\nThe elapsed time in seconds is ";


	//ArrayList<CS401Edge> added_edge_list; 

	CS401Graph g;// = new CS401Graph();

	final double NANO_FACTOR = 1000000000.0;  // nanoseconds per second 
	long startTime,
	     finishTime,
	     elapsedTime;
	public static void main(String[] args)
	{
		new PrimAlgorithm().run();
	}

	void printf(String line)
	{
		System.out.println(line);
	}
   	void run()
   	{
		System.out.println(INTRO);
		g = new CS401Graph();
		System.out.println(g);
		while(true) {
			// create an graph based on matrix array
			//g = new CS401Graph();
			//System.out.println(g);
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
				//System.out.print("From starting vertex "+ "\"start_node\"" + " the Depth First travesel : " );
				startTime = System.nanoTime();
				String result = PrimSpanningTree(start_node);
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

	public String PrimSpanningTree(String start_node)
	{
		// flags to track the traversed vetrexs
		int traversed_node_flags[] = { NO, NO, NO, NO, NO, NO, NO, NO, NO};
		EdgeSet<CS401Edge> edgeSet = new EdgeSet<CS401Edge>();
		edgeSet.addAllEdges(start_node);
		int total_cost = 0;
		String result = "";

		while(edgeSet.isEmpty() == false) {
			
			// get the edge which is at of priority Queue
			CS401Edge e = edgeSet.getBestCS401Edge();

			//check if First Vertex of edge is being visted or not
			String v1 = e.getFirstVertex();
			String v2 = e.getSecondVertex();
			if(edgeSet.checkAddedEdgeList(e) == false) {
				if(edgeSet.checkForCyclicGraph(v1, v2) == false)
				{
					result += "\t Add edge : " + e;// + "\n";
					//System.out.println("\t Add edge : "+ e);
					edgeSet.setAddedEdgeList(e, v1, v2);
					total_cost += e.getWeight();
					// add all the edges of second vertex into edge set
					edgeSet.addAllEdges(v2);
				}else{
					// discard the edge due to which cycle is formed in graph
					//System.out.println("\t\t discard cyclic edge :"+ e);
				}

			}else {
				//discard the duplicate edge
				//System.out.println("\t\t discard duplicate edge :"+ e);
			}
		}
		result += "\t Total cost : " + total_cost;// + "\n";
		return result;
	}

	public class EdgeSet<CS401Edge>
	{
		private PriorityQueue<CS401Edge> edge_set;
		private ArrayList<CS401Edge> added_edge_list; 
		private ArrayList<String> traverse_node_list;
						 /* A  B   C   D   E   F */
		//private String[] traverse_nodes = {NO, NO, NO, NO, NO, NO };

		public EdgeSet()
		{
			edge_set = new PriorityQueue<CS401Edge>();
			added_edge_list = new ArrayList<CS401Edge>();
			traverse_node_list = new ArrayList<String> ();
		}

		public boolean checkForCyclicGraph(String v1, String v2)
		{
			if(traverse_node_list.isEmpty() == true) {
				// no node is being traversed
				return false;
			} else if( (traverse_node_list.contains(v1) == true) && ( traverse_node_list.contains(v2) == true) ) {
				return true;
			} else {
				return false;
			}
		}

			//if(e.getFirstVertex())
			//ArrayList<String> traverse_nodes = new ArrayList<String> ();
			//CS401StackLinkedListImpl<String> stack = new CS401StackLinkedListImpl<String>();
			//Iterator<CS401Edge> itr = edge_set.iterator();
			//traverse_node.add(e.getFirstVertex());
			//traverse_node.add(e.getSecondVertex());
			//while (itr.hasNext())  {
			//      CS401Edge edge = (CS401Edge)itr.next();
			//}


		

		public void addAllEdges(String vertex)
		{
			g.setCurrentVertex(vertex);
			int edge_count = g.getVertexCS401EdgeCount(vertex);
			for(int i=0; i<edge_count; i++) {
				CS401Edge e = (CS401Edge) g.getVertexCS401Edge(vertex, i);
				add(e);
				//System.out.println("Queu Add: "+e);
			}
			//System.out.println("Queue : ");
			//System.out.println(edge_set);
		}
		
		private void add(CS401Edge e)
		{
			Iterator<CS401Edge> itr = edge_set.iterator();
			Boolean flag = false; 
			while (itr.hasNext())  {
			     CS401Edge edge = (CS401Edge)itr.next();
			     // check if this edge is already added in priority queue
			     if(edge.equals(e) == true) {
				     flag = true;
				     break;
			     }
			}
			if(flag == false && checkAddedEdgeList(e) == false)
				edge_set.add(e);

		}

		public CS401Edge getBestCS401Edge()
		{
			// get the edge which is at of priority Queue
			CS401Edge e = edge_set.peek();
			edge_set.remove(e);
			return e;
		}
	
		public void setAddedEdgeList(CS401Edge e, String v1, String v2)
		{
			if (e == null)
				return;
		       	added_edge_list.add(e);	
			// set the traversed node list
			traverse_node_list.add(v1);
			traverse_node_list.add(v2);
		}

		public boolean checkAddedEdgeList(CS401Edge e)
		{
			boolean flag = false;
			if (e == null)
				return flag;
			Iterator<CS401Edge> itr = added_edge_list.iterator();
			while (itr.hasNext())  {
			     CS401Edge edge = itr.next();
			     if (edge.equals(e) == true) {
				     flag = true;
				     break;
			     }
			}

			return flag;
		}

		public boolean isEmpty()
		{
			return edge_set.isEmpty();
		}
		public String toString()
		{
			Iterator<CS401Edge> itr = edge_set.iterator();
			String s = "";
			while (itr.hasNext())  {
			     CS401Edge edge = itr.next();
			     s += edge.toString();
			}
			return s;
			     // check if this edge is already added in priority queue
		}	

		
	}


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
						CS401Edge n = new CS401Edge(nodeLabel[i], nodeLabel[j], graph[i][j]);
						v[i].addCS401Edge(n);
					}
				}
	   		}
   		}

		public String toString()
		{
			String s = "";
			for(int i=0; i<MAX_NODE; i++)
			{
				s += v[i].toString();
			}
			return s;
		}
		public CS401Edge getVertexCS401Edge(String node, int i)
		{
			if((node.compareToIgnoreCase(current_vertex_label) != 0) && (current_vertex_index != -1) ) {
				setCurrentVertex(node);
			}
			return v[current_vertex_index].getCS401Edge(i);

		}
		/*
		public String getVertexNextBestNeighbour(String node)
		{
			if((node.compareToIgnoreCase(current_vertex_label) != 0) && (current_vertex_index != -1) ) {
				setCurrentVertex(node);
			}
			return v[current_vertex_index].getNextBestNeighbour();
		}
                */
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
		public int getVertexCS401EdgeCount(String node)
		{
			if((node.compareToIgnoreCase(current_vertex_label) != 0) && (current_vertex_index != -1) ) {
				setCurrentVertex(node);
			}
			return v[current_vertex_index].getCS401EdgeCount();
		}
	}






public class Vertex  
{
   private String label;
   private int label_idx;
   private int total_edge;
   private ArrayList<CS401Edge> edges;
   private boolean visited;

   public Vertex(String l, int i) 
   { 
      label = l;
      label_idx = i;
      edges = null;
      visited = false;
      total_edge = 0;
   }

   public void addCS401Edge(CS401Edge e)
   {
	if(edges == null)
		edges = new ArrayList<CS401Edge>();
	edges.add(e);
	total_edge++;
   }

   public CS401Edge getCS401Edge(int i)
   {
	   if (edges == null)
		   return null;
	   return edges.get(i);
   }

   public int getCS401EdgeCount()
   {
	   return total_edge;
   }

   /*
    * return the ttaol number of neighbours
    *
    * */
   public String get_label() { return label; }

   public String toString()
   {
      String s = "Vertex: " + label + "\n";
      
      s += "\tCS401Edges: \n";

      Iterator<CS401Edge> itr = edges.iterator();
      while (itr.hasNext())  {
         CS401Edge n = itr.next();
         s += "\t" +n;
      }

      return s;
   }

}


public class CS401Edge implements Comparable<CS401Edge>
{
	private String src;
	private String dst;
	private int weight;

	public CS401Edge(String s, String d, int w)
	{
		src = s;
		dst = d;
		weight = w;
	}	

	public int compareTo(CS401Edge e)
	{
		if(this.weight < e.weight)
			return -1;
		if(this.weight > e.weight)
			return 1;
		else
			return 0;
	}

	public boolean equals(Object o)
	{
		CS401Edge e = (CS401Edge)o;
		if((src.compareTo(e.getFirstVertex()) == 0) && (dst.compareTo(e.getSecondVertex()) == 0) )
			return true;
		if((src.compareTo(e.getSecondVertex()) == 0) && (dst.compareTo(e.getFirstVertex()) == 0))
			return true;
		//System.out.println("\t\t" + "[" +this.src + ","+ this.dst +"] [" + e.getSecondVertex() +", "+ e.getFirstVertex()+"]");
		return false;
		
	}	
	
	public String toString()
	{
		return "< " + src + ", " + dst + ", " + weight + " >\n";
	}
/*
	public boolean equals(CS401Edge e)
	{
		System.out.println( " :::::: " + e);

		return false;
	}
*/
	public String getFirstVertex()
	{
		return src;
	}
	public String getSecondVertex()
	{
		return dst;
	}
	public int getWeight()
	{
		return weight;
	}
	
}

}


/*
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
}*/
