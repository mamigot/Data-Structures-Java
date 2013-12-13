/**
 * Test7 -- test class extending {@link TestHarness}
 * <p>
 * Provide a pattern with no letters e.g. "********" and verify that a
 * non-empty list is returned.
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Miguel Amigot <ma2786@nyu.edu>
 * @version     Assignment 7
 * @since       2013-12-13
 */

import java.util.ArrayList;
import java.util.List;

public class Test7 extends TestHarness {

	public Test7(String s) { super(s); }

	public boolean test() { 
		List<String> dict = new ArrayList<String>();
		dict.add("Hello");
		dict.add("potaTOes");
		dict.add("MomavoEs");
		dict.add("hahahahahahaha");

		try {
			CWSolution c = new CWSolution(dict);
			List<String> result = c.solutions("********",10);

			System.out.println("Provide a pattern with no letters e.g. \"********\"");
			System.out.println("Entered: "+dict);
			System.out.println("Result: "+result);

			return (result.size() == 2);
		} catch (Exception e){
			//if we catch an exception, something went wrong
			return false;
		}
	}
}
