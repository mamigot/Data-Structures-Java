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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
    	//arraylist that will be populated through the iterator and returned
    	ArrayList<Node<T,L>> nodesList = new ArrayList<Node<T,L>>();
    	
    	//use an iterator for the hashmap
    	Collection<Node<T, L>> collection = nodes.values();
    	//get an iterator for the collection
    	Iterator<Node<T,L>> it = collection.iterator();
    	//go through the iterator and add each node to the arraylist
    	while(it.hasNext()){
    		nodesList.add(it.next());
    	}
    	
    	
    	//ERROR check------------
    	if(it.hasNext()) System.out.println("BAD! the iterator still has something!!!!");
    	//if needed, debug!
    	
		return nodesList;
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
    	//retreive the appropriate nodes from the hashmap
    	Node<T,L> head = findNode(n);
    	Node<T,L> tail = findNode(m);
    	
    	//neither "n" nor "m" may be null
    	if((head == null) || (tail == null)) throw new InvalidOperationException("Both tail and head node labels must exist");
    
    	//construct the edge from the TAIL to the HEAD
    	Edge<T,L> newEdge = new Edge<T,L>(l, head, tail);
    	
    	//the new edge is an outbound arc from the TAIL to the HEAD
    	tail.addOutArc(newEdge);
    	
		return newEdge;
    }

    /** 
     * Variant of {@link addEdge} in which the Nodes are specified
     * rather than node labels.
     * @throws InvalidOperationException
     */
    public Edge<T,L> addEdge(Node<T,L> N, L l, Node<T,L> M) throws InvalidOperationException {
    	//neither "N" nor "M" may be null
    	if((N == null) || (M == null)) throw new InvalidOperationException("Both tail and head nodes must exist");
    	
    	//rename parameters to facilitate reading
    	Node<T,L> head = N;
    	Node<T,L> tail = M;
    	
    	//construct the edge from the TAIL to the HEAD
    	Edge<T,L> newEdge = new Edge<T,L>(l, head, tail);
    	
    	//the new edge is an outbound arc from the TAIL to the HEAD
    	tail.addOutArc(newEdge);
    	
		return newEdge;
    }

    /** 
     * Add an edge from n to m, as well as an edge from m to n.
     */
    public void addBiEdge(T n, L l, T m) throws InvalidOperationException {
    	//retreive the appropriate nodes from the hashmap
    	Node<T,L> one = findNode(n);
    	Node<T,L> two = findNode(m);
    	
    	//neither "n" nor "m" may be null
    	if((one == null) || (two == null)) throw new InvalidOperationException("Both tail and head node labels must exist");
    	
    	//construct two edges to link nodes "one" and "two"
    	Edge<T,L> fromOne = new Edge<T,L>(l, two, one);
    	Edge<T,L> fromTwo = new Edge<T,L>(l, one, two);
    	
    	//add them to their respective instantiations
    	one.addOutArc(fromOne);
    	two.addOutArc(fromTwo);
    }

    /** 
     * Display the graph as in the Assignment description
     */
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	//get an iterator to go through the elements of the hashmap
    	Collection<Node<T, L>> collection = nodes.values();
    	//get an iterator for the collection
    	Iterator<Node<T,L>> it = collection.iterator();
    	//go through the iterator and add each node to the arraylist
    	while(it.hasNext()){
    		sb.append(it.next().toStringWithEdges());
    	}
    	
		return sb.toString();

    }
}
