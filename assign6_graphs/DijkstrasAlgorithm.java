/**
 * An implementation of Dijkstra's shortest path algorithm on a {@link Graph}
 * <p>
 * Once this class is instantiated, a user invokes {@link execute} to run
 * the algorithm, and then {@link getPath} to view the path that was found.
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Miguel Amigot <ma2786@nyu.edu>, Eric Koskinen <ejk@cs.nyu.edu> (lecturer)
 * @version     Assignment 6
 * @since       2013-11-30
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstrasAlgorithm<T> {
	
	/**
	 * List containing the nodes of the graph.
	 */
	private List<Node<T,Integer>> nodes;
	/**
	 * List containing the edges of the graph.
	 */
	private List<Edge<T,Integer>> edges;
	/**
	 * Set containing the settled nodes of the graph.
	 */
	private Set<Node<T,Integer>> settledNodes;
	/**
	 * Set containing the unsettled nodes of the graph.
	 */
	private Set<Node<T,Integer>> unSettledNodes;
	/**
	 * Map from nodes to nodes containing the predecessors of the algorithm.
	 */
	private Map<Node<T,Integer>, Node<T,Integer>> predecessors;
	/**
	 * Map from nodes to integers containing the relevant distances.
	 */
	private Map<Node<T,Integer>, Integer> distance;
	
  /** 
   * Constructor. A Graph<T,Integer> must be provided.
   * <p>
   * Note that Dijkstra's Algorithm assumes that the edges
   * are weighted, so this constructor only operates on Graphs
   * whose edges have Integer labels.
   */
  public DijkstrasAlgorithm(Graph<T,Integer> graph) {
	  //do nothing if graph is null
	  if(graph == null) return;
	  
	   //create an array list with the nodes
	   this.nodes = new ArrayList<Node<T,Integer>>(graph.getNodes());
	   this.edges = new ArrayList<Edge<T,Integer>>();
	   
	   //retreive the edges from each node's getOutArcs() lists
	   //if a given node is not in this.edges, then add it
	   for(Node<T,Integer> Node: nodes){
		   List<Edge<T,Integer>> currList = Node.getOutArcs();
		   for(Edge<T,Integer> Edge: currList){
			   if(!this.edges.contains(Edge)){
				   this.edges.add(Edge);
			   }
		   }
	   }
  }

  /**
   * Main method that kicks off the algorithm.
   * <p>
   * As long as {@link source} is not null, it partitions nodes into the settled and unsettled
   * sets. Nodes are moved to the settled set if a shortest path from the source to the node
   * is found. Iteratively, it selects the nodes with the lowest distance from the source and removes them
   * from the unsettled set. Consequently, it evaluates and updates their relevant distances.
   * <p>
   * Relies on {@link #findMinimalDistances(Node)}, {@link #getDistance(Node, Node)},
   * {@link #getNeighbors(Node)}, {@link #getMinimum(Set)}, {@link #isSettled(Node)} and
   * {@link #getShortestDistance(Node)}.
   * 
   * @param source	starting node from which the algorithm is initiated.
   */
  public void execute(Node<T,Integer> source) {
	  //do nothing if source is null
	  if(source == null) return;
	  //do nothing if this.edges or this.nodes is null
	  if(this.edges == null || this.nodes == null) return;
	  
	  //initialize variables
	  this.settledNodes = new HashSet<Node<T,Integer>>();
	  this.unSettledNodes = new HashSet<Node<T,Integer>>();
	  this.predecessors = new HashMap<Node<T,Integer>, Node<T,Integer>>();
	  this.distance = new HashMap<Node<T,Integer>, Integer>();
	  
	  //distance for the current node is 0
	  distance.put(source, 0);
	  //start with the source
	  unSettledNodes.add(source);
	  //iterate as long as there are unsettled nodes
	  while (unSettledNodes.size() > 0) {
		 //look for the minumum and add it to the settled nodes' set
		 Node<T,Integer> node = getMinimum(unSettledNodes);
	     settledNodes.add(node);
	     unSettledNodes.remove(node);
	     //obtain distance for node
	     findMinimalDistances(node);
	   }
  }
  
  /**
   * Fills {@link #distance}, {@link #predecessors} and {@link #unSettledNodes}
   * for a given {@link node}.
   * <p>
   * Helper method for {@link #execute(Node)}.
   * 
   * @param node	node whose neighbors are collected to update the mentioned
   * 				data structures.
   */
  private void findMinimalDistances(Node<T,Integer> node) {
	  //do nothing if node is null
	  if(node == null) return;
	  
	  //iterate through the node's neighbors
	  //and update this.distance, this.predecessors and this.unSettledNodes if necessary
	  List<Node<T,Integer>> adjacentNodes = getNeighbors(node);
	  for (Node<T,Integer> target : adjacentNodes) {
	    if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)){
	      distance.put(target, getShortestDistance(node) + getDistance(node, target));
	      predecessors.put(target, node);
	      unSettledNodes.add(target);
	    }
	  }	
  }
  
  /**
   * Provides the distance between {@link node} and {@link target}, which is
   * also the label of the edge that connects them.
   * <p>
   * Helper method for {@link #findMinimalDistances(Node)}.
   * 
   * @param node 	node at the tail of the edge whose label is returned.
   * @param target	node at the head of the edge whose label is returned.
   * @return The label of the edge that connects {@link node} and {@link target},
   * 		which is also the distance that separates them; if either of the parameters
   * 		is null, returns 0.
   */
  private int getDistance(Node<T,Integer> node, Node<T,Integer> target) {
	  //return 0 if node or target are null
	  if(node == null || target == null) return 0;
	  
	  for (Edge<T,Integer> edge : edges) {
	    if (edge.getTail().equals(node) && edge.getHead().equals(target)) {
	    	return edge.getLabel();
	    }
	   }
	  throw new RuntimeException("Should not happen");
  }
  
  /**
   * Returns a list of {@link node}'s neighbors in the graph.
   * <p>
   * Helper method for {@link #findMinimalDistances(Node)}.
   * 
   * @param node	node whose neighbors in the graph are added to the returned list.
   * @return	list of {@link node}'s neighbors if it's not null; otherwise, null.
   */
  private List<Node<T,Integer>> getNeighbors(Node<T,Integer> node) {
	  //return null if node is null
	  if(node == null) return null;
	  
	  List<Node<T,Integer>> neighbors = new ArrayList<Node<T,Integer>>();
	  for (Edge<T,Integer> edge : edges) {
	    if (edge.getTail().equals(node) && !isSettled(edge.getHead())) {
	       neighbors.add(edge.getHead());
	     }
	  }
	  return neighbors;
  }
  
  /**
   * Finds and returns the node with the lowest distance.
   * <p>
   * Helper method for {@link #execute(Node)}.
   * 
   * @param vertexes
   * @return node with the lowest distance if {@link vertexes} is not null;
   * 		otherwise, return null.
   */
  private Node<T,Integer> getMinimum(Set<Node<T,Integer>> vertexes) {
	  //return null if vertexes is null
	  if(vertexes == null) return null;
	  
	  Node<T,Integer> minimum = null;
	  for (Node<T,Integer> vertex : vertexes) {
	    if (minimum == null) {
	      minimum = vertex;
	    } else {
	      if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
	        minimum = vertex;
	      }
	    }
	  }
	  return minimum;
  }
  
  /**
   * Determines if a provided node is in the settled set.
   * <p>
   * Helper method for {@link #getNeighbors(Node)}.
   * 
   * @param vertex	node whose presence in the settled set is checked.
   * @return	true if it's present; false, if it's not.
   */
  private boolean isSettled(Node<T,Integer> vertex) {
	  return settledNodes.contains(vertex);
  }
  
  /**
   * Returns the mapped distance corresponding to {@link destination}.
   * <p>
   * Helper method for {@link #findMinimalDistances(Node)} and {@link #getMinimum(Set)}.
   * 
   * @param destination	node whose presence is checked in {@link #distance}.
   * @return the value of the distance if it's present, MAX_VALUE if not.
   */
  private int getShortestDistance(Node<T,Integer> destination) {
	  //return 0 if destination is null
	  if(destination == null) return 0;
	  
	  Integer d = distance.get(destination);
	  if (d == null) {
	    return Integer.MAX_VALUE;
	  } else {
	    return d;
	  }
  }
  
  /**
   * Returns a path to the node labeled {@link target}.
   * <p>
   * Called after {@link #execute(Node)} is called. Goes through the steps detailed
   * in {@link #predecessors}. 
   * 
   * @param target	node at the destination of the path that is returned.
   * @return linked-list containing the path to the node {@link target}; if no
   * 		path exists, null.
   */
  public LinkedList<Node<T,Integer>> getPath(Node<T,Integer> target) {
	 //return null if target is null
	 if(target == null) return null;
	 //return null if this.edges or this.nodes is null
	 if(this.edges == null || this.nodes == null) return null;
	  
	 //linked list which will be returned
	 LinkedList<Node<T,Integer>> path = new LinkedList<Node<T,Integer>>();
	 Node<T,Integer> step = target;
	  // check if a path exists
	  if (predecessors.get(step) == null) {
	    return null; //no path exists
	  }
	  path.add(step);
	  //account for all steps in the path
	  while (predecessors.get(step) != null) {
	    step = predecessors.get(step);
	    path.add(step);
	  }
	  // Put it into the correct order
	  Collections.reverse(path);
	  return path;
	}
  }