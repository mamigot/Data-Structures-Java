/**
 * Test1 -- Example test class extending {@link TestHarness}
 * <p>
 * This test creates a Graph with a few nodes/edges
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

public class Test1 extends TestHarness {

    public Test1(String s) { super(s); }

    public boolean test() {
	Graph<String,Integer> g = new Graph<String,Integer>();
	Node<String,Integer> n1;

	try {
	    // Create two nodes and an edge between them
	    n1 = g.addNode("a");
	    Node<String,Integer> n2 = g.addNode("B");
	    g.addEdge("a",15,"B");
	    
	} catch (InvalidOperationException e) {
	    return false;
	}

	// Make sure n1's toStringWithEdges() works correctly
	if(!n1.toStringWithEdges().equals("Node(a)\n  --Edge(15)-->Node(B)\n"))
	    return false;

	System.out.println(g.toString());
	return true;
    }

}