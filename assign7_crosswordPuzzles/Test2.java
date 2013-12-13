/**
 * Test2 -- test class extending {@link TestHarness}
 * <p>
 * Provide null parameter to the constructor and verify that the returned
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

public class Test2 extends TestHarness {

    public Test2(String s) { super(s); }

    public boolean test() {
    	
	try {
	    //null parameter to the constructor
	    CWSolution c = new CWSolution(null);
	    List<String> result = c.solutions("abc*",10);
	    //Nothing should match
	    System.out.println("Provide null parameter to the constructor");
	    return (result.size() == 0);
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
