/**
 * Test18 -- Dijkstra's Algorithm with null parameters.
 * <p>
 * Tests that the methods of Dijkstra's Algorithm's class do not operate
 * when provided null parameters.
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

import java.util.LinkedList;
import java.util.List;

public class Test18 extends TestHarness {

    public Test18(String s) { super(s); }

    public boolean test() {
    	//call constructor with a null parameter
    	DijkstrasAlgorithm da1 = new DijkstrasAlgorithm(null);
    	da1.execute(null);
    	//return null if parameter is null
    	boolean one = da1.getPath(null) == null;
    	
    	//use dijkstra's algorithm on a real graph
    	Graph<String,Integer> g1 = new Graph<String,Integer>();
    	String aa = "aa";
    	Node<String,Integer> aaN = new Node<String,Integer>(aa);
    	String bb = "bb";
    	Node<String,Integer> bbN = new Node<String,Integer>(bb);
    	try {
			g1.addNode(aa);
			g1.addNode(bb);
		} catch (InvalidOperationException e1) {
			//why? just why?
			return false;
		}
    	
    	try {
    		//all links of equal weight
    		g1.addEdge(aaN, 1, bbN);
    	} catch (InvalidOperationException e) {
    		//why was an exception thrown?
    		e.printStackTrace();
    		return false;
    	}
    	DijkstrasAlgorithm da2 = new DijkstrasAlgorithm(g1);
    	da1.execute(aaN);
    	//return null if parameter is null
    	boolean two = da1.getPath(null) == null;

    	System.out.println("one: "+one);
    	System.out.println("two: "+two);
    	
    	return one && two;
    }

}