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
    	Graph<Character,Integer> g = new Graph<Character,Integer>();
    	
    	//add a myriad of nodes
    	int count = 0;
    	int start = 32;
    	int end = 123;
    	//create a list where all relevant characters will be stored
    	List<Character> charList = new ArrayList<Character>();
    	try{
    		for(int i=start; i<end; i++){
    			g.addNode((char) i);
    			charList.add((char) i);
    			count++;
    		}
    	}catch(InvalidOperationException e){
    		//Shouldn't have thrown an exception here
    		return false;
    	}
    	
    	List<Node<Character,Integer>> list = g.getNodes();
    	boolean countTest = list.size() == count;
    	
    	//checks that all of the elements that were inserted in the graph
    	//are in the charList
    	boolean presentTest = true;
    	for(Node<Character, Integer> Node: list){
    		if(!charList.contains(Node.getLabel())) presentTest = false; 
    	}
    	
    	System.out.println("Added "+count+" elements.");
    	return countTest && presentTest;
    }

}