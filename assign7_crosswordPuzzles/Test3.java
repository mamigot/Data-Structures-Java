// ***********************************************************************
//
// Test3 -- Provide null parameter to the solutions() method
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
