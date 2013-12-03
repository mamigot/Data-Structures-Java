/**
 * Test19 -- Dijkstra's Algorithm for unlinked nodes.
 * <p>
 * Tests Dijkstra's Algorithm's on a graph whose nodes are not linked.
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

public class Test19 extends TestHarness {

    public Test19(String s) { super(s); }

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
			
		} catch (InvalidOperationException e) {
			//why was an exception thrown?
			e.printStackTrace();
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
	
		System.out.println(g.toString());
		LinkedList<Node<String,Integer>> ll = da.getPath(jpNode);

		//There should be no path because the nodes aren't linked
		if(  ll != null ) return false;
	
		System.out.println("There is no path because there are no edges.");
		return true;
	}

}