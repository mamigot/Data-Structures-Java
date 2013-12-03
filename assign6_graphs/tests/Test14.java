/**
 * Test14 -- test class extending {@link TestHarness}
 * <p>
 * Tests the addBiEdge(T n, L l, T m) method by inputting standard data and analyzing
 * it through the getNodes() method.
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

public class Test14 extends TestHarness {

    public Test14(String s) { super(s); }

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
			g.addBiEdge("a", 22, "b");
			one = true;
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("First exception incorrectly thrown.");
			one = false;
		}
    	
    	try {
			g.addBiEdge("c", 22, "b");
			two = true;
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Second exception incorrectly thrown.");
			two = false;
		}
    	
    	try {
			g.addBiEdge("a", 22, "c");
			three = true;
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Third exception incorrectly thrown.");
			three = false;
		}
    	
    	//the word "edge" may not show up in the string, since we've inserted no edges
    	boolean strAnalysis = false;
    	String graph = g.toString();
    	String[] gr = graph.split("Edge");
    	if(gr.length == 7) strAnalysis = true;
    	System.out.println(gr.length-1+" edges have been inserted.");
    	
    	return one && two && three && strAnalysis;
    }

}