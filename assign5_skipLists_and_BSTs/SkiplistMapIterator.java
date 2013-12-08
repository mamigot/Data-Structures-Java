/**
 * The iterator for the skip-list.
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
import java.util.LinkedList;
import java.util.Queue;

public class SkiplistMapIterator<T extends Comparable<T>> implements Iterator<T> {

	/**
	 * Queue containing the elements of the BST.
	 */
	protected Queue<SkiplistMapNode<T,?>> queue;
	/**
	 * SkiplistMapNode that allows the user to call remove on the iterator if it's not null.
	 */
	protected SkiplistMapNode<T,?> nextTracker;
	/**
	 * Specifies the current version of the iterator. Useful to determine whether
	 * the class's iterator may be used.
	 */
	protected int versionNumberIterator;
	/**
	 * Reference to the main skip-list. Useful to call its remove method.
	 */
	protected SkiplistMap<T,?> mainSkipList;
	
	/**
	 * Constructor for the iterator called from the main SkiplistMap class.
	 * <p>
	 * Sets the relevant instance variables to the parameters.
	 * 
	 * @param head		SkiplistMapNode specifying the first node in the skip-list.
	 * @param main		Reference to the main SkiplistMap class.
	 * @param versionNumberSkipList		Integer to keep track of the current version of the iterator.
	 */
	protected SkiplistMapIterator(SkiplistMapNode<T,?> head, SkiplistMap<T,?> main, int versionNumberSkipList){
		//cannot instantiate a queue directly because it's an interface
		queue = new LinkedList<SkiplistMapNode<T,?>>();
		mainSkipList = main; 
		if(head != null) populateQueue(head);
		else queue = null;
		versionNumberIterator = versionNumberSkipList; //The versions of the skip-list and iterator are equal upon construction
	}
	
	/**
	 * Helper method for the constructor; populates the stack through a linear traversal.
	 * 
	 * @param head		corresponds to the first node in the skip-list.
	 */
	private void populateQueue(SkiplistMapNode<T,?> head){
		SkiplistMapNode<T,?> current = head;
		//Start at the lowest level of the head's array
		while(current.nodeRefsArray[0] != null){
			queue.add(current.nodeRefsArray[0]); //add the node to the queue!
			current = current.nodeRefsArray[0]; //keep going forward!
		}
	}
	
	/**
	 * Determines whether the iterator has a next element.
	 * 
	 * @return true if the {@link #queue} empty and its next element is not null.
	 * @throws RuntimException if the version of the iterator is not the same as that of the BSTMap
	 */
	public boolean hasNext() throws RuntimeException{
		if(mainSkipList.versionNumberSkipList != versionNumberIterator) throw new RuntimeException("Can't use the iterator because the underlying data structure has been modified.");
		else return !queue.isEmpty() && queue.peek() != null;
	}

	/**
	 * Removes the next element from {@link #queue} and saves it in {@link #nextTracker}.
	 * 
	 * @return The key of the node in {@link #nextTracker} if this is not null. Otherwise, null.
	 * @throws RuntimException if the version of the iterator is not the same as that of the BSTMap
	 */
	public T next() throws RuntimeException{
		if(mainSkipList.versionNumberSkipList != versionNumberIterator) throw new RuntimeException("Can't use the iterator because the underlying data structure has been modified.");
		if(!queue.isEmpty()){
			SkiplistMapNode<T,?> N = queue.remove(); //Save the node into the tracker
			nextTracker = N;
			return N.key; //return the key of the tracker
		}else{
			return null;
		}
	}

	/**
	 * Removes the node saved in {@link #nextTracker} if this is not null and returns its key.
	 * <p>
	 * Calls the main class's remove method with the returned key if it's not null. Must be preceded by a call to {@link #next()}.
	 * 
	 * @throws RuntimException if the version of the iterator is not the same as that of the BSTMap
	 */
	public void remove() throws RuntimeException{
		if(mainSkipList.versionNumberSkipList != versionNumberIterator) throw new RuntimeException("Can't use the iterator because the underlying data structure has been modified.");
		try {
			if(nextTracker != null && nextTracker.key != null){
				versionNumberIterator++; //Increment the iterator after removing it (but the main class will also get incremented)
				mainSkipList.remove(nextTracker.key);
				nextTracker = null; //Set the tracker to null to avoid the possibility of the user calling remove() twice in a row
			}else throw new RuntimeException("Wasn't removed because .next() hasn't been called.");
		} catch (SortedMapException e) {
			System.out.println("Error calling the iterator's remove() method");
			e.printStackTrace();
		}
	}

}
