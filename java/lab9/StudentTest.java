import java.util.Comparator;

public class StudentTest
{
   public static void main(String[] args)
   {
      Student s1 = new Student("Donald Duck", 4.0),
              s2 = new Student("Daffy Duck", 4.0);
      int c;

      System.out.println("Natural ordering considers GPA while comparing.");
      System.out.println("Using natural ordering and comparing\n" +
                         s1 + " == " + s2);
      c = s1.compareTo(s2);
 int result = ((Comparable)(((FairElement)e1).element)).compareTo(((FairElement)e2).element);

      if (c == 0) 
         System.out.println("These objects are the same.");
      else
         System.out.println("These objects are different.");
 

      System.out.println();

      System.out.println("Unnatural ordering considers names while comparing.");
      System.out.println("Using unnatural ordering and comparing\n" +
                         s1 + " == " + s2);

      Comparator<Student> name_comparator = new ByName();
    
      c = name_comparator.compare(s1, s2);

      if (c == 0) 
         System.out.println("These objects are the same.");
      else
         System.out.println("These objects are different.");
   }
}
