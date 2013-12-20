/**
 * The main class for a skip-list that implements a sorted map.
 * <p>
 * Implements a generic key-value map with {@link #put()}, {@link #get()} and {@link #remove()} functions.
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

import java.util.Iterator;

public class SkiplistMap<K extends Comparable<K>,V> implements SortedMap<K,V> {

	/**
	 * Head node for the skip-list.
	 */
	public SkiplistMapNode<K,V> head;
	/**
	 * Specifies the current version of the skip-list. Useful to determine whether
	 * the class's iterator may be used.
	 */
	protected int versionNumberSkipList;
	/**
	 * Counter variable detailing the size of the BST map.
	 */
	private int sizeSkipList;
	/**
	 * Maximum number of levels that will be used (same as the size of {@link #head}'s array)
	 */
	protected final int maxNumberLevels = 32;
	/**
	 * Sorted map which stores the statistics provided by {@link #calculateStats()}
	 */
	protected SortedMap<Integer, Integer> mapLevelNodes;

	/**
	 * Class constructor.
	 * <p>
	 * Sets the head's key and value to null, sets its array of nodes to the size specified by {@link #maxNumberLevels} and initializes
	 * the {@link #sizeSkipList} and {@link #versionNumberSkipList} counters.
	 */
	public SkiplistMap() {
		head = new SkiplistMapNode<K,V>(null, null, maxNumberLevels);
		sizeSkipList = 0; //Initialize the counter variable
		versionNumberSkipList = 0;
	}

	/**
	 * Getter for the BST's size variable.
	 * @return integer variable of the size.
	 */
	public int getSize(){ return sizeSkipList; }

	/**
	 * Used to insert key-value pairs into the sorted map.
	 * <p>
	 * May not accept a null key or value. Increments the size variable of the skip-list.
	 * <p>
	 * Uses {@link #contains(K)} to check if a node corresponding to the existing key exits and its value
	 * simply needs to be updated and {@link #determineArraySize()} to determine the size of the node if it is new.
	 * 
	 * @param key	generic key which implements Comparable.
	 * @param value	generic value.
	 * @throws SortedMapException if the provided key or value is null.
	 */
	public void put(K key, V value) throws SortedMapException {
		//Cannot accept null values
		if(key==null || value==null) throw new SortedMapException("Neither 'key' nor 'value' may be null!");

		//Check if the node already exists
		SkiplistMapNode<K,V> existingNode = contains(key);
		if(existingNode != null){ //Update its value
			existingNode.value = value; versionNumberSkipList++;
			return; //end the method here
		}

		//Based on 50% probabilities
		int levels = determineArraySize();
		//Create a new node
		SkiplistMapNode<K,V> newNode = new SkiplistMapNode<K,V>(key, value, levels);

		//Find where it should be in the skip-list
		//Don't tamper with "head" -- use the "current" variable to traverse
		SkiplistMapNode<K,V> current = head;
		//Must modify all levels of head's array in the skip list
		for(int i=maxNumberLevels-1; i>=0; i--){
			//If the current array does not point to null at the position i, check it
			while(current.nodeRefsArray[i] != null){
				int ct = (key).compareTo(current.nodeRefsArray[i].key);
				if(ct < 0) break; //key is too great, no point in going through further
				current = current.nodeRefsArray[i]; //traverse forward
			}
			if(i<=levels-1){ //insert the new node between two existing nodes
				newNode.nodeRefsArray[i] = current.nodeRefsArray[i];
				current.nodeRefsArray[i] = newNode;
			}
		}
		sizeSkipList++; versionNumberSkipList++; //increment the size and version counters
	}

	/**
	 * Helper method for {@link #put(K, V)} to determine if a node corresponding to the provided key already exists.
	 * 
	 * @param key	generic key which implements Comparable.
	 * @return		SkiplistMapNode object whose key is equal to that provided in the parameter, null if it doesn't exist.
	 */
	private SkiplistMapNode<K,V> contains(K key){
		//Don't tamper with "head" -- use the "current" variable to traverse
		SkiplistMapNode<K,V> current = head;
		//To go in O(logN) time, start at the highest level of the head
		for(int i=maxNumberLevels-1; i>=0; i--){
			//While you don't find it, set its reference forward to traverse
			while(current.nodeRefsArray[i] != null){
				int ct = (key).compareTo(current.nodeRefsArray[i].key);
				if(ct < 0) break;
				if(ct == 0) return current.nodeRefsArray[i]; // return the relevant node
				current = current.nodeRefsArray[i]; //traverse forward
			}
		}
		return null; //if it was never found return null
	}

	/**
	 * Helper method for {@link #put(K, V)} to determine the size of the new node's level array.
	 * <p>
	 * There is a 50% probability that a node will have more than one level, (50%)^2 that it will have
	 * more than two, (50%)^3 that it will have more than three...
	 * 
	 * @return			integer specifying the number of levels.
	 */
	private int determineArraySize(){
		int numLevels = 1; //It will be of size 1 at least
		//--Do not go over the maximum number of levels
		while(Math.random() > 0.5 && numLevels < this.maxNumberLevels){
			numLevels++;
		}
		return numLevels; 
	}

	/**
	 * Used to obtain the value corresponding to a key in the skip-list.
	 * <p>
	 * May not accept a null key. Largely similar to {@link #contains(K)}. 
	 * 
	 * Uses {@link #dig(K, BSTMapNode<K,V>)} to recursively search through the nodes.
	 * 
	 * @param key	generic key which implements Comparable.
	 * @return		generic value corresponding to the key in the BST if it exists.
	 * 				Otherwise, null.
	 * @throws SortedMapException if the provided key is null.
	 */
	public V get(K key) throws SortedMapException {
		//Cannot accept null values
		if(key==null) throw new SortedMapException("Neither 'key' nor 'value' may be null!");
		//Don't tamper with "head" -- use the "current" variable to traverse
		SkiplistMapNode<K,V> current = head;
		//To go in O(logN) time, start at the highest level of the head
		for(int i=maxNumberLevels-1; i>=0; i--){
			//While you don't find it, set its reference forward to traverse
			while(current.nodeRefsArray[i] != null){
				int ct = (key).compareTo(current.nodeRefsArray[i].key);
				if(ct < 0) break;
				if(ct == 0) return current.nodeRefsArray[i].value; // return the relevant node's value
				current = current.nodeRefsArray[i];
			}
		}
		return null; //null if it hasn't been found
	}

	/**
	 * Used to remove the node corresponding to a key in the BST.
	 * <p>
	 * May not accept a null key.
	 * 
	 * Traverses through the skip-list and erases the appropriate references to the unwanted node.
	 * 
	 * @param key	generic key which implements Comparable.
	 * @return		true if the node was successfully deleted, false if not.
	 * @throws SortedMapException if the provided key is null.
	 */
	public boolean remove(K key) throws SortedMapException {
		//Cannot accept null values
		if(key==null) throw new SortedMapException("Neither 'key' nor 'value' may be null!");
		//Don't tamper with "head" -- use the "current" variable to traverse
		SkiplistMapNode<K,V> current = head;
		boolean found = false; //assume it's never going to be found
		for(int i=maxNumberLevels-1; i>=0; i--){
			while(current.nodeRefsArray[i] != null){
				int ct = (key).compareTo(current.nodeRefsArray[i].key);
				if(ct == 0){
					found = true; //set found to true if it has been found
					current.nodeRefsArray[i] = current.nodeRefsArray[i].nodeRefsArray[i]; //overwrite it!
					break; //out of the while loop!
				}
				if(ct < 0) break;
				current = current.nodeRefsArray[i]; //traverse forward!
			}
		}
		if(found) versionNumberSkipList++; sizeSkipList--; //decrease the size counter and increase the version counter!
		return found;
	}

	/**
	 * Provides a string, full representation of the skip-list.
	 * <p>
	 * Adds up the string from the lowest values of the node's arrays.
	 * 
	 * @return string representation of the skip-list if its head is not null. If it is, then returns "(null)".
	 */
	public String toString(){
		if(head.nodeRefsArray[0] == null) return "(null)";
		else{
			String tot = ""; //build up the tot string
			//Don't tamper with "head" -- use the "current" variable to traverse
			SkiplistMapNode<K,V> current = head;
			while(current.nodeRefsArray[0] != null){
				//get a string representation of the node and add it to the tot string
				tot += current.nodeRefsArray[0] + "\n";
				current = current.nodeRefsArray[0]; //traverse forward
			}
			return tot;
		}
	}

	/**
	 * Constructs an iterator for this version of the skip-list.
	 * 
	 * @return a BSTMapInorderIterator<K> object.
	 */
	public Iterator<K> iterator() {
		if(head == null) System.out.println("The SkipList is empty but here's an iterator anyway.");
		return new SkiplistMapIterator<K>(head, this, versionNumberSkipList);
	}

	/**
	 * Calculates relevant statistics to analyze the performance of the skip-list.
	 * <p>
	 * Uses {@link #traverseComparisons()} to traverse through the nodes linearly
	 * and calculates the "# of Steps to reach element" and the "# of elements reachable
	 * in that # of steps" through {@link #getComparisons(K)}.
	 * 
	 * @return a filled {@link #mapLevelNodes}
	 */
	public SortedMap<Integer, Integer> calculateStats() {
		if(head.nodeRefsArray[0] != null){
			return traverseComparisons();
		}else return null; //null if the skip-list is empty
	}

	/**
	 * Helper method for {@link #calculateStats()} to traverse through the nodes linearly.
	 * <p>
	 * Calls {@link #getComparisons(K)} on each node that is visited to fill {@link #mapLevelNodes}.
	 * 
	 * @return	a filled {@link #mapLevelNodes}
	 */
	private SortedMap<Integer, Integer> traverseComparisons(){
		//initialize mapLevelNodes to a new BST
		mapLevelNodes = new BSTMap<Integer, Integer>();
		//Don't tamper with "head" -- use the "current" variable to traverse
		SkiplistMapNode<K,V> current = head;
		//traverse through the skip-list linearly
		while(current.nodeRefsArray[0] != null){

			try {
				//Get a number of comparisons for that node (ind. variable)
				int numComparisons = getComparisons(current.nodeRefsArray[0].key);

				//Check if there is a value corresponding to that specific number of comparisons
				Integer res = mapLevelNodes.get(numComparisons);
				if(res == null) mapLevelNodes.put(numComparisons, 1); //First one
				else{ //Update the value
					Integer newVal = new Integer(res.intValue() + 1);
					mapLevelNodes.put(numComparisons, newVal);
				}

			} catch (SortedMapException e) { e.printStackTrace();}

			current = current.nodeRefsArray[0]; //traverse forward
		}
		return mapLevelNodes; //return the sorted map
	}

	/**
	 * Helper method for {@link #traverseComparisons()} to traverse through the nodes linearly.
	 * <p>
	 * Called for each node to calculate and update the number of comparisons that were made to arrive to it.
	 * 
	 * @return an integer containing the number of comparisons for a specific node
	 */
	private int getComparisons(K key) throws SortedMapException {
		//set the counter at 0
		int numComparisons = 0;
		//Don't tamper with "head" -- use the "current" variable to traverse
		SkiplistMapNode<K,V> current = head;
		for(int i=maxNumberLevels-1; i>=0; i--){
			while(current.nodeRefsArray[i] != null){
				int ct = (key).compareTo(current.nodeRefsArray[i].key);
				numComparisons++; //update the counter every time a comparison is made
				if(ct < 0) break;
				if(ct == 0) break;
				current = current.nodeRefsArray[i]; //traverse forward
			}
		}
		return numComparisons; //return the number of comparisons
	}

}
