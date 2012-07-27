/**********************************************
*  CS401 Lab Assignment 10                    *
*  File : ComparePersonFile.java              *
*       defination of compare method as per   * 
* person file sp[ecifivation                  *
*  Auther: Anand Singh                        *
*  CWID  : A20280101                          *
*  Email : asingh83@hawk.iit.edu              *
*  Date  : 13-Apr-2012                        *
***********************************************/

import java.util.*;

public class ComparePersonFile implements Comparator<String>
{
	   public int compare (String s1, String s2)
           {
                        String[] temp_1;
                        String[] temp_2;
                        int ret = 0;
                        temp_1 = s1.split(" ");
                        temp_2 = s2.split(" ");
                        // comaper first name
                        ret = temp_1[1].compareTo(temp_2[1]);
                        if(ret == 0)
                        {
                                //compare sencond name
                                ret = temp_1[2].compareTo(temp_2[2]);
                                if (ret == 0)
                                {
                                        // compare last name 
                                        ret = temp_1[0].compareTo(temp_2[0]);
                                        if (ret == 0)
                                        {
                                                // comapre the security key
                                                ret = temp_1[3].compareTo(temp_2[3]);
					//	System.out.println("c: "+ret+" "+ temp_1[3] + " " + temp_2[3]);
                                        }
                                }
                        }
                        return ret;

           }

}
