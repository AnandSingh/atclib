import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.Result;
import static org.junit.runner.JUnitCore.runClasses;
import java.util.*;
import java.io.*;

public class SequenceTest 
{
	public static void main (String[] args)
	{
		Result res = runClasses(SequenceTest.class);
		System.out.println("Test run = "+ res.getRunCount() + "\nTest failed = "+
				    res.getFailures());
	}
	
	protected Sequence<String> seq;

	@Before
	public void runBeforeEveryTest()
	{
		seq = new Sequence<String> ();
	}
	
	@Test
	public void testSequenceAppendSizeGet()
	{
	// test the append, get and size method
		seq.append("one");
		seq.append("two");
		seq.append("three");
		assertEquals (3, seq.size());
		assertEquals ("two", seq.get(1));
        }
	
	@Test
	public void testSequenceGet()
	{
	        // test the negative condition of set method 
	        seq.set(4,"FOUR");
                assertEquals ("FOUR", seq.get(4));
                seq.set(0,"ONE");
                assertEquals ("ONE", seq.get(0));
		seq.append("one");
		assertEquals("one", seq.get(0));
		seq.set(0,"ONE");
		assertEquals("ONE", seq.get(0));

	}

	@Test
	public void testSequenceAppendLimit()
	{
		seq = new Sequence<String> ();
		seq.append("1");
		seq.append("2");
		seq.append("3");
		seq.append("4");
		seq.append("5");
		seq.append("6");
		seq.append("7");
		seq.append("8");
		seq.append("9");
		seq.append("10");
		seq.append("11");
		seq.append("12");
		assertEquals("11", seq.get(10));
		assertEquals("12", seq.get(11));
		assertEquals("12", seq.size());
		
	}
} 
