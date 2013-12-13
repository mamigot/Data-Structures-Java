/**
 * Test6 -- test class extending {@link TestHarness}
 * <p>
 * Let the maxRequired parameter in solutions() be less than the existing
 * number of words.
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

public class Test6 extends TestHarness {

	public Test6(String s) { super(s); }

	public boolean test() { 
		List<String> dict = new ArrayList<String>();
		dict.add("Tomatoes");
		dict.add("potaTOes");
		dict.add("MomavoEs");
		dict.add("hahahahahahaha");

		try {
			//parameter to solutions() is lower-case
			CWSolution c = new CWSolution(dict);
			//we know there are 3 solutions, but we only want 2
			List<String> result = c.solutions("*o*A*OeS",2);

			System.out.println("Let the maxRequired parameter in solutions() be less than the existing number of words");
			System.out.println("Entered: "+dict);
			System.out.println("Result: "+result);

			return (result.size() == 2);
		} catch (Exception e){
			//if we catch an exception, something went wrong
			return false;
		}
	}
}
