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

		//Graph class
		Test3 t3 = new Test3("t3");
		Test4 t4 = new Test4("t4");
		Test5 t5 = new Test5("t5");
		Test6 t6 = new Test6("t6");
		Test7 t7 = new Test7("t7");
		Test8 t8 = new Test8("t8");
		Test9 t9 = new Test9("t9");
		Test10 t10 = new Test10("t10");
		Test11 t11 = new Test11("t11");
		Test12 t12 = new Test12("t12");
		Test13 t13 = new Test13("t13");
		Test14 t14 = new Test14("t14");
		Test15 t15 = new Test15("t15");
		Test16 t16 = new Test16("t16");
		Test17 t17 = new Test17("t17");
		
		//Kevin Bacon - Dijkstra's Algorithm
		TestKB tKB = new TestKB("tKB");
		TestKB2 tKB2 = new TestKB2("tKB2");
		
		//More on Dijkstra's Algorithm
		Test18 t18 = new Test18("t18");
		Test19 t19 = new Test19("t19");
		Test20 t20 = new Test20("t20");

		TestHarness.run();
	}
}