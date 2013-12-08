// ***********************************************************************
//
// Test2 -- Provide null parameter to the constructor
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

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
