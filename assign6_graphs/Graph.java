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
 * @author      Miguel Amigot <ma2786@nyu.edu>
 * @version     Assignment 6
 * @since       2013-11-30
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Graph<T, L> {

	/**
	 * Hashmap which maps generic labels to generic nodes.
	 */
	HashMap<T,Node<T,L>> nodes;

	/**
	 * Class constructor.
	 * <p>
	 * Initializes {@link #nodes}.
	 */
	public Graph() {
		nodes = new HashMap<T,Node<T,L>>();
	}

	/**
	 * Used to get a node with label {@link lab} if it's in the graph.
	 * <p>
	 * If the node is not in the graph, returns null.
	 * 
	 * @param lab	generic label used to identify the node.
	 * @return 		relevant node if it's present, null if it's not.
	 */
	public Node<T,L> findNode(T lab) {
		//look for it in the hashmap    	
		Node<T,L> gotten = nodes.get(lab);
		if(gotten != null) return gotten;
		else return null;
	}

	/**
	 * Adds a new node to the graph.
	 * <p>
	 * The new node will have label {@link lab} unless a node consisting of {@link lab}
	 * already existed, in which case this method throws an {@link InvalidOperationException}.
	 * 
	 * @param lab	generic label used to identify the node.
	 * @return		the node that was just created.
	 * @throws InvalidOperationException if {@link lab} is null or if the node corresponding
	 * 			to {@link lab} already exists.
	 */
	public Node<T,L> addNode(T lab) throws InvalidOperationException {
		//label cannot be null
		if(lab == null) throw new InvalidOperationException("Can't add a 'null' node.");

		//Check if the node already exists
		if(findNode(lab) != null) throw new InvalidOperationException("This node is already in the graph.");

		//If not, construct it and put it in the hashmap
		Node<T,L> newGuy = new Node<T,L>(lab);

		//put the node in the hashmap and return it
		nodes.put(lab, newGuy);
		return newGuy;
	}

	/**
	 * Returns a list of all of the nodes in the graph.
	 * <p>
	 * Populates a list containing the nodes in the graph through an iterator and returns it,
	 * in no particular order.
	 * 
	 * @return list of nodes in the graph.
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

		return nodesList;
	}

	/**
	 * Creates an edge with label {@link l}.
	 * <p>
	 * This node only succeeds if there is already a node with label {@link n},
	 * and another node with label {@link m}. Note: since this is a multi-graph, there may
	 * be multiple edges between two nodes as long as they have distinct labels. Since this
	 * is also a directed graph, there is an important difference between addEdge(a,x,b) and addEdge(b,x,a).
	 * 
	 * @param n	generic label corresponding to the tail node in the edge.
	 * @param l	generic label specifying the value of the edge.
	 * @param m	generic label corresponding to the head node in the edge.
	 * @return
	 * @throws InvalidOperationException if {@link l}, {@link n} or {@link m} is null, if
	 * 			there are no nodes with such labels or if both labels correspond to the same node. 
	 */
	public Edge<T,L> addEdge(T n, L l, T m) throws InvalidOperationException {
		if(n == null || m == null) throw new InvalidOperationException("No node's label may be null.");
		//the labels must correspond to different nodes
		else if(n.equals(m)) throw new InvalidOperationException("The head and tail nodes must be different!");

		//retreive the appropriate nodes from the hashmap
		Node<T,L> tail = findNode(n);
		Node<T,L> head = findNode(m);

		//neither "n" nor "m" may be null
		if((head == null) || (tail == null)) throw new InvalidOperationException("Both tail and head node labels must exist");
		//label cannot be null
		if(l == null) throw new InvalidOperationException("Can't add a 'null' label.");

		//construct the edge from the TAIL to the HEAD
		Edge<T,L> newEdge = new Edge<T,L>(l, head, tail);

		//the new edge is an outbound arc from the TAIL to the HEAD
		tail.addOutArc(newEdge);

		return newEdge;
	}

	/**
	 * Creates an edge with label {@link l}.
	 * Variant of {@link #addEdge(T,L,T)} in which the nodes are specified.
	 * <p>
	 * Does not insert the nodes from the parameters into the graphs
	 * if they are not present already.
	 * 
	 * Note: since this is a multi-graph, there may
	 * be multiple edges between two nodes as long as they have distinct labels. Since this
	 * is also a directed graph, there is an important difference between addEdge(a,x,b) and addEdge(b,x,a).
	 * 
	 * @param N	generic node which will comprise the tail of the edge.
	 * @param l	generic label specifying the value of the edge.
	 * @param M	generic node which will comprise the head of the edge.
	 * @return
	 * @throws InvalidOperationException if {@link l}, {@link N} or {@link M} is null, if
	 * 			there are no nodes with such labels or if both labels correspond to the same node. 
	 */
	public Edge<T,L> addEdge(Node<T,L> N, L l, Node<T,L> M) throws InvalidOperationException {
		if(N == null || M == null) throw new InvalidOperationException("No node's label may be null.");
		//the labels must correspond to different nodes
		else if(N.getLabel().equals(M.getLabel())) throw new InvalidOperationException("The head and tail nodes must be different!");

		//both nodes must have labels that are matched to nodes in the graph
		//i.e. both nodes must be in the graph
		if((!nodes.containsKey(N.getLabel())) || (!nodes.containsKey(M.getLabel()))){
			throw new InvalidOperationException("Both tail and head nodes must exist.");
		}
		//label cannot be null
		if(l == null) throw new InvalidOperationException("Can't add a 'null' label.");

		//retrieve the existing nodes
		Node<T,L> tail = findNode(N.getLabel());
		Node<T,L> head = findNode(M.getLabel());

		//construct the edge from the TAIL to the HEAD
		Edge<T,L> newEdge = new Edge<T,L>(l, head, tail);

		//the new edge is an outbound arc from the TAIL to the HEAD
		tail.addOutArc(newEdge);

		return newEdge;
	}

	/**
	 * Add an edge from {@link n} to {@link m}, as well as an edge from {@link m} to {@link n}.
	 * <p>
	 * This node only succeeds if there is already a node with label {@link n},
	 * and another node with label {@link m}. Note: since this is a multi-graph, there may
	 * be multiple edges between two nodes as long as they have distinct labels. Since this
	 * is also a directed graph, there is an important difference between addEdge(a,x,b) and addEdge(b,x,a).
	 * 
	 * @param n	generic label corresponding to the tail node in the edge.
	 * @param l	generic label specifying the value of the edge.
	 * @param m	generic label corresponding to the head node in the edge.
	 * @return
	 * @throws InvalidOperationException if {@link l}, {@link n} or {@link m} is null, if
	 * 			there are no nodes with such labels or if both labels correspond to the same node. 
	 */
	public void addBiEdge(T n, L l, T m) throws InvalidOperationException {
		if(n == null || m == null) throw new InvalidOperationException("No node's label may be null.");
		//the labels must correspond to different nodes
		else if(n.equals(m)) throw new InvalidOperationException("The head and tail nodes must be different!");

		//retreive the appropriate nodes from the hashmap
		Node<T,L> one = findNode(m);
		Node<T,L> two = findNode(n);

		//neither "n" nor "m" may be null
		if((one == null) || (two == null)) throw new InvalidOperationException("Both tail and head node labels must exist");
		//label cannot be null
		if(l == null) throw new InvalidOperationException("Can't add a 'null' label.");

		//construct two edges to link nodes "one" and "two"
		Edge<T,L> fromOne = new Edge<T,L>(l, two, one);
		Edge<T,L> fromTwo = new Edge<T,L>(l, one, two);

		//add them to their respective instantiations
		one.addOutArc(fromOne);
		two.addOutArc(fromTwo);
	}

	/**
	 * Provides string representing the graph.
	 * <p>
	 * Follows the specifications provided by the assignment description.
	 * Returns "(null)" if the graph is empty.
	 * 
	 * @return	string representing the nodes and edges in the graphs if its size
	 * 			is greater than 0; otherwise, "(null)".
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();

		//get an iterator to go through the elements of the hashmap
		Collection<Node<T, L>> collection = nodes.values();

		//get an iterator for the collection
		Iterator<Node<T,L>> it = collection.iterator();
		while(it.hasNext()){
			sb.append(it.next().toStringWithEdges());
		}

		//print "(null)" if the graph is empty
		if(collection.size() == 0) return "(null)";
		else return sb.toString();

	}
}
