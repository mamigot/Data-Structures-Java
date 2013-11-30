/**
 * A basic generic graph
 * <p>
 * This implements a basic directed graph with nodes and edges both having
 * their own generic type (nodes have labels of type T, edges have labels of
 * type L).
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Eric Koskinen <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-11-08
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Graph<T, L> {

	//hashmap from labels to nodes
	HashMap<T,Node<T,L>> nodes;

    public Graph() {
    	nodes = new HashMap<T,Node<T,L>>();
    }

    /** 
     * Look for an existing node with label @lab and return it.
     * Return null if no such node exists.
     */
    public Node<T,L> findNode(T lab) {
    	//CHECK: what happens if lab is null

    	//look for it in the hashmap    	
    	Node<T,L> gotten = nodes.get(lab);
    	if(gotten != null) return gotten;
    	else return null;
    }

    /** 
     * Add a new node to the graph. The new node will have label {@link lab}
     * unless a node consisting of {@link lab} already existed, in which case
     * this method throws an {@link InvalidOperationException}.
     */
    public Node<T,L> addNode(T lab) throws InvalidOperationException {
    	//Check if the node already exists
    	if(findNode(lab) != null) throw new InvalidOperationException("This node is already in the graph.");
    	
    	//If not, construct it and put it in the hashmap
    	Node<T,L> newGuy = new Node<T,L>(lab);
    	
    	//put the node in the hashmap and return it
		return nodes.put(lab, newGuy);
    }


    /** 
     * Return a list of all of the nodes in the Graph.
     */
    public List<Node<T,L>> getNodes() {
    	
		return null;

	// Implement.

	// Hint: Use an ArrayList

    }

    /** 
     * Create an edge with label l. This node only succeeds if there
     * is already a node with label n, and another node with label m.
     * Note: this is a multi-graph: there may be multiple edges
     * between two nodes, as long as they have distinct labels.
     * Note also that this is a directed graph, so there is an important
     * difference between addEdge(a,x,b) and addEdge(b,x,a).
     *
     * If a node with label n doesn't already exist, throws an
     * InvalidOperationException. Same thing if a node with label m
     * doesn't already exist.
     */
    public Edge<T,L> addEdge(T n, L l, T m) throws InvalidOperationException {
		return null;

	// Implement.

    }

    /** 
     * Variant of {@link addEdge} in which the Nodes are specified
     * rather than node labels.
     */
    public Edge<T,L> addEdge(Node<T,L> N, L l, Node<T,L> M) {
		return null;

	// Implement.

    }

    /** 
     * Add an edge from n to m, as well as an edge from m to n.
     */
    public void addBiEdge(T n, L l, T m) throws InvalidOperationException {

	// Implement.

    }

    /** 
     * Display the graph as in the Assignment description
     */
    public String toString() {
		return null;

	// Implement.

	// Hint: You may want to use a StringBuilder object
	// to assemble a string from all of the nodes.

    }
}
