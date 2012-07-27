import java.util.Iterator;

public class CS401Lab5Assigment
{

	public static void main(String[] args)
	{

		new CS401Lab5Assigment().run();

	}

	public void run()
	{		

		removeTestCase_a();
		removeTestCase_b();
		removeTestCase_c();
		removeTestCase_d();
	}


	public void removeTestCase_a()
	{
		System.out.println("\nTest case a:");
                CS401LinkedListImpl<String> test_list = new CS401LinkedListImpl<String> ();

                
                test_list.add("First Entry");
		System.out.println("List data : (before remove)");
                for(String s1 : test_list)
                        System.out.println(s1);

		
               	Iterator<String> itr = test_list.iterator();
                itr.remove();

		System.out.println("List data : (after remove)");
                System.out.println("List size = "+test_list.size());
                for(String s1 : test_list)
                        System.out.println(s1);

	
	}

	public void removeTestCase_b()
        {
                System.out.println("\nTest case b:");
                CS401LinkedListImpl<String> test_list = new CS401LinkedListImpl<String> ();

 
                test_list.add("First Entry");
                test_list.add("Second Entry");
		System.out.println("List data : (before remove)");
                for(String s1 : test_list)
                        System.out.println(s1);
                
                Iterator<String> itr = test_list.iterator();
                itr.remove();
        	
		System.out.println("List data : (after remove)");
	        System.out.println("List size = "+test_list.size());
                for(String s1 : test_list)
                        System.out.println(s1);
 
        }


	public void removeTestCase_c()
        {
                System.out.println("\nTest case c:");
                CS401LinkedListImpl<String> test_list = new CS401LinkedListImpl<String> ();


                test_list.add("First Entry");
                test_list.add("Second Entry");
	
		System.out.println("List data : (before remove)");
	        for(String s1 : test_list)
                        System.out.println(s1);

                Iterator<String> itr = test_list.iterator();
                itr.next();
                
                itr.remove();
                
                System.out.println("List data : (after remove)");
                System.out.println("List size = "+test_list.size());
                for(String s1 : test_list)
                        System.out.println(s1);

        }

	public void removeTestCase_d()
        {
                System.out.println("\nTest case d:");
                CS401LinkedListImpl<String> test_list = new CS401LinkedListImpl<String> ();


                test_list.add("First Entry");
                test_list.add("Second Entry");
                test_list.add("Third Entry");
		
		System.out.println("List data : (before remove)");
 	        for(String s1 : test_list)
                        System.out.println(s1);

                Iterator<String> itr = test_list.iterator();
                itr.next();
	        itr.remove();

		System.out.println("List data : (after remove)");
                System.out.println("List size = "+test_list.size());
                for(String s1 : test_list)
                        System.out.println(s1);

        }



}
