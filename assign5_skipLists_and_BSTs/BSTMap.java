/**
 * The main class for a binary search tree (BST) that implements a sorted map.
 * <p>
 * Implements a generic BST key-value map with {@link #put()}, {@link #get()} and {@link #remove()} functions. Additionally,
 * an iterator that implements PREORDER or INORDER traversals through the {@link #iterator()}
 * method. The default traversal is INORDER but it may be specified by the user
 * through {@link #setIteratorDefault()};
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BSTMap<K extends Comparable<K>,V> implements SortedMap<K,V> {
	
	/**
	 * Saves the preferred way of traversing the BST. May be PREORDER or INORDER.
	 */
	VisitorOrder defaultVisitOrder;
	/**
	 * Node that saves the root of the BST and all of the subtrees.
	 */
	private BSTMapNode<K,V> root;
	/**
	 * Specifies the current version of the BST map. Useful to determine whether
	 * the class's iterator may be used.
	 */
	protected int versionNumberBSTmap;
	/**
	 * Counter variable detailing the size of the BST map.
	 */
	private int BSTsize;
	/**
	 * Sorted map which stores the statistics provided by {@link #calculateStats()}
	 */
	protected SortedMap<Integer, Integer> mapLevelNodes;
	
	/**
	 * Class constructor.
	 * <p>
	 * Sets the root to null, specifies that the default traversal will be INORDER and initializes
	 * the {@link #BSTsize} and {@link #versionNumberBSTmap} counters.
	 */
	public BSTMap(){ root = null; defaultVisitOrder=VisitorOrder.INORDER; BSTsize=0; versionNumberBSTmap=0; }
	
	/**
	 * Getter for the BST's size variable.
	 * @return integer variable of the size.
	 */
	public int getSize(){ return BSTsize; }

	/**
	 * Used to insert key-value pairs into the sorted map.
	 * <p>
	 * May not accept a null key or value. Increments the size variable of the BST.
	 * <p>
	 * Uses {@link #add(K, V, BSTMapNode<K,V>)} to recursively add the nodes.
	 * 
	 * @param key	generic key which implements Comparable.
	 * @param value	generic value.
	 * @throws SortedMapException if the provided key or value is null.
	 */
	public void put(K key, V value) throws SortedMapException {
		//Cannot accept null values
		if(key==null || value==null) throw new SortedMapException("Neither 'key' nor 'value' may be null!");
		
		//If the BST is empty set up the root
		if(root==null){
			root = new BSTMapNode<K, V>(key, value, null, null, null);
			BSTsize++; versionNumberBSTmap++;
		} else {
			//Rely on the recursive add method
			boolean added = add(key, value, root);
			//Keep track of the version of the BST
			if(added){ versionNumberBSTmap++; }
		}
	}
	
	/**
	 * Helper method for {@link #put(K, V)} to recursively add nodes to the BST.
	 * <p>
	 * If the new key corresponds to an existing node, update its value.
	 * 
	 * @param addKey	generic key which implements Comparable.
	 * @param addValue	generic value.
	 * @param N			initially corresponds to the root of the BST, but defined by
	 * 					recursive calls as subtrees of the BST. 
	 * @return			true if successfully added or updated, false if not.
	 */
	private boolean add(K addKey, V addValue, BSTMapNode<K, V> N) {
		//Compare the comparable keys
		int ct = addKey.compareTo(N.key);
		//There shall be no repeats so do nothing if they're the same
		if(ct != 0){
			if(ct > 0){
				if(N.right == null){
					//Set the new node to the right of this one (remember to give it the parent)
					N.right = new BSTMapNode<K, V>(addKey, addValue, null, null, N);
					BSTsize++;
					return true;
				}else{
					//Recursive call with node to its right
					return add(addKey, addValue, N.right);
				}
			}else{ //if (ct < 0)
				if(N.left == null){
					//Set the new node to this one's left
					N.left = new BSTMapNode<K, V>(addKey, addValue, null, null, N);
					BSTsize++; 
					return true;
				}else{
					//Recursive call with node to its left
					return add(addKey, addValue, N.left);
				}
			}
		}else if(ct == 0){
			//Update the value!
			N.value = addValue;
			return true;
		}else{
			//Return false because it's not getting added!
			return false;
		}
	}

	/**
	 * Used to obtain the value corresponding to a key in the BST.
	 * <p>
	 * May not accept a null key.
	 * 
	 * Uses {@link #dig(K, BSTMapNode<K,V>)} to recursively search through the nodes.
	 * 
	 * @param key	generic key which implements Comparable.
	 * @return		generic value corresponding to the key in the BST if it exists.
	 * 				Otherwise, null.
	 * @throws SortedMapException if the provided key is null.
	 */
	public V get(K key) throws SortedMapException {
		if(key==null) throw new SortedMapException("Cannot get a 'null' key");
		if(root == null) return null; //Empty root returns a null value
		else{
			//Call helper method to recursively find node
			BSTMapNode<K,V> result = dig(key, root);
			//Return the value of the node if it's not null
			if(result != null) return result.value;
			else return null;
		}
	}
	
	/**
	 * Helper method for {@link #get(K)} to recursively search through nodes of the BST.
	 * <p>
	 * If the key corresponds to an existing node N, return N.
	 * 
	 * @param digKey	generic key which implements Comparable.
	 * @param N			initially corresponds to the root of the BST, but defined by
	 * 					recursive calls as subtrees of the BST. 
	 * @return			the node if it was successfully found. Otherwise, null.
	 */
	private BSTMapNode<K,V> dig(K digKey, BSTMapNode<K,V> N){
		//If the node is null do not attempt to traverse through it!
		if(N == null) return null;
		
		//Return the found key if it corresponds to that provided by the parameter
		int ct = digKey.compareTo(N.key);
		if(ct == 0) return N;
		
		//Nodes corresponding to greater keys will fall to the current's right
		//(lower-valued keys fall to the left)
		else if(ct>0) return dig(digKey,N.right);
		else return dig(digKey,N.left);
	}

	/**
	 * Used to remove the node corresponding to a key in the BST.
	 * <p>
	 * May not accept a null key.
	 * 
	 * Uses {@link #takeOut(K, BSTMapNode<K,V>)}, {@link #deleteLeaf(BSTMapNode<K,V>)} and
	 * {@link #shiftNodeUp(BSTMapNode<K,V>)} to recursively search through the nodes, delete
	 * the one that corresponds to the provided key and re-adjust the BST.
	 * 
	 * @param key	generic key which implements Comparable.
	 * @return		true if the node was successfully deleted, false if not.
	 * @throws SortedMapException if the provided key is null.
	 */
	public boolean remove(K key) throws SortedMapException {
		if(key==null) throw new SortedMapException("Cannot remove a 'null' key");
		//Most simple case: if it's the root and it's a leaf
		if(key.equals(root.key) && root.isLeaf()){
			root = null;
			versionNumberBSTmap++;
			BSTsize--;
			return true;
		}else{
			//true if successfully deleted, false if not
			boolean success = takeOut(key, root);
			if(success){ BSTsize--; versionNumberBSTmap++; }
			return success;
		}
	}
	
	/**
	 * Helper method for {@link #remove(K)} to recursively search through nodes of the BST.
	 * <p>
	 * If the key corresponds to an existing node N, call {@link #deleteLeaf(BSTMapNode<K,V>)}
	 * if it is a leaf or {@link #shiftNodeUp(BSTMapNode<K,V>)} to re-adjust the tree by
	 * overriding the nodes that need to be deleted.
	 * 
	 * @param toRemoveKey	generic key which implements Comparable.
	 * @param N				initially corresponds to the root of the BST, but defined by
	 * 						recursive calls as subtrees of the BST. 
	 * @return				true if the node was successfully deleted, false if not.
	 * @throws SortedMapException because of {@link #deleteLeaf(BSTMapNode<K,V>)}
	 */
	private boolean takeOut(K toRemoveKey, BSTMapNode<K,V> N) throws SortedMapException{
		//Reached the end without finding it
		if(N==null){ return false; }
		
		int ct = toRemoveKey.compareTo(N.key);
		
		if(ct>0){
			return takeOut(toRemoveKey,N.right);
		}else if(ct<0){
			return takeOut(toRemoveKey,N.left);
		}else{
			//The element has been found (similar keys)
			if(N.isLeaf()){ 
				//Element is a leaf so just need to erase it
				deleteLeaf(N);
			}else if(N.left == null){
				//Doesn't have a left child
				shiftNodeUp(N.right);
			}else if(N.right == null){
				//Doesn't have a right child
				shiftNodeUp(N.left);
			}else{
				//Neither right nor left are null
				//There is a rightmost descendant
				BSTMapNode<K,V> rightmost = N.left.getRightMostDescendant();
				//"Updating in place makes it easier to handle the case of the root"
				N.value = rightmost.value;
				N.key = rightmost.key;
				
				//"Now deal with the rightmost node and its children"
				if(rightmost.isLeaf()){
					deleteLeaf(rightmost);
				}else{
					shiftNodeUp(rightmost.left);
				}
			}
			return true;
		}
	}
	
	/**
	 * Helper method for {@link #takeOut(K, BSTMapNode<K,V>)} to simply delete leafs of the BST.
	 * <p>
	 * If the key corresponds to an existing node, set it to null.
	 * 
	 * @param L		corresponds to the node of the BST that must be deleted.
	 * @throws SortedMapException if L is not a leaf and thus the BST needs to be re-adjusted.
	 */
	private void deleteLeaf(BSTMapNode<K,V> L) throws SortedMapException{
		if(!L.isLeaf()) throw new SortedMapException("L is not a leaf");
		if(L == L.parent.left){ //Checking if L is its parent's left child
			L.parent.left = null; //The parent no longer has a left child
		}else{ //Checking if L is its parent's right child
			L.parent.right = null;
		}
	}
	
	/**
	 * Helper method for {@link #takeOut(K, BSTMapNode<K,V>)} to adjust the BST's hierarchy.
	 * <p>
	 * Shifts a node into its parent if it has only one child and the parent is not null.
	 * 
	 * @param N		corresponds to the node of the BST that must be shifted.
	 * @throws SortedMapException if N's parent is null or if it has more than one child.
	 */
	private void shiftNodeUp(BSTMapNode<K,V> N) throws SortedMapException{
		if(N.parent == null) throw new SortedMapException("Can't shift up root node");
		if(!N.parent.hasOneChild()) throw new SortedMapException("Can't shift into full parent");
		
		//Move the values and the children
		//Take N's value and give it to its parent
		N.parent.value = N.value;
		//Bypass N and access its children
		N.parent.left = N.left;
		N.parent.right = N.right;
		
		//Depends on whether you're the left child or the next child
		if(N.parent.left != null){
			N.parent.left.parent = N.parent;
		}
		if(N.parent.right != null){
			N.parent.right.parent = N.parent;
		}
	}
	
	/**
	 * Used to concatenate string representations of nodes in the BST.
	 */
	private String inOrderTot = "";
	
	/**
	 * Provides a string, INORDER representation of the BST.
	 * <p>
	 * Uses {@link #inOrderTraversal(BSTMapNode<K,V>)} to traverse through the nodes
	 * and fill the string.
	 * 
	 * @return string representation of the tree if its root is not null. If it is, then returns "(null)".
	 */
	public String toString(){
		if(root == null) return "(null)";
		else{
			//In order traversal
			//Empty out the instance variable
			this.inOrderTot = "";
			return inOrderTraversal(root);
		}
	}
	
	/**
	 * Helper method for {@link #toString()} to perform an INORDER traversal for the BST
	 * <p>
	 * Recursively traverse through the tree and build up {@link #inOrderTot} with string
	 * representations of the nodes and line breaks. 
	 * 
	 * @param N		corresponds to the node of the BST that is traversed.
	 * @returns		string representing the nodes of the BST in an INORDER manner.
	 */
	private String inOrderTraversal(BSTMapNode<K,V> N){
		//Ensure that nothing is null before we call its properties
		if(N.left != null) inOrderTraversal(N.left);
		if(N != null) this.inOrderTot += N.toString()+ "\n";
		if(N.right != null) inOrderTraversal(N.right);

		return this.inOrderTot;
	}
	
	/**
	 * Sets {@link #defaultVisitOrder} to INORDER or PREORDER if these values are provided
	 * by the user. 
	 * 
	 * @param v		Enum data-type whose properties specify the available traversals.
	 * @throws SortedMapException if the parameters neither contain PREORDER or INORDER.
	 */
	public void setIteratorDefault(VisitorOrder v) throws SortedMapException {
        if (v != VisitorOrder.PREORDER && v != VisitorOrder.INORDER){
        	throw new SortedMapException("Iterator type not implemented");
        }
        defaultVisitOrder = v;
    }

	/**
	 * Constructs an iterator for this version of the BST (detailed by {@link #versionNumberBSTmap})
	 * that is INORDER or PREORDER, depending on the current value of {@link #defaultVisitOrder}.
	 * 
	 * @returns a BSTMapInorderIterator<K> object.
	 */
	public Iterator<K> iterator() {
		//It's not enough to pass it the Node (root), but we have to pass it a reference to this object
		//if we want to use its remove method
		return new BSTMapInorderIterator<K>(root, this, versionNumberBSTmap, defaultVisitOrder);
	}
	
	/**
	 * Calculates relevant statistics to analyze the performance of the BST.
	 * <p>
	 * Uses {@link #fillMapInOrder(BSTMapNode<K,V>)} to traverse through the nodes
	 * and calculate the "# of Steps to reach element" and the "# of elements reachable
	 * in that # of steps".
	 * 
	 * @returns a filled {@link #mapLevelNodes}
	 */
	public SortedMap<Integer, Integer> calculateStats() {
		//construct a new BST to host our statistics
		mapLevelNodes = new BSTMap<Integer, Integer>();
		if(root != null){
			fillMapInOrder(root, 1);
			return mapLevelNodes;
		}else return null; //return a null object for an empty BST
	}
	
	/**
	 * Helper method for {@link #calculateStats()} to update the statistics on the BST
	 * <p>
	 * Recursively traverse through the tree (INORDER) and stores the data in {@link #mapLevelNodes}.
	 * 
	 * @param N			initially corresponds to the root of the BST, but defined by
	 * 					recursive calls as subtrees of the BST. 
	 * @param level		corresponds to the keys in the BST, or the current number of steps
	 * 					required to reach an element.
	 */
	private void fillMapInOrder(BSTMapNode<K,V> N, int level){
		//Increase the level counters when going down a level and increase them when going up
		if(N.left != null){
			level++;
			fillMapInOrder(N.left, level);
			level--;
		}
		try{
			//check if the level is in mapLevelNodes
			//if it is, then increment
			//if it's not, create a key for it and update
			if(N != null){ //don't count the root; already did before								
				if(mapLevelNodes.get(level) != null){
					Integer currentVal = mapLevelNodes.get(level);			
					//Integer objects are immutable so can't increment with ++
					Integer newVal = new Integer(currentVal.intValue() + 1);
					mapLevelNodes.put(level, newVal);
				}else{
					mapLevelNodes.put(level, 1);
				};
			}
		}catch(Exception e){
			System.out.println("Error calculating stats");
		}
		if(N.right != null){
			level++;
			fillMapInOrder(N.right, level);
			level--;		
		}
	}

}
