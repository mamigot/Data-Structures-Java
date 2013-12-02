/**
 * Test Class -- The main function that registers and executes tests.
 * <p>
 * Register your new tests in this file by extending {@link Test#main(String[])}
 * <p>
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-09-01
 */

public class Test {

	public static void main(String [] args) {
		Test1 t1 = new Test1("t1");
		Test2 t2 = new Test2("t2");

		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// %%%%% Register your new tests here, by extending this method %%%%%
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

		Test3 t3 = new Test3("t3");
		Test4 t4 = new Test4("t4");
		Test5 t5 = new Test5("t5");
		Test6 t6 = new Test6("t6");

		TestHarness.run();
	}
}