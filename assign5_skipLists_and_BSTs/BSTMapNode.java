/**
 * The main class for a binary search tree node (BSTMapNode).
 * <p>
 * Implements a generic BST key-value node.
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


public class BSTMapNode<K extends Comparable<K>,V> {

	/**
	 * Node's generic key, which implements Comparable.
	 */
	protected K key;
	/**
	 * Node's generic value.
	 */
	protected V value;
	/**
	 * Reference to the node's left child.
	 */
	protected BSTMapNode<K,V> left;
	/**
	 * Reference to the node's right child.
	 */
	protected BSTMapNode<K,V> right;
	/**
	 * Reference to the node's parent (null if it's the tree's root).
	 */
	protected BSTMapNode<K,V> parent;
	
	/**
	 * Class constructor.
	 * <p>
	 * Sets the node's key, value, left child, right child and parent values.
	 * 
	 * @param k		generic key which implements Comparable.
	 * @param v		generic value
	 * @param l		left descendant (BSTMapNode)
	 * @param r		right descendant (BSTMapNode)
	 * @param p		parent (BSTMapNode)
	 */
	protected BSTMapNode(K k,V v, BSTMapNode<K,V> l, BSTMapNode<K,V> r, BSTMapNode<K,V> p){
		key = k; value = v; left = l; right = r; parent = p;
	}
	
	/**
	 * States whether a node is a leaf of the tree.
	 * 
	 * @return true if its left and right references are null, false if otherwise.
	 */
	protected boolean isLeaf(){ return (left==null) && (right ==null); }
	/**
	 * States whether a node is the root of the tree.
	 * 
	 * @return true if its parent is null, false if otherwise.
	 */
	protected boolean isRoot(){ return parent==null; }
	/**
	 * Recursively traverses a tree to get a node's right-most descendant.
	 * 
	 * @return reference to the node's right-most descendant.
	 */
	protected BSTMapNode<K, V> getRightMostDescendant(){
		if(right == null) return this;
		else return right.getRightMostDescendant();
	}
	/**
	 * Determines whether a current node has one child.
	 * <p>
	 * Possible to have a left or a right reference that is not equal to null, but not both.
	 * 
	 * @return true if only left or right is equal to null, false if both or neither references are null.
	 */
	protected boolean hasOneChild() {
		return (left==null && right != null) || (left!=null && right==null);
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
