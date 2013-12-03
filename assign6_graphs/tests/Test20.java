/**
 * Test20 -- Dijkstra's Algorithm for one node that's linked to itself.
 * <p>
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

public class Test20 extends TestHarness {

    public Test20(String s) { super(s); }

    public boolean test() {
		Graph<String,Integer> g = new Graph<String,Integer>();
	
		//save participants strings for easy access
		String kb = "Kevin Bacon";
		//only node will be kbNode
		//will be defined after the graph is created
		Node<String,Integer> kbNode = null;
		
		try {
			g.addNode(kb);
			//edge should not be created and an exception should be thrown
			g.addEdge(kb, 1, kb);
			
			return false; //if exception for addEdge is not thrown
		} catch (InvalidOperationException e) {
			//exception should be thrown
			
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
		}
	
		System.out.println(g.toString());
		LinkedList<Node<String,Integer>> ll = da.getPath(kbNode);

		//There should be no path because the node isn't linked to itself
		if(  ll != null ) return false;
	
		System.out.println("There's only one node and it's not linked to itself.");
		return true;
	}

}