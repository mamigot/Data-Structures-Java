// ***********************************************************************
//
// Test4 -- Enter words in lower and upper-case through the constructor
// and later provide upper-case words
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

public class Test4 extends TestHarness {

    public Test4(String s) { super(s); }

    public boolean test() { 
	List<String> dict = new ArrayList<String>();
	dict.add("Tomatoes");
	dict.add("potaTOes");
	dict.add("MomavoEs");
	
	try {
	    //parameter to solutions() is lower-case
	    CWSolution c = new CWSolution(dict);
	    List<String> result = c.solutions("*o*a*oes",10);
	   
	    System.out.println("Enter words in lower and upper-case through the constructor and later provide upper-case words");
	    System.out.println("Entered: "+dict);
	    System.out.println("Result: "+result);
	    
	    return (result.size() == 3) && (dict.equals(result));
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
