/**
 * TestKB -- Dijkstra's Algorithm with Kevin Bacon.
 * <p>
 * Creates the Kevin Bacon graph and uses Dijkstra's Algorithm to find a
 * path between Kevin Bacon and Joe Pesci.
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

public class TestKB extends TestHarness {

    public TestKB(String s) { super(s); }

    public boolean test() {
	Graph<String,Integer> g = new Graph<String,Integer>();

	//save participants strings for easy access
	String kb = "Kevin Bacon";
	String mr = "Mickey Rourke";
	String mt = "Marisa Tomei";
	String jp = "Joe Pesci";
	//starting point will be kbNode
	//they'll be defined after the graph is created
	Node<String,Integer> kbNode = null;
	Node<String,Integer> jpNode = null;
	
	try {
		g.addNode(kb);
		g.addNode(mr);
		g.addNode(mt);
		g.addNode(jp);
		//all links of equal weight
		g.addEdge(kb, 1, mr);
		g.addEdge(mr, 1, mt);
		g.addEdge(mt, 1, jp);
		
	} catch (InvalidOperationException e) {
		//why was an exception thrown?
		return false;
	}

	//run Dijkstra's Algorithm
	DijkstrasAlgorithm da = new DijkstrasAlgorithm(g);
	
	//get the nodes in a list to initialize the starting and ending nodes
	List<Node<String,Integer>> list = g.getNodes();
	for(Node<String,Integer> Node:list){
		//starting node is kevin bacon
		if(Node.getLabel().equals(kb)){
			kbNode = Node;
			//execute the algorithm from the starting point
			da.execute(kbNode);
		}
		//save the finishing node (which is joe pesci)
		else if(Node.getLabel().equals(jp)){
			jpNode = Node;
		}
	}

	LinkedList<Node<String,Integer>> ll = da.getPath(jpNode);
	System.out.println("path: "+ll.toString());
	
	
	// Confirm that this path is what it should be
	// kevin bacon -> mickey rourke -> marisa tomei -> joe pesci
	if(! ll.pop().getLabel().equals(kb)) return false;
	if(! ll.pop().getLabel().equals(mr)) return false;
	if(! ll.pop().getLabel().equals(mt)) return false;
	if(! ll.pop().getLabel().equals(jp)) return false;
	if(  ll.size() != 0) return false;

	System.out.println(g.toString());
	return true;
    }

}