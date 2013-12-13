/**
 * Test3 -- test class extending {@link TestHarness}
 * <p>
 * Provide null parameter to the solutions() method and verify that the returned
 * list's length is 0.
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

public class Test3 extends TestHarness {

	public Test3(String s) { super(s); }

	public boolean test() {

		List<String> dict = new ArrayList<String>();
		dict.add("hahaha");
		dict.add("that");
		dict.add("is");
		dict.add("funny");

		try {
			//give words
			CWSolution c = new CWSolution(dict);
			List<String> result = c.solutions(null,10);
			//Nothing should match
			System.out.println("Provide null parameter to the solutions() method");
			return (result.size() == 0);
		} catch (Exception e){
			//if we catch an exception, something went wrong
			return false;
		}
	}
}
