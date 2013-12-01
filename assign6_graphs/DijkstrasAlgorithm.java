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

	 private List<Node<T,Integer>> nodes;
	 private List<Edge<T,Integer>> edges;
	 
	 private Set<Node<T,Integer>> settledNodes;
	 private Set<Node<T,Integer>> unSettledNodes;
	 private Map<Node<T,Integer>, Node<T,Integer>> predecessors;
	 private Map<Node<T,Integer>, Integer> distance;
	
  /** 
   * Constructor. A Graph<T,Integer> must be provided.
   *
   * Note that Dijkstra's Algorithm assumes that the edges
   * are weighted, so this constructor only operates on Graphs
   * whose edges have Integer labels.
   */
  public DijkstrasAlgorithm(Graph<T,Integer> graph) {
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
   * The main method that kicks off the algorithm. 
   */
  public void execute(Node<T,Integer> source) {
	  this.settledNodes = new HashSet<Node<T,Integer>>();
	  this.unSettledNodes = new HashSet<Node<T,Integer>>();
	  this.predecessors = new HashMap<Node<T,Integer>, Node<T,Integer>>();
	  this.distance = new HashMap<Node<T,Integer>, Integer>();
	  distance.put(source, 0);
	  unSettledNodes.add(source);
	  while (unSettledNodes.size() > 0) {
		 Node<T,Integer> node = getMinimum(unSettledNodes);
	     settledNodes.add(node);
	     unSettledNodes.remove(node);
	     findMinimalDistances(node);
	   }
  }
  
  private void findMinimalDistances(Node<T,Integer> node) {
	  List<Node<T,Integer>> adjacentNodes = getNeighbors(node);
	  for (Node<T,Integer> target : adjacentNodes) {
	    if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)){
	      distance.put(target, getShortestDistance(node) + getDistance(node, target));
	      predecessors.put(target, node);
	      unSettledNodes.add(target);
	    }
	  }	
  }
  
  private int getDistance(Node<T,Integer> node, Node<T,Integer> target) {
	  for (Edge<T,Integer> edge : edges) {
	    if (edge.getTail().equals(node) && edge.getHead().equals(target)) {
	    	return edge.getLabel();
	    }
	   }
	  throw new RuntimeException("Should not happen");
  }
  
  private List<Node<T,Integer>> getNeighbors(Node<T,Integer> node) {
	  List<Node<T,Integer>> neighbors = new ArrayList<Node<T,Integer>>();
	  for (Edge<T,Integer> edge : edges) {
	    if (edge.getTail().equals(node) && !isSettled(edge.getHead())) {
	       neighbors.add(edge.getHead());
	     }
	  }
	  return neighbors;
  }
  
  private Node<T,Integer> getMinimum(Set<Node<T,Integer>> vertexes) {
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
  
  private boolean isSettled(Node<T,Integer> vertex) {
	  return settledNodes.contains(vertex);
  }
  
  private int getShortestDistance(Node<T,Integer> destination) {
	  Integer d = distance.get(destination);
	  if (d == null) {
	    return Integer.MAX_VALUE;
	  } else {
	    return d;
	  }
  }
  

  /**
   * This method returns the path from the source to the selected target and
   * null if no path exists
   */
  public LinkedList<Node<T,Integer>> getPath(Node<T,Integer> target) {
	 LinkedList<Node<T,Integer>> path = new LinkedList<Node<T,Integer>>();
	 Node<T,Integer> step = target;
	  // check if a path exists
	  if (predecessors.get(step) == null) {
	    return null;
	  }
	  path.add(step);
	  while (predecessors.get(step) != null) {
	    step = predecessors.get(step);
	    path.add(step);
	  }
	  // Put it into the correct order
	  Collections.reverse(path);
	  return path;
	}
  }