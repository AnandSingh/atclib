public class CS401FairPQTest 
{

   
   public static void main(String[] args)
   {
	   new CS401FairPQTest().run();
   }

   public void run()
   {
       
      	   CS401FairPQ<Student> pq = new CS401FairPQ<Student>();
	   Student std1 = new Student ("Soumya", 3.4);
	   Student std2 = new Student ("Navdeep", 3.4);
	   Student std3 = new Student ("Viet", 3.4);
	

	   pq.add(std1);
	   pq.add(std2);
	   pq.add(std3);
		
	   System.out.println(pq);

       /*
      Integer[] data = {91, 101, 67, 12, 88, 78, 90, 101, 66, 56, 100};

      CS401PriorityQueue<Integer> pq = new CS401PriorityQueue<Integer>();

      for (int i = 0; i < data.length; i++)
      {
         pq.add(data[i]);
      }
 
      System.out.println("Added data to a MIN HEAP.  Heap is:");
      System.out.println(pq);

      System.out.println("Removing elements from heap:");
      System.out.println(" Removed: " + pq.remove());
      System.out.println("Heap is now:");
      System.out.println(pq);
      System.out.println(" Removed: " + pq.remove());
      System.out.println(" Removed: " + pq.remove());
      System.out.println(" Removed: " + pq.remove());
      System.out.println(" Removed: " + pq.remove());

      System.out.println("Remaining elements in heap are:");
      System.out.println(pq);
      */
   }
}
