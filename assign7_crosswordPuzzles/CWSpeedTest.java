//YOU DO _NOT_ NEED TO MODIFY THIS FILE

import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CWSpeedTest {

    public static void main(String[] args){
	try{
	    FileParser fp = new FileParser("TWL06.txt");
	    List<String> solutions = null;
	    //Change this to change the pattern
	    String pattern = "*S**"; 
	    //Change this to change the max solutions
	    int maxSolns = 2000;
	    
	    List<String> dict = fp.getAllWords();

	    Date d1 = new Date();
	    CWSolution c = new CWSolution(dict);
	    Date d2 = new Date();
	    for (int i = 0; i < 1000; i++)
		solutions = c.solutions(pattern,maxSolns);
	    Date d3 = new Date();
	    System.out.println("Time 1: " + (d2.getTime() - d1.getTime()));
	    System.out.println("Time 2: " + (d3.getTime() - d2.getTime()));
	    System.out.println("For the pattern: " + pattern);
	    System.out.println("With max solutions: " + maxSolns);
	    System.out.println(solutions);

	}catch (Exception e){
	    e.printStackTrace();
	}
    }
}
