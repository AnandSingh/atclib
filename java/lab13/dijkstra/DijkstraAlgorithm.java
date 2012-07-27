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

public class DijkstraAlgorithm
{

	int graph[][];
	String nodeLabel[];
	String delims = "[,]";
	//static String nodeLabel[] = {"A", "B", "C", "D", "E", "F" };
     
  	int MAX_NODE = -1;
        final static int YES = 1;
        final static int NO = 0;

	final static String INTRO = "\n ===== Begin Dijkstra's Algrorith =====\n";
	final static String FILE_INPUT_PROMPT = "\n Enter Path of Graph File (*** to Exit) :";
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
		new DijkstraAlgorithm().run();
	}

	void printf(String line)
	{
		System.out.println(line);
	}
   	void run()
   	{
		System.out.println(INTRO);
		System.out.print(FILE_INPUT_PROMPT);
		// Take the input File from user
                Scanner s = new Scanner (System.in);

                String graphFileName = s.nextLine();
                if(graphFileName.compareTo("***") == 0)
                {
                      //exit from main program
                      System.out.println(PROMPT_2);
                      return;
                }
		// Print the content of the file 
                        try{
                                // Open the file  
                                FileInputStream fstream = new FileInputStream(graphFileName);
                                // Get the object of DataInputStream
                                DataInputStream in = new DataInputStream(fstream);
                                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                String strLine;
                                System.out.println("\n" + graphFileName + " contents :");
				// read first Line
                                //Read File Line By Line
				boolean first_line = false;
				int line = 0;
                                while ((strLine = br.readLine()) != null) {
					if(first_line == false) {
						nodeLabel = strLine.split(delims);
						System.out.print("Vertex in graph :");
						graph = new int[nodeLabel.length][];
						for( int i=0; i< nodeLabel.length; i++) {
							graph[i] = new int[nodeLabel.length]; 
        	                                	System.out.print(nodeLabel[i] + " ");
						}
        	                               	System.out.println("\n");
						MAX_NODE = nodeLabel.length;
						first_line = true;
					}else{
						
						String temp[] = strLine.split(delims);
						for(int i =0; i<temp.length; i++) {
							graph[line][i] = Integer.valueOf(temp[i]);
						}
						line++;
					}
                                }
                                //Close the input stream
                                in.close();
                                //break;
                        }catch (Exception e){//Catch exception if any
                                System.err.println("Error: graph file not proper : " + e.getMessage());
                        }


		// create an graph based on matrix array
		g = new CS401Graph();
		System.out.println(g);
		while(true) {
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
				String result = DijkstraAlgo(start_node);
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

	public String DijkstraAlgo(String start_node)
	{
		// flags to track the traversed vetrexs
		PriorityQueue<minEdgeWeight> edge_set = null; // = new PriorityQueue<minEdgeWeight>();
		int d[] = new int[MAX_NODE];
		String p[] = new String[MAX_NODE];
		String pp[] = new String[MAX_NODE];
		ArrayList<String> S = new ArrayList<String>();
		ArrayList<String> VS = new ArrayList<String>();
		String res = "";
		res = "Start Vertex : " + start_node + "\n";
		for(int i =0; i<MAX_NODE; i++) {
			d[i] = -1;
		}

		S.add(start_node);
		for(int i=0; i<MAX_NODE-1;i++){
			if(nodeLabel[i].compareTo(start_node) != 0)
				VS.add(nodeLabel[i]);
		}
		//System.out.println(S +" : " + VS);

		int path_weigth = 0;
		int weigth_sum = 0;
		String next_vertex = null;
		for(int i =0; i<MAX_NODE -1; i++)
		{
			// set p[v] to s
			if(edge_set != null)
			{
				// not so good way to dealing with priority queue
				// in worst case it will give bad performance
				while(true) {
					minEdgeWeight temp = edge_set.peek();
					edge_set.remove(temp);
					int flag =0;
					for(int ii =0; ii<i; ii++) {
						if(p[ii].compareTo(temp.getVertex()) == 0) {
							flag = 1;
							break;
						}
					}

					if(flag == 0){
						S.add(temp.getVertex());
						weigth_sum = temp.getWeigth();
						int idx = getNodeIndex(temp.getVertex());
						pp[idx] = temp.getSource();
						break;
					}
				}
			}

			p[i] = S.get(i);
			System.out.println("\t START: " + p[i] + ", " + weigth_sum);

			g.setCurrentVertex(p[i]);
			int edge_count = g.getVertexCS401EdgeCount(p[i]);
			path_weigth = 0;
			for(int j=0; j<edge_count; j++) {
			       	CS401Edge e = (CS401Edge) g.getVertexCS401Edge(p[i], j);
				
				int idx = getNodeIndex(e.getSecondVertex());
				if((d[idx] == -1) || (d[idx] > (weigth_sum + e.getWeight()))){
					d[idx] = weigth_sum + e.getWeight();
				}
				
				
				if(path_weigth == 0) {
					path_weigth = e.getWeight() + weigth_sum;
				}else if((e.getWeight() + weigth_sum) < path_weigth )
				{
					path_weigth = e.getWeight() + weigth_sum;
				}
				//path_weigth = e.getWeight() + weigth_sum;
				if(edge_set == null)
					edge_set = new PriorityQueue<minEdgeWeight>();
				// not so good way blindly adding to the priority queue
				// need to optimize
				minEdgeWeight temp = new minEdgeWeight(e.getSecondVertex(), e.getWeight() + weigth_sum, e.getFirstVertex() );
				edge_set.add(temp);

			}
			System.out.println("Queue: "+ edge_set);
		}
		System.out.print("\tD: ");
		for(int k=0; k< MAX_NODE; k++) {
			System.out.print(d[k]+" ");
		}
		System.out.println("");
		System.out.print("\tP: ");
		for(int k=0; k< MAX_NODE; k++) {
			System.out.print( pp[k]+ " ");
		}
		System.out.println("");
		int t=0;
		for(int k =1; k<MAX_NODE; k++) {
			t=0;
			res += "\t" + start_node + " --> " +nodeLabel[k] +" : Cost is ";
			res += d[k] + ", Path is " + start_node + "->" +pp[k+1];
			String temp = pp[k];
			/*
			while(temp.compareTo(start_node) != 0) {
				int idx = getNodeIndex(temp);
				temp = pp[idx];
				res += "-->" + temp;
			}*/
			res += "\n";
			//while(temp[t-1].compareTo(start_node) != 0){

			//	temp[t++] = p[idx];
			//}
		}

		return res;
	}


	public int getNodeIndex(String v)
	{
		for(int i=0; i<nodeLabel.length; i++)
		{
			if(nodeLabel[i].compareTo(v) == 0)
				return i;
		}
		return -1;
	}

	public class minEdgeWeight implements Comparable<minEdgeWeight>
	{
		private String p; //precdence node
		private int w; // cummunicltive weight
		private String s; // source node
		public minEdgeWeight(String p, int w, String s)
		{
			this.p = p;
			this.w = w;
			this.s = s;
		}

		public int compareTo(minEdgeWeight ew)
		{
			if(this.w < ew.w)
				return -1;
			else if(this.w > ew.w)
				return 1;
			else 
				return 0;
		}
		public String getVertex()
		{
			return p;
		}
		public int getWeigth()
		{
			return w;
		}

		public String getSource()
		{
			return s;
		}
		public String toString()
		{
			String str = "";
			str += w +"/" + p + "/" + s;
			return str;
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
			//	System.out.println(i+". " + v[i].toString());
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
      
      s += "\tEdges: \n";
      if(edges != null) {
      	Iterator<CS401Edge> itr = edges.iterator();
      	while (itr.hasNext())  {
         CS401Edge n = itr.next();
        	 s += "\t" +n;
      	}
      }else
      {
	      s += "\n";
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
