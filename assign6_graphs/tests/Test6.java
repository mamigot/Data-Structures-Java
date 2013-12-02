/**
 * Test6 -- test class extending {@link TestHarness}
 * <p>
 * Tests the toString() method by comparing it to Kevin Bacon's (as provided
 * in the assignment description).
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Miguel Amigot <ma2786@nyu.edu>
 * @version     Assignment 6
 * @since       2013-11-30
 */

import java.util.List;
import java.util.ArrayList;

public class Test6 extends TestHarness {

    public Test6(String s) { super(s); }

    public boolean test() {
    	Graph<String,String> g = new Graph<String,String>();
    	
    	try {
    		String kb = "Kevin Bacon";
    		String mr = "Mickey Rourke";
    		String mt = "Marisa Tomei";
    		String jp = "Joe Pesci";
    		
			g.addNode(kb);
			g.addNode(mr);
			g.addNode(mt);
			g.addNode(jp);
			
			g.addEdge(kb, "Diner", mr);
			g.addEdge(mr, "The Wrestler", mt);
			g.addEdge(mt, "My Cousin Vinny", jp);
			
		} catch (InvalidOperationException e) {
			//why was an exception thrown?
			return false;
		}
    	
    	//note that the order in which the nodes are printed doesn't matter
    	//there just have to be edges connecting certain actors and actresses
    	
    	String graphStr = g.toString();
    	
    	//must have: "--Edge(My Cousin Vinny)-->Node(Joe Pesci)"
    	boolean one = graphStr.contains("--Edge(My Cousin Vinny)-->Node(Joe Pesci)");
    	//must have: "--Edge(The Wrestler)-->Node(Marisa Tomei)"
    	boolean two = graphStr.contains("--Edge(The Wrestler)-->Node(Marisa Tomei)");
    	//must have: "--Edge(Diner)-->Node(Mickey Rourke)"
    	boolean three = graphStr.contains("--Edge(Diner)-->Node(Mickey Rourke)");
    	//must not have: "tomatoes"
    	boolean four = !graphStr.contains("tomatoes");
    	
    	//check ocurrences of the string
    	String[] arrStr = graphStr.split("Edge");
    	//"Edge" must show up 3 times (so the array's length should be 4)
    	boolean five = arrStr.length == 4;
    	
    	System.out.println();
    	System.out.println(g);
    	
    	return one && two && three && four && five;
    }

}