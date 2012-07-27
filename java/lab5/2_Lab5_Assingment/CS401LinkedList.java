import java.util.Iterator;

public class CS401LinkedList
{

	public static void main(String[] args)
	{
		CS401LinkedListImpl<Chores>
			chores_list = new CS401LinkedListImpl<Chores> ();

		Chores a = new Chores("Make Bed", 10);
		Chores b = new Chores("Do Laundry", 5);
		Chores c = new Chores("Take cut garbage", 20);
		Chores d = new Chores("Clean car", 18);
		Chores e = new Chores("Excerise", 70);
		Chores f = new Chores("Sleep", 70);

		chores_list.add(a);
		chores_list.add(b);
		chores_list.add(c);
		chores_list.add(d);
		chores_list.add(e);
		chores_list.add(f);

		System.out.println("There are "+chores_list.size()+" elements on the chores list !");

		System.out.println("These are: ");
		for(Chores c1 : chores_list)
			System.out.println(c1);

		Iterator<Chores> itr = chores_list.iterator();
		while(itr.hasNext())
			System.out.println(itr.next());
			
	}

}
