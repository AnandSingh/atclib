/**********************************************
*  CS401 Lab Assignment 7                     *
*  File : CarWashUser.java                 *
*         Assignment for programming          *
*         project 8.2, pag                    *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 8-Mar-2012                        *
***********************************************/

import java.util.*;

public class CarWashUser
{
    public static void main (String[] args)
    {
        new CarWashUser().run();
    } // method main
    
    
    /**
     *  Reads in all of the arrival times, runs the simulation, and calculates the average 
     *  waiting time.
     *
     */ 
    public void run()
    {        
       // final int SENTINEL = 999;
	
	final String INPUT_PROMPT_1 = "\nPlease Enter mean Arrival Time: ";
	final String INPUT_PROMPT_2 = "\nPlease Enter mean Service Time: ";
	final String INPUT_PROMPT_3 = "\nPlease Enter maximum Arrival Time: ";
	final String INPUT_PROMPT_4 = "\nTo start simulation from begnining [Y/N]";
	final String ERROR_MESS_1   = "ERROR!! Enter 'Y' to continue, 'N' to exit";
	final String ERROR_MESS_2   = "\n ERROR!! Wrong ";

       // final String INPUT_PROMPT = "\nPlease enter three positive number ival time (or " + 
       //              SENTINEL + " to quit): ";           

        final String OUT_OF_RANGE = "The input must consist of a non-" +
                                    "negative integer less than the sentinel.";

        //CarWash carWash = new CarWash();
     
        Scanner sc = new Scanner (System.in);
          
       // int nextArrivalTime;
	int meanArrivalTime;
	int meanServiceTime;
	int maxArrivalTime;

        while (true)
        {
		try
		{
            		System.out.print (INPUT_PROMPT_1);
        		meanArrivalTime = sc.nextInt(); 
			System.out.print (INPUT_PROMPT_2);
			meanServiceTime = sc.nextInt();
            		System.out.print (INPUT_PROMPT_3);
        		maxArrivalTime = sc.nextInt(); 
			int ret = checkUserInput(meanArrivalTime, meanServiceTime, maxArrivalTime);
			if(0 == ret)
			{
	
        			CarWash carWash = new CarWash(meanArrivalTime, meanServiceTime, maxArrivalTime);
				carWash.Process();
				printResults(carWash);
			}else{
				System.out.print(ERROR_MESS_2+meanArrivalTime+meanServiceTime+meanArrivalTime);
			}

		}
        	catch (Exception e)
		{
			System.out.println(e);
		}
       		System.out.print (INPUT_PROMPT_4);
		try
		{
			String input = sc.nextLine();
			if(0 == input.compareToIgnoreCase("Y"))
			{

			}
			else if (0 == input.compareToIgnoreCase("N")){
				System.out.println("\n\nBye Bye !!\n");
				break;
			}
			else{
				System.out.println("\n"+ERROR_MESS_1);
			}

		}
		catch (Exception e)
		{
			System.out.println("\n"+e+ " "+ERROR_MESS_1);
		}
	}
          
    } // method run


    public int checkUserInput(int arrivalTime, int serviceTime, int maxArrivalTime)
    {
	    return 0;
    }
    /**
     *  Prints the results of the simulation.
     *
     */
    public void printResults (CarWash carWash)
    {
        final String RESULTS_HEADING =
            "\nHere are the results of the simulation:\n";

        LinkedList<String> results = carWash.getResults();
        System.out.println (RESULTS_HEADING);
        for (String s : results)
            System.out.print (s);
    } // method printResults

} // class CarWashUser
