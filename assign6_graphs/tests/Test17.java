/**
 * Test17 -- test class extending {@link TestHarness}
 * <p>
 * Makes sure that neither 'n' nor 'm' in addBiEdge(T n, L l, T m) are similar.
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

public class Test17 extends TestHarness {

    public Test17(String s) { super(s); }

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
    	
    	try {
			g.addBiEdge("a", 22, "a");
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("First exception correctly thrown.");
			one = true;
		}
    	
    	try {
			g.addBiEdge("b", 22, "b");
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Second exception correctly thrown.");
			two = true;
		}
    	
    	try {
			g.addBiEdge("c", 22, "c");
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