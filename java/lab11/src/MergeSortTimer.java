import java.util.*;

public class MergeSortTimer
{ 
    public MergeSort m = new MergeSort();	
    public static void main (String[] args)
    {
        MergeSortTimer ms = new MergeSortTimer();
	
	while(true)
	{
        	Scanner sc = new Scanner (System.in);
	        System.out.print ("Enter y to continue: ");
		String l = sc.nextLine();
		if((l.compareTo("y") == 0)|| ( l.compareTo("Y") == 0))
		{	
			ms.run();
		}else{
			System.out.println("Exiting ...");
			return;
		}
	}
    } // method main  

    /**
     * Estimates the run-time, on average, for a sort method.
     */
    public void run() 
    {    
        final String TIME_MESSAGE = "\n\nThe elapsed time in seconds is ";

        final int SENTINEL = -1;
    
        final String INPUT_PROMPT = 
            "\nPlease enter the number of integers to be sorted: ";      

        final String POSITIVE_INT_NEEDED =
            ": The input must be a positive integer.";

        final long MAX_TIME = 1000000000000L;

	final int SEED = 100,
                  TRIALS = 5;
    
        final double NANO_FACTOR = 1000000000.0;  // nanoseconds per second    

        Scanner sc = new Scanner (System.in);

        Random r = new Random (SEED);
        
        long startTime,
             finishTime,
             elapsedTime,
             minElapsedTime;

        int n;
    
        try
        {
            System.out.print (INPUT_PROMPT);
            n = sc.nextInt();
                
            if (n <= 0)
                throw new Exception (POSITIVE_INT_NEEDED);        
            minElapsedTime = MAX_TIME;
               
            Integer [] a = new Integer [n];
                           
            for (int i = 0; i < TRIALS; i++) 
            {                         
                for (int j = 0; j < n; j++)
                    a [j] = r.nextInt (n);
                //System.out.println ("\nBefore Sorting: " + Arrays.toString (a)); 
                                                    
                startTime = System.nanoTime();
               
	        m.mergeSort(a);	
                
                finishTime = System.nanoTime();
                //System.out.println ("After Sorting : " + Arrays.toString (a));
                elapsedTime = finishTime - startTime;
                if (elapsedTime < minElapsedTime)
                    minElapsedTime = elapsedTime;
            } // for i
            System.out.println (TIME_MESSAGE + 
                                (minElapsedTime / NANO_FACTOR));
        } // try
        catch (Exception e)
        {
            System.out.println (e);
            sc.nextLine();
        }//catch Exception                         
    } // method run
   
} // class SortTimer
