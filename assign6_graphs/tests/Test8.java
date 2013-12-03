/**
 * Test8 -- test class extending {@link TestHarness}
 * <p>
 * Tests the addEdge(Node<T,L> N, L l, Node<T,L> M) method. Checks that it doesn't accept
 * any null parameters.
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

public class Test8 extends TestHarness {

    public Test8(String s) { super(s); }

    public boolean test() {
    	Graph<String,Integer> g = new Graph<String,Integer>();
   
    	//use these nodes to test the edges
    	Node<String,Integer> bob = new Node<String,Integer>("bob");
    	Node<String,Integer> max = new Node<String,Integer>("max");
    	
    	//have booleans for each parameter
    	boolean one = false;
    	boolean two = false;
    	boolean three = false;
    	//no edge should be in the graph if any of its
    	//parameters is null
    	try {
    		//first parameter is null
			g.addEdge(null, 1, max);
			//get all the nodes in the graph and iterate through their
			//list of edges to see if the inserted edge is present
			List<Node<String,Integer>> list1 = g.getNodes();
			for(Node<String,Integer> Node: list1){
				List<Edge<String,Integer>> edges = Node.getOutArcs();
				for(Edge<String,Integer> Edge: edges){
					//the edge with the label '1' shouldn't be in there
					if(Edge.getLabel() == 1) one = false;
				}
			}
    	} catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("First exception correctly thrown.");
			one = true;
		}
			
    	try{
			//second parameter is null
			g.addEdge(bob, null, max);
			//get all the nodes in the graph and iterate through their
			//list of edges to see if the inserted edge is present
			List<Node<String,Integer>> list2 = g.getNodes();
			for(Node<String,Integer> Node: list2){
				List<Edge<String,Integer>> edges = Node.getOutArcs();
				for(Edge<String,Integer> Edge: edges){
					//the edge with the label '1' shouldn't be in there
					if(Edge.getLabel() == null) two = false;
				}
			}
	    } catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Second exception correctly thrown.");
			two = true;
		}
			
    	try{
			//first parameter is null
			g.addEdge(bob, 1, null);
			//get all the nodes in the graph and iterate through their
			//list of edges to see if the inserted edge is present
			List<Node<String,Integer>> list3 = g.getNodes();
			for(Node<String,Integer> Node: list3){
				List<Edge<String,Integer>> edges = Node.getOutArcs();
				for(Edge<String,Integer> Edge: edges){
					//the edge with the label '1' shouldn't be in there
					if(Edge.getLabel() == 1) one = false;
				}
			}
	    } catch (InvalidOperationException e) {
			//should throw an exception
    		System.out.println("Third exception correctly thrown.");
			three = true;
		}
    	
    	return one && two && three;
    }

}