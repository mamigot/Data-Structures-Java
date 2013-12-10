// ***********************************************************************
//
// Test6 -- Let the maxRequired parameter in solutions() be less than
// the existing number of words
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
