/**
 * The main class for a skip-list node (SkiplistMapNode).
 * <p>
 * Implements a generic skip-list key-value node.
 * 
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Miguel Amigot <ma2786@nyu.edu>
 * @version     Assignment 5
 * @since       2013-11-22
 */

public class SkiplistMapNode<K extends Comparable<K>,V> {

	/**
	 * Node's generic key, which implements Comparable.
	 */
	protected K key;
	/**
	 * Node's generic value.
	 */
	protected V value;
	/**
	 * Array containing the references to the nodes of the skip-list.
	 */
	protected SkiplistMapNode<K,V>[] nodeRefsArray;

	/**
	 * Class constructor.
	 * <p>
	 * Sets the node's key, value, and array of a size equivalent to the provided number of levels.
	 */
	protected SkiplistMapNode(K k, V val, int level){
		key = k; value = val;
		nodeRefsArray = new SkiplistMapNode[level];
	}

	/**
	 * Returns a string representation of a single node, through its key and value.
	 * 
	 * @return string containing the node's key and value.
	 */
	public String toString(){
		return "\""+key+"\"\""+value+"\"";
	}

}
