// ***********************************************************************
//
// Test1 -- An example test : confirms that the set is initially of size 0
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

public class Test1 extends TestHarness {

    public Test1(String s) { super(s); }

    public boolean test() { 
	List<String> dict = new ArrayList<String>();
	
	try {
	    //Check what happens if we try give no words
	    CWSolution c = new CWSolution(dict);
	    List<String> result = c.solutions("abc*",10);
	    //Nothing should match
	    System.out.println("An example test : confirms that the set is initially of size 0");
	    return (result.size() == 0);
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
