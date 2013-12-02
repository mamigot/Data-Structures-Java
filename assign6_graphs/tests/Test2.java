/**
 * Test2 -- Example test class testing {@link DijkstrasAlgorithm}
 * <p>
 * This test creates a Graph with a few nodes/edges and runs the algorithm,
 * using the graph found 
 * <a href="http://en.wikipedia.org/wiki/Dijkstra's_algorithm">here</a>
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-11-25
 */

import java.util.LinkedList;

public class Test2 extends TestHarness {

    public Test2(String s) { super(s); }

    public boolean test() {
	Graph<String,Integer> g = new Graph<String,Integer>();

	// Construct the graph from wikipedia:
	Node<String,Integer> n1;
	Node<String,Integer> n2;
	Node<String,Integer> n3;
	Node<String,Integer> n4;
	Node<String,Integer> n5;
	Node<String,Integer> n6;

	try {
	    n1 = g.addNode("1");
	    n2 = g.addNode("2");
	    n3 = g.addNode("3");
	    n4 = g.addNode("4");
	    n5 = g.addNode("5");
	    n6 = g.addNode("6");
	    g.addBiEdge("1",7,"2");
	    g.addBiEdge("1",9,"3");
	    g.addBiEdge("1",14,"6");
	    g.addBiEdge("2",10,"3");
	    g.addBiEdge("2",15,"4");
	    g.addBiEdge("3",11,"4");
	    g.addBiEdge("3",2,"6");
	    g.addBiEdge("4",6,"5");
	    g.addBiEdge("6",9,"5");
	} catch (InvalidOperationException e) {
	    return false;
	}

	// Run Dijkstra's Algorithm
	DijkstrasAlgorithm da = new DijkstrasAlgorithm(g);
	da.execute(n1);

	// Get the path from n1 to n5 that was computed by da
	LinkedList<Node<String,Integer>> ll = da.getPath(n5);
	System.out.println("path: "+ll.toString());
	// Confirm that this path is what it should be
	if(! ll.pop().getLabel().equals("1")) return false;
	if(! ll.pop().getLabel().equals("3")) return false;
	if(! ll.pop().getLabel().equals("6")) return false;
	if(! ll.pop().getLabel().equals("5")) return false;
	if(  ll.size() != 0) return false;

	//System.out.println(g.toString());
	return true;
    }

}