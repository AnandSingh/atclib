/**********************************************
*  CS401 Lab Assignment 7                     *
*  File : InfixToPostfix.java                 *
*         Assignment for programming          *
*         project 8.2, pag                    *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 8-Mar-2012                        *
***********************************************/

/*
** import java util library
*/
import java.util.*;

/*
* Main class for evaluting the infix to posfix coversion
*/
public class InfixToPostfix {

	// operator stack used in conversion of infix to postfix notation
	protected CS401StackLinkedListImpl<String> operatorStack; 

	// default infix experssion as per assignment for evaluation
	protected String TEST_EXP_1 = "b * a > a + c";
	protected String TEST_EXP_2 = "b * a < a + c";
	protected String TEST_EXP_3 = "m + j * next == current * (next - previous)";
	protected String TEST_EXP_4 = "m + j * next != current * (next - previous)";
	protected String TEST_EXP_5 = "m + j * next != current * (next - previous)";	

	protected final String INPUT_PROMPT = "\n\nPlease enter a condition, or $ to quit: ";
	protected final String INPUT_PROMPT_1 = "Please enter a value: ";
	protected final String RESULT = "The value of the condition is ";
	// store  the converted postfix expression in an ArrayList form
	protected ArrayList<postfixElement> postfixExp;
 	protected int numIndentifier = 0;

        protected ArrayList<String> identifierList;
	protected ArrayList<Integer> identifierValues;

	protected ArrayList<identifierObj> identifierArray;  // not used, for future optimization

      
	public static void main(String[] args)
	{
		new InfixToPostfix().run();
	}

	/*
	 * provide an menu type of option for user to evalute the infix to postix conversion
	 * */
	public void run()
	{
		boolean menu = true;
		int choice = 0;

		System.out.println("Lab7 Assignment, Programming project 8.2");
	

		while(menu == true)
		{
			choice = 0;
			postfixExp = null;
			Scanner in = new Scanner(System.in);
			
			System.out.print(INPUT_PROMPT);	
			String line = in.nextLine();
			//System.out.println(line);
			if(0 == line.compareTo("$"))
			{
				// Exit from the program
				System.out.println("\n\tBye Bye !!\n");
				break;
			}else
			{
				//line = "b * a > a + c";
				postfixExp = infixToPostfix(line);
				if(postfixExp != null)
				{
					int numIndentifier = getNumberOfIndentifier();
					identifierValues = new ArrayList<Integer>();
					while(numIndentifier-- > 0)
					{
						System.out.print(INPUT_PROMPT_1);
						Scanner inp = new Scanner(System.in);
						try
						{
							Integer temp = inp.nextInt();
							identifierValues.add(temp);
							
							
						}catch (Exception e)
						{
							System.out.println("\n\tERROR !! Enter only Integer values ");	
						}

					}
					//System.out.println(" :" + identifierValues.toString());
					printPostfixExp(postfixExp);
					String result = evaluatePostfixExp(postfixExp);
					//System.out.println(result);					
					if(result.equals("1"))
					{
						result = "true";
					}else if(result.equals("0"))
						result = "fasle";
					System.out.println(RESULT+ result);
					
				}else
				{
					System.out.println("\n\tERROR !! Illegal infix Expression ");	
				}
			}

		//		printPostfixExp(postfixExp);
		//		String result = evaluatePostfixExp(postfixExp);
		//		System.out.println("\tResult after Evaluating postfix Exp : [ " + result + " ]");
		}
	}

	/*
	 * calculate the results by conerting the operand and operator into meaningful
	 * mathmatical equation
	 * retun : double value 
	 * */
	protected String calcResult(String operand_1, String operand_2, String operartor)
	{
				
		if(operartor.equals("*"))
		{
			return String.valueOf((Double.valueOf(operand_2) * Double.valueOf(operand_1)));
		}
		if(operartor.equals("/"))
		{
			return String.valueOf((Double.valueOf(operand_2) / Double.valueOf(operand_1)));
		}
		if(operartor.equals("%"))
		{
			return String.valueOf((Double.valueOf(operand_2) % Double.valueOf(operand_1)));
		}
		if(operartor.equals("+"))
		{
			return String.valueOf((Double.valueOf(operand_2) + Double.valueOf(operand_1)));
		}
		if(operartor.equals("-"))
		{
			return String.valueOf((Double.valueOf(operand_2) - Double.valueOf(operand_1)));
		}
		if(operartor.equals(">"))
		{
			if( Double.valueOf(operand_2) > Double.valueOf(operand_1) )
				return "1";
			else
				return "0";
		}
		if(operartor.equals(">="))
		{
			if( Double.valueOf(operand_2) >= Double.valueOf(operand_1) )
				return "1";
			else
				return "0";
		}
		if(operartor.equals("<="))
		{
			if( Double.valueOf(operand_2) <= Double.valueOf(operand_1) )
				return "1";
			else
				return "0";
		}
		if(operartor.equals("<"))
		{
			if( Double.valueOf(operand_2) < Double.valueOf(operand_1) )
				return "1";
			else
				return "0";
		}
		if(operartor.equals("=="))
		{
			if( Double.valueOf(operand_2) == Double.valueOf(operand_1) )
				return "1";
			else
				return "0";
		}
		if(operartor.equals("!="))
		{
			if( Double.valueOf(operand_2) != Double.valueOf(operand_1) )
				return "1";
			else
				return "0";
		}
		if(operartor.equals("&&"))
		{
			if( operand_2.equals("1") && operand_1.equals("1") )
				return "1";
			else
				return "0";
		}
		if(operartor.equals("||"))
		{
			if( operand_2.equals("1") || operand_1.equals("1") )
				return "1";
			else
				return "0";
		}
		return "0";


/*		switch (c)
		{
			case '*':
				res = Double.valueOf(operand_2) * Double.valueOf(operand_1);
				break;
			case '/':
				res = Double.valueOf(operand_2) / Double.valueOf(operand_1);
				break;
			case '%':
				res = Double.valueOf(operand_2) % Double.valueOf(operand_1);
				break;
			case '+':
				res = Double.valueOf(operand_2) + Double.valueOf(operand_1);
				break;
			case '-':
				res = Double.valueOf(operand_2) - Double.valueOf(operand_1);
				break;
			case ''
		}
		return res;*/
	}

	/*
	 * Method to evalate an postfix expression
	 * */
	protected String evaluatePostfixExp(ArrayList<postfixElement> postfixExp)
	{
		int arrSize = postfixExp.size();
		// intialize the stack to be used to store the result  
        	CS401StackLinkedListImpl<String> runTimeStack = new CS401StackLinkedListImpl<String> (); 
		// iterate through the postfix expression 
	        // and based on it's nature i.e. either it's and 
		// operartor or operand perform the desired task
		//System.out.println("val"+ identifierValues.toString() +" :: "+ identifierValues.size());
		//System.out.println("val"+ identifierList.toString()+" :: "+ identifierList.size());
		for (int i = 0; i<arrSize; i++)
		{
			postfixElement postfix = postfixExp.get(i);
			if(postfix.isOperartor)
			{
				// it's an operartor 
				// pop last two element from stack
				// and store the result on stack
				String operand_1 = runTimeStack.pop();
				String operand_2 = runTimeStack.pop();
				String operartor = postfix.element;
				//System.out.println(operand_1 + "=>" + identifierList.indexOf(operand_1));
				//System.out.println(operand_2 + "=>" + identifierList.indexOf(operand_2));
				if(identifierList.contains(operand_1) == true)
				{
					operand_1 = String.valueOf(identifierValues.get(identifierList.indexOf(operand_1)));
				}	
				if(identifierList.contains(operand_2) == true)
				{
					operand_2 = String.valueOf(identifierValues.get(identifierList.indexOf(operand_2)));
				}			
				//Integer operand_i1 = identifierValues.get(identifierList.indexOf(operand_1));
				//Integer operand_i2 = identifierValues.get(identifierList.indexOf(operand_2));
				//System.out.println(operand_1 );
				//System.out.println(operand_2);
				String result = calcResult(operand_1, operand_2, operartor); 
				runTimeStack.push(String.valueOf(result));
			}else  
			{
				// it's an operand
				// push it to the stack
				runTimeStack.push(postfix.element);
			}
		}
		// return the final result which will be on the top of the stack
		return runTimeStack.pop();
	}

	/*
	 * Check the precedence of the operator between the top of stack and from 
	 * the infix operator 
	 *  return 1 : pop to postfix, high precedence
	 *  	  -1 : push to stack, low precedence
	 *  	   0 : pop and discard  
	 */
	protected int checkPrecedence(String topStack, String operartor)
	{
		//String[] operatorList = {"*", "/", "%"};
		//System.out.println("==>" + operartor + " " + topStack);
		if (operartor.equals("("))
		{
			return -1;  
		} 
		else if(operartor.equals(")"))
		{
			if(topStack.equals("||") || topStack.equals("&&") || topStack.equals("==") || topStack.equals("!=") || 
					topStack.equals(">") || topStack.equals(">=") || topStack.equals("<=") || topStack.equals("<") || 
					topStack.equals("+") || topStack.equals("-") || topStack.equals("*") || topStack.equals("/") || 
					topStack.equals("%") )
			{
				return 1;
			}else if (topStack.equals("("))
			{
				return 0;
			}
		}
		else if (operartor.equals("||"))
		{
			if(topStack.equals("||") || topStack.equals("&&") || topStack.equals("==") || topStack.equals("!=") || 
					topStack.equals(">") || topStack.equals(">=") || topStack.equals("<=") || topStack.equals("<") || 
					topStack.equals("+") || topStack.equals("-") || topStack.equals("*") || topStack.equals("/") || 
					topStack.equals("%") )
			{
				return 1;
			}else if(topStack.equals("("))
			{
				return -1;
			}
		} 
		else if (operartor.equals("&&"))
		{
			if(topStack.equals("&&") || topStack.equals("==") || topStack.equals("!=") || topStack.equals(">") || 
					topStack.equals(">=") || topStack.equals("<=") || topStack.equals("<") || topStack.equals("+") || 
					topStack.equals("-") || topStack.equals("*") || topStack.equals("/") || topStack.equals("%"))
			{
				return 1;
			}else if(topStack.equals("(") || topStack.equals("||") )
			{
				return -1;
			}
		} 
		else if (operartor.equals("==") || operartor.equals("!=") )
		{
			if(topStack.equals("==") || topStack.equals("!=") || topStack.equals(">") || topStack.equals(">=") || 
					topStack.equals("<=") || topStack.equals("<") || topStack.equals("+") || topStack.equals("-") || 
					topStack.equals("*") || topStack.equals("/") || topStack.equals("%"))
			{
				return 1;
			}else if(topStack.equals("(") || topStack.equals("||") || topStack.equals("&&") )
			{
				return -1;
			}
		}
		else if (operartor.equals(">") || operartor.equals(">=") || operartor.equals("<=") || operartor.equals("<"))
		{
			if(topStack.equals(">") || topStack.equals(">=") || topStack.equals("<=") || topStack.equals("<") || 
					topStack.equals("+") || topStack.equals("-") || topStack.equals("*") || topStack.equals("/") || 
					topStack.equals("%"))
			{
				return 1;
			}else if(topStack.equals("(") || topStack.equals("||") || topStack.equals("&&") || topStack.equals("==") || 
					topStack.equals("!=")  )
			{
				return -1;
			}
		}
		else if(operartor.equals("+") || operartor.equals("-"))
		{
			if(topStack.equals("+") || topStack.equals("-") || topStack.equals("*") || topStack.equals("/") || topStack.equals("%"))
			{
				return 1;
			}else if(topStack.equals("(") || topStack.equals(">") || topStack.equals(">=") || topStack.equals("<=") || 
					topStack.equals("<")  || topStack.equals("||") || topStack.equals("&&") || topStack.equals("==") || 
					topStack.equals("!=")  )
			{
				return -1;
			}
		} 
		else if(operartor.equals("*") || operartor.equals("/") || operartor.equals("%"))
		{
			if(topStack.equals("*") || topStack.equals("/") || topStack.equals("%"))
			{
				return 1;
			}  
			else if(topStack.equals("(") || topStack.equals("+") || topStack.equals("-") || topStack.equals("*") || 
					topStack.equals(">") || topStack.equals(">=") || topStack.equals("<=") || topStack.equals("<")  || 
					topStack.equals("||") || topStack.equals("&&") || topStack.equals("==") || topStack.equals("!=")  )
			{
				return -1;
			}   
			  
		}
		return 0;
	}

	/*
	 * Method to return the total number of Indentifier in 
	 * postfix expression
	 * */
	protected int getNumberOfIndentifier()
	{
		return numIndentifier;
	}

	/*
	 * Method to convert the infix expression to postfix expression
	 * return the postfix expression in ArrayList form
	 * */
	protected ArrayList<postfixElement> infixToPostfix(String infixExp)
	{
		// Array List to store the elements of the postfix expression
		ArrayList<postfixElement> postfixExp = new ArrayList<postfixElement>(); 
		// intialize the stack to store the operatore, while converting infix to postfix
		operatorStack = new CS401StackLinkedListImpl<String> ();
		// intialize the parseInfix object to be used to iterate and parse the infix
		parseInfix infix = new parseInfix(infixExp);

                // initialize the number of Indentifier in a infix exp
		numIndentifier = 0;
		identifierList = new ArrayList<String>();
		identifierArray = new ArrayList<identifierObj>();
		
		// validating the infix Expression 
		//if (false == infix.verifyInfixExp())
		//	return null;

		// iterarte if  infix expression has next character to parse 
		while( true == infix.hasNext())
		{
			// switch based on if the next charater is operand or an operator
			switch (infix.isNextOperatorOrOperand())
			{
				// if next character is operator
				case 1: 
					// get the Operator from infix expression
					String operartor = infix.getNextOperator();
					//System.out.println("O: "+operartor);
					
					// if stack is empty or the operartor is open bracket then push to the operartor stcak
					if( (operatorStack.isEmpty() == true) || (operartor.compareTo("(") == 0))
					{
						operatorStack.push(operartor);
					}else 
					{
						// iterate if stack is not empty and 
						// consecutevly checks for the precedence of the operartor from the
						// top of the stack 
						while(operatorStack.isEmpty() == false)
						{
							// get the top of stack for checking precedence
							String topStack = operatorStack.pop();

							// check precedence
							int ret = checkPrecedence(topStack, operartor);
							//System.out.println(":: topStack = "+topStack+", operartor = "+operartor+", ret = "+ret);
							if (ret > 0) // topStack has higher precendence
							{
								// pop to postfix
								// adding the top stack operator to the postfix expression
	       							postfixElement temp = new postfixElement(true);	
								temp.element = topStack;//.toString();
								postfixExp.add(temp);
								// if stack is empty then push the operartor on stack and 
								// break from loop, sinec there is no operator on stack 
								// to check for precedence
								if(operatorStack.isEmpty() == true)
								{
									operatorStack.push(operartor);
									break;
								}
							}else if (ret < 0) // topStack has lower precendence
							{
								// push to stack
								operatorStack.push(topStack);
								operatorStack.push(operartor);
								break;
							}else if(ret == 0) // pop and discard
							{
								break;
							}
						}
					}
					
					//System.out.print("\t S: ");
					//operatorStack.printStack();			
					break;
				case 2: // next character is operand
					// add to the postfix expression
					String operand = infix.getNextOperand();
					//System.out.println("I: "+ operand);
					
					postfixElement temp = new postfixElement(false);
					temp.element = operand;
               				postfixExp.add(temp);   
					if(identifierList.contains(operand) == false)
					{    
						numIndentifier++;
						identifierList.add(operand);
						//identifierObj ident = new identifierObj();
						//ident.identifiername = operand;
						//identifierArray.add(ident);
					}
					//System.out.println("\t P: "+postfixExp);
					break;
			}
		}

                // pop till the operator stack is empty
		// and append the top of stack to postfix Expression
		while(operatorStack.isEmpty() == false)
		{
			String topStack = operatorStack.pop();
			postfixElement temp = new postfixElement(true);
			temp.element = topStack.toString();
			postfixExp.add(temp);
		}
		return postfixExp;
		
	}
	
	
	/*
	 * Prints the postfix expression which is stored in Array list
	 * */
	public void printPostfixExp(ArrayList<postfixElement> postfixExp)
	{
		System.out.print("\n\tConverted Postfix Expression => [ " );
		int arrSize = postfixExp.size();
		for(int i = 0; i< arrSize; i++)
		{
			postfixElement e = postfixExp.get(i);
			System.out.print(e.element+" ");
		}
		System.out.println(" ]");		
	
	}

	/*
	 * Basic Data structure to store the postfix expression
	 * in the Array List form. With the information of it's nature
	 * i.e, either its' operand or it's operartor
	 * */
	class postfixElement
	{
		public boolean isOperartor; // true for Operator, false for operand
		public String element;
		public postfixElement(boolean operartor) { this.isOperartor = operartor; this.element = new String();}
	}

	/*
	 * Basic Data structure to store the indentifier name and values
	 * in the Array List form. 
	 * */
	class identifierObj
	{
		public String identifierName;
		public Integer identifierValue;
		public identifierObj() { this.identifierName = new String(); this.identifierValue = 0;}
	}
	/*
	 * Class used to parse the infix expression 
	 *
	 * */
	protected class parseInfix
	{

		// used to store the infix expression in string form
		protected String infixExp = new String();
		// used to store the length of the Infix expression
		protected Integer Length = -1;
		// used to store the current index while parsing the infix
		// expression one character by one.
		protected Integer currIndex = -1;
		//char infixCharArray[];

		protected String[] operatorList = {"*", "/", "%", "+", "-", ">", ">=", "<=", "<", "==", "!=", "&&", "||", "(", ")", "=", "!", "&", "|" };

		/*
		 * Intilise the parseInfix Object
		 * */
		public parseInfix(String Exp)
		{
			infixExp = Exp;
			Length = infixExp.length();
			currIndex = 0;
			//System.out.println("&&&&&&"+infixExp);
			//infixCharArray = new char[Length];
			//infixCharArray = infixExp.toCharArray();
		}


		/*
		 * verifying the whole in Infix Expression
		 * cehckes it should only conatins the integer, operartor and open / close brackets
		 * It also the checks the position of open and close brackets and rule for that is 
		 * open brackets should start only at the begning or it shouldbe precced with an space, open brackets
		 * or any operator, the close brackets should start should be precced only with space, close brackets 
		 * or any operand
		 * */
		/*
		public boolean verifyInfixExp()
		{
			if (Length == 0)
				return false;
			int temp = 0;
                        int numBrackets = 0; 
			char prevChar = '\0';
			while (temp < Length)
			{
				char c = infixExp.charAt(temp);
				// the char should be any one of these characters
	                        if ( c == ' '||  c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || 
				     c == '6' || c == '7' || c == '8' || c == '9' || c == '0' || c == '*' || 
				     c == '/' ||  c == '+' || c == '-' || c == '(' || c == ')' )       
				{
					// check for proper open brackets brackets 
					// count only those valid brackets which are
					// at the begninig of the expression or the previous character
					// are any one of these *, /, +, -, (
					// this a dirty way of checking, but it works
					if(((c == '(') && (prevChar == '\0')) || 
					   ((c == '(')  && (prevChar == '(' || prevChar == '+' || prevChar == '-' || prevChar == '+' 
						   || prevChar == '*' || prevChar == '/' || prevChar == ' ')))
					{
						numBrackets++;
					}
					// check for the proper closing brackets
					// count only those close brackets which are
					// not at the begning of the expression and it's previous character 
					// should be any of these 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, )
					// These is some dirty way of checking, but it works  
					if(c == ')' && (prevChar == ')' || prevChar == '1' || prevChar == '2' || prevChar == '3' || prevChar == '4'
							|| prevChar == '5' || prevChar == '6' || prevChar == '7' || prevChar == '8' || prevChar == '9'
							|| prevChar == '0' || prevChar == ' '))
					{
						numBrackets--;
					}
					temp++;
					prevChar = c; // store the previous char for validating proper brackets
				        // for debugging
					//System.out.println(temp+". " + prevChar + " . " + c + "num Br : " + numBrackets);
				}else
			       	{
					break;
				}
			}
			if((temp == Length) && (numBrackets == 0))
				return true;
			else 
				return false;
		}*/

		/*
		 * return the next Operator in the infix Expression
		 * it should only be called after verifying through inNextOperatorOrOperand
		 *
		 * */
		public String getNextOperator()
		{
			//char ret = infixExp.charAt(currIndex);
			//currIndex++;
			//return ret;
			String Operator = new String();
			char temp = infixExp.charAt(currIndex);
			Operator = Character.toString(temp);
			currIndex++;
			if( isNextOperatorOrOperand() == 1)
			{
				temp = infixExp.charAt(currIndex);
				if(temp == '=' || temp == '&' || temp == '|')
				{
					Operator = Operator.concat(Character.toString(temp));
					currIndex++;
				}
			}
			return Operator;
		}
		
		/*
		 * return the next operand in the infix Expression
		 * it should be called after verifying through inNextOperatorOrOprand
		 * 
		 * */
		public String getNextOperand()
		{
			String operandVal = new String();
			//char temp = infixArray[currIndex];
			//char temp = infixExp.charAt[currIndex];
			char temp = infixExp.charAt(currIndex);
			operandVal = Character.toString(temp);
			currIndex++;
			
			while( isNextOperatorOrOperand() == 2)
			{
				temp  = infixExp.charAt(currIndex);
				operandVal = operandVal.concat(Character.toString(temp));
				currIndex++;
			}
			return operandVal;
			
		}

		/*
		 * checks if current element is operend or operator
		 * RETURN 1 if Operator
		 *        2 if Operend
		 *        0 if no element is present
		 *       -1 in case of other then operator and operand
		 */
                public int isNextOperatorOrOperand()
		{
			int tempIndex = currIndex;
			
			// error checking, if infix object in not initialised
			if(tempIndex >= Length)
				return 0;
		
			// iterate to next valid character (i.e. only for operand and operator 
			// neglecting the spaces)
			while (tempIndex < Length)
			{
				//char c = infixCharArray[tempIndex];
				char c = infixExp.charAt(tempIndex);
				if (c == ' '){
					tempIndex++; 
					currIndex++;
					continue;
				}
				
				int len = operatorList.length;
				boolean isOperator = false;
				for(int i = 0; i< len; i++)
				{
					String temp = operatorList[i];
					if(temp.compareTo(Character.toString(c)) == 0)
					{
						isOperator = true;
						break;
					}
				}
				if(isOperator == true)
					return 1;
				else
					return 2;
			}
			return -1;
		}
			
		/*
		 * check the character to be only 
		 * operand or operator, i.e removing the spaces
		 * */
		protected boolean checkForIntegerAndOperands(char c)
		{
			if(c != ' ')
				return true;
			else
				return false;
		}
	
	
		/*
		 * Check if the infix expression has next character 
		 * 
		 */
		public boolean hasNext()
		{
			if (Length == -1)
				return false;
			if(currIndex < Length)
				return true;
			else
				return false;	
		}
}
}
