/**
 * Test11 -- test class extending {@link TestHarness}
 * <p>
 * Tests the addBiEdge(T n, L l, T m) method. Only creates an edge if a node
 * with label n and another with label m exist.
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

public class Test11 extends TestHarness {

    public Test11(String s) { super(s); }

    public boolean test() {
    	Graph<String,Integer> g = new Graph<String,Integer>();
    	try {
			g.addNode("a");
			g.addNode("b");
			g.addNode("c");
		} catch (InvalidOperationException e) {
			//shouldn't throw an exception
			return false;
		}
    	
    	boolean one = false;
    	boolean two = false;
    	boolean three = false;
    	
    	//node with n exists but m doesn't, so shouldn't be created
    	try {
			g.addBiEdge("a", 22, "notThere");
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("First exception correctly thrown.");
			one = true;
		}
    	
    	//node with n exists but m doesn't, so shouldn't be created
    	try {
			g.addBiEdge("notThere", 22, "b");
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Second exception correctly thrown.");
			two = true;
		}
    	
    	//neither n nor m exist
    	try {
			g.addBiEdge("hahahaha", 22, "notThere");
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Third exception correctly thrown.");
			three = true;
		}
    	
    	//the word "edge" may not show up in the string, since we've inserted no edges
    	boolean strAnalysis = true;
    	String graph = g.toString();
    	String[] gr = graph.split("Edge");
    	if(gr.length > 1) strAnalysis = false;
    	System.out.println("No edges have been inserted.");
    	
    	return one && two && three && strAnalysis;
    }

}