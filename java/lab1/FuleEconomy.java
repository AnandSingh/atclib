/**********************************************
*  CS401 Lab Assignment 1                     *
*  File : FuleEconomy.java                    *
*         The main program provide an menu to *
*         user for calculating Fule Economy   *
*         in an interactive manner.           *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 18-jan-2012                        *
***********************************************/

/*
** import java util library
*/
import java.util.*;


/*
** Main class for calculating Fule Economy
*/
public class FuleEconomy {

        /*
	** private member variables
	** act as input parameter from user
	*/
	private double distanceKm = 0;
	private double fulesLtr = 0;
	
	/*
	** public member fuction defination
        */

        /* Init() : initialize teh private member variables
	*/
        public void Init()
	{
		this.distanceKm = 0;
		this.fulesLtr = 0;
	}

        /* Average() : calculate the average (Fuel Economy and return the result)
        */
	public double Average()
	{
		return (this.distanceKm / this.fulesLtr);
	}
        
        /* printErrorMsg() : Print the error message as per thr error code
	*/
        public void printErrorMsg(int errorNo)
	{
		switch (errorNo)
		{
			case 1:  // if user has enter the wrong values for Input parameters
 		        	System.out.println("Please enter only unsigned Integer/Decimal Values.");
				break;
			case 2:   // if user has enter the wrong choice in Menu.
				System.out.println("Wrong choice, Please enter 1 or 2");  
				break;
			default:
		}
	}

	/*
	** main subroutine 
	*/
	public static void main (String[] args){
                // creating an instance of main class 
		FuleEconomy fuleEcon = new FuleEconomy();
                // condition variable to dispaly menu reapetedly
		Boolean condition = true;
		while(condition) {
			// for reciving input from standard input (stdin)
		        Scanner in = new Scanner(System.in);
			// varaible to recive main menu choice from user
			int choice = 0;
                        // Main menu prints
                	System.out.println("\n====== MENU for calculating Fule Economy =======");
			System.out.println(" 1. To calculate Fule Economy.");
			System.out.println(" 2. Exit.");
                        System.out.print(" Enter your choice : ");
			
			// try-catch block: to catch exception if user has enter other character then integer or float
			try {
				// Initialising the variables to 0
				fuleEcon.Init();  
			        // to recive main menu choice from user
				choice = in.nextInt();
				// execute based on user choice 
				if(choice == 1) {
					// fule economy calculation
					// takes user values for Distance travled (in KM) and Fule counsumed (in Liter)
                                        // do the boundanry condition check on recived values from user
                                        // if values are in linit then calculate the Average and display on screen
					System.out.print("Enter the KiloMeter Traveled : ");
					fuleEcon.distanceKm = in.nextDouble();
					if(fuleEcon.distanceKm > 0) {
						System.out.print("Enter Liters of Fules consumed : ");
						fuleEcon.fulesLtr = in.nextDouble();
						if(fuleEcon.fulesLtr > 0) {
							System.out.println("Average Km/L is : " + fuleEcon.Average() + " Km/L");
						} else {
					      		fuleEcon.printErrorMsg(1);
						}
					} else {
					      fuleEcon.printErrorMsg(1);
					}
				} else if(choice == 2) {
					// For exit choice from main menu
					condition = false;
				} else {
                                        // for wrong slection of choice
					fuleEcon.printErrorMsg(2);
				}
                                choice = 0;        // clearing the state 
        		} catch (Exception e) {
                                // if user has enter values other then Integer/Decimal
                                if(choice == 1) {
					// display the error message if user has enter wrong values in Fuel economy section
					fuleEcon.printErrorMsg(1);
				} else {
					// display the error message if user has enter wrong values in Menu choice
					fuleEcon.printErrorMsg(2);
				} 
				
			}
		}
	}

}
