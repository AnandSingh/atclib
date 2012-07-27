import java.util.*;

public class InfixToPostfix {

	protected CS401StackLinkedListImpl<Character> operatorStack; 

	protected String INFIX_EXP_1 = "1 + 3 * 8";
	protected String INFIX_EXP_2 = "(1 + 3) * 8";
	protected String INFIX_EXP_3 = "8 - 3 - 4 * (10 + 12)";
	protected String INFIX_EXP_4 = "(10 + 9) * 20 - ( 2 + 3 * 8 / ((28 / 4 + 31 - 33) * 30)) / 15";

	protected String postfixExp = new String();
	public static void main(String[] args)
	{
		new InfixToPostfix().run();
	}

	public void run()
	{
		boolean menu = true;
		int choice = 0;
		while(menu == true)
		{
			choice = 0;
			Scanner in = new Scanner(System.in);
			System.out.println("\n=== Infix to postfix Evaluation ===");
			System.out.println("Please choose an option to test Infix to Postfix ");
			System.out.println("1. [Test]=> " + INFIX_EXP_1 );
			System.out.println("2. [Test]=> " + INFIX_EXP_2 );
			System.out.println("3. [Test]=> " + INFIX_EXP_3 );
			System.out.println("4. [Test]=> " + INFIX_EXP_4 );
        	        System.out.println("5. To test other Infix Expression");
			System.out.println("6. Exit");
			System.out.print("Enter Your Choice : ");	
			try {
				choice = in.nextInt();
			}
			catch (Exception e) {
				//System.out.println("\n\tERROR !! wrong input\n");
			}

			if(choice == 1)
			{
				System.out.println("1");
				postfixExp = infixToPostfix(INFIX_EXP_1);
				System.out.println("Postfix Expression : " + postfixExp);			
			}
			else if (choice == 2)
			{
				System.out.println("2");
				postfixExp = infixToPostfix(INFIX_EXP_2);			
				System.out.println("Postfix Expression : " + postfixExp);			
			}
			else if (choice == 3)
			{
				System.out.println("3");
				postfixExp = infixToPostfix(INFIX_EXP_3);			
				System.out.println("Postfix Expression : " + postfixExp);			
			}
			else if (choice == 4)
			{
				System.out.println("4");
				postfixExp = infixToPostfix(INFIX_EXP_4);			
				System.out.println("Postfix Expression : " + postfixExp);			
			}
			else if (choice == 5)
			{
				System.out.println("5");
				Scanner line = new Scanner(System.in);
				System.out.print("\nEnter an Infix Expression : ");
				String infixExp = line.nextLine();
				postfixExp = infixToPostfix(infixExp);			
				System.out.println("Postfix Expression : " + postfixExp);			
			}
			else if(choice == 6)
			{
				System.out.println("\n\tBye Bye !!\n");
				break;
			}else 
			{
				System.out.println("\n\tERROR !! wrong input\n");
			}

		}
	}

	/*
	 *  return 1 : pop to postfix
	 *  	  -1 : push to stack
	 *  	   0 : pop and discard  
	 * */
	protected int checkPrecedence(char topStack, char operartor)
	{
		if (operartor == '(')
		{
			return -1; // push 
		} else if(operartor == ')')
		{
			if(topStack == '+' || topStack == '-' || topStack == '*' || topStack == '/')
			{
				return 1;
			}else if (topStack == '(')
			{
				return 0;
			}
		} else if(operartor == '+' || operartor == '-')
		{
			if(topStack == '+' || topStack == '-' || topStack == '*' || topStack == '/')
			{
				return 1;
			}else if(topStack == '(')
			{
				return -1;
			}
		} else if(operartor == '*' || operartor == '/')
		{
			if(topStack == '+' || topStack == '-' || topStack == '(')
			{
				return -1;
			}   
			else if(topStack == '*' || topStack == '/')
			{
				return 1;
			}   
		}
		return 0;
	}

	protected String infixToPostfix(String infixExp)
	{
		String postfixExp = new String();
		operatorStack = new CS401StackLinkedListImpl<Character> ();
		parseInfix infix = new parseInfix(infixExp);
	
		while( true == infix.hasNext())
		{
			switch (infix.isNextOperatorOrOperand())
			{
				case 1: // Next character is operator
					Character operartor = infix.getNextOperator();
					if(operatorStack.isEmpty() == true || operartor == '(')
					{
						operatorStack.push(operartor);
					}else 
					{
						while(operatorStack.isEmpty() == false)
						{
							Character topStack = operatorStack.pop();
							int ret = checkPrecedence(topStack, operartor);
							if (ret > 0) // topStack has higher precendence
							{
							//	if(operartor == '(')
							//	{
							//		operatorStack.push(topStack);
							//		operatorStack.push(operartor);
							//		break;
							//	}

								postfixExp = postfixExp.concat(topStack.toString());
								if(operatorStack.isEmpty() == true)
								{
									operatorStack.push(operartor);
									break;
								}
						/*		if (operartor == ')' )
								{
									postfixExp = postfixExp.concat(topStack.toString());
								}	
								else 
								{       //
									postfixExp = postfixExp.concat(operartor.toString());
									operatorStack.push(topStack);
									break;
								}
								*/
								//topStack = operatorStack.pop();	
							}else if (ret < 0) // topStack has lower precendence
							{
								operatorStack.push(topStack);
								operatorStack.push(operartor);
								break;
							}else if(ret == 0) // pop and discard
							{
								break;
							}
						}
					}
					int stackLen = operatorStack.size();
					System.out.print("\t S: ");
					operatorStack.printStack();			
					break;
				case 2: // next character is operand
					String operand = infix.getNextOperand();
               				postfixExp = postfixExp.concat(operand);                  
					System.out.println("\t P: "+postfixExp);
					break;
			}
			//System.out.println("  : " +infix.nextValidChar());
		}

		while(operatorStack.isEmpty() == false)
		{
			Character topStack = operatorStack.pop();
			postfixExp = postfixExp.concat(topStack.toString());
		}
		return postfixExp;
		
	}

	protected class parseInfix
	{

		protected String infixExp = new String();
		protected Integer Length = -1;
		protected Integer currIndex = -1;
		//char infixCharArray[];

		public parseInfix(String Exp)
		{
			infixExp = Exp;
			Length = infixExp.length();
			currIndex = 0;
			//infixCharArray = new char[Length];
			//infixCharArray = infixExp.toCharArray();
		}

		public char getNextOperator()
		{
			//int ret = inflixCharArray[currIndex];
			char ret = infixExp.charAt(currIndex);
			currIndex++;
			return ret;
		}
		
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

			if(tempIndex >= Length)
				return 0;
			while (tempIndex < Length)
			{
				//char c = infixCharArray[tempIndex];
				char c = infixExp.charAt(tempIndex);
				if (c == ' '){
					tempIndex++; 
					currIndex++;
					continue;
				}
				if( c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '(' || c == ')' ) {
					return 1;
				} else if ( c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || 
					c == '6' || c == '7' || c == '8' || c == '9' || c == '0') {
					return 2;
				}else {
					return -1;
				}
			}
			return -1;
		}

		protected boolean checkForIntegerAndOperands(char c)
		{
			if ( (c != ' ') && 
			     ( c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0' ||
			       c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '(' || c == ')' ) )
				return true;
			else
				return false;
		}
		/*
		public int numValidChar()
		{
			if(Length == -1)
				return 0;

			int validCount = 0;
			for(int i = 0; i <Length; i++ )
			{
				if(true == checkForIntegerAndOperands(infixCharArray[i]))
				{
					validCount++;
				}
			}
			return validCount;
		}
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
		/*
		public char nextValidChar()
		{
			
			char ret = '\0';
			int valid = 0;

			if((currIndex >= 0) && (currIndex < Length))
			{
				while(currIndex < Length)
				{
					if(checkForIntegerAndOperands(infixCharArray[currIndex]) == true)
					{
						valid = 1;		
						ret = infixCharArray[currIndex];
						currIndex++;
						break;
					}else
					{
						currIndex++;
					}
				}
				if(valid == 1)
				{
					return ret;
				}else
				{
					throw new UnsupportedOperationException();
				}
			}
			else {
				throw new UnsupportedOperationException();
			}

		}
		*/
	}
}
