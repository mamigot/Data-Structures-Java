/**
 * Test13 -- test class extending {@link TestHarness}
 * <p>
 * Tests the addEdge(Node<T,L> N, L l, Node<T,L> M) method by inputting standard data and analyzing
 * it through the toString() method.
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

public class Test13 extends TestHarness {

    public Test13(String s) { super(s); }

    public boolean test() {
    	Graph<String,Integer> g = new Graph<String,Integer>();
    	
		Node<String,Integer> a = new Node<String,Integer>("a");
		Node<String,Integer> b = new Node<String,Integer>("b");
		Node<String,Integer> c = new Node<String,Integer>("c");
		try {
			g.addNode("a");
			g.addNode("b");
			g.addNode("c");
		} catch (InvalidOperationException e1) {
			//why? just why?
			return false;
		}
		
		
    	boolean one = false;
    	boolean two = false;
    	boolean three = false;
    	
    	try {
			g.addEdge(a, 22, b);
			one = true;
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("First exception incorrectly thrown.");
			e.printStackTrace();
			one = false;
		}
    	
    	try {
			g.addEdge(c, 23, b);
			two = true;
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Second exception incorrectly thrown.");
			e.printStackTrace();
			two = false;
		}
    	
    	try {
			g.addEdge(a, 21, c);
			three = true;
		} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Third exception incorrectly thrown.");
			e.printStackTrace();
			three = false;
		}

//    	System.out.println("one: "+one);
//    	System.out.println("two: "+two);
//    	System.out.println("three: "+three);
    	
    	System.out.println("graph:");
    	System.out.println(g);
    	
    	//the word "edge" may not show up in the string, since we've inserted no edges
    	boolean strAnalysis = false;
    	String graph = g.toString();
    	String[] gr = graph.split("Edge");
    	if(gr.length == 4) strAnalysis = true;
    	System.out.println(gr.length-1+" edges have been inserted.");
    	
    	return one && two && three && strAnalysis;
    }

}