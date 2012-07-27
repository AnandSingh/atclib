
import java.util.*;
public class test {

	public final static int SEED = 500;
//	final static int MAX = 100;

	public final static long NANO_FACTOR = 1000000000;

       // public long startTime, finishTime, elapsedTeime;

	public static void main(String[] args)
        {
            System.out.print("Hello world\n");
	    int n = 10;
	    Random random = new Random (SEED);
            long startTime = 0, finishTime = 0, elapsedTime = 0;
            final int MAX = 100;
	    Integer intArray[] = new Integer[n];
	    for(int i =0; i < n; i++)
	    {
		intArray[i] = new Integer (random.nextInt(MAX));
		System.out.println("" + i+". "+intArray[i]); 
	    }
	   
	    //finishTime = System.nanoTime();
            startTime = 885161724000;
		finishTime = 889961724000;
	    
	    elapsedTime = finishTime - startTime;
            System.out.println(""+(elapsedTime / NANO_FACTOR));
	}
}
