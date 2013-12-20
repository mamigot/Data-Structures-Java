/**
 * The iterator for the BST map.
 * <p>
 * Default is INORDER but may also be PREORDER.
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

import java.util.*;

public class BSTMapInorderIterator<T extends Comparable<T>> implements Iterator<T> {

	/**
	 * Stack containing the elements of the BST.
	 */
	protected Stack<BSTMapNode<T,?>> stack;
	/**
	 * BSTMapNode that allows the user to call remove on the iterator if it's not null.
	 */
	protected BSTMapNode<T,?> nextTracker;
	/**
	 * Specifies the current version of the iterator. Useful to determine whether
	 * the class's iterator may be used.
	 */
	protected int versionNumberIterator;
	/**
	 * Enums which may be modified to specify an INORDER or PREORDER traversal.
	 */
	VisitorOrder visitOrder;
	/**
	 * Reference to the main BST. Useful to call its remove method.
	 */
	protected BSTMap<T,?> mainBST;

	/**
	 * Constructor for the iterator called from the main BSTMap class.
	 * <p>
	 * Sets the relevant instance variables to the parameters.
	 * 
	 * @param root		BSTMapNode specifying the root of the tree.
	 * @param refBST		Reference to the main BSTMap class.
	 * @param versionNumberBSTmap		Integer to keep track of the current version of the iterator.
	 * @param v		Enums data type which determines whether the iterator will be INORDER or PREORDER.
	 */
	protected BSTMapInorderIterator(BSTMapNode<T, ?> root, BSTMap<T,?> refBST, int versionNumberBSTmap, VisitorOrder v){ //Takes also the reference to the BST whose remove() we'll call
		//Populate the instantiated stack with the keys from the inOrder traversal

		visitOrder = v;
		stack = new Stack<BSTMapNode<T,?>>();
		if(root != null){
			//Choose INORDER or PREORDER depending on the data provided by the parameter to the constructor
			if(visitOrder == VisitorOrder.INORDER) populateStackInOrder(root);
			if(visitOrder == VisitorOrder.PREORDER) populateStackPreOrder(root);
		}

		//Save "root" as an instance variable for the remove method
		mainBST = refBST;
		versionNumberIterator = versionNumberBSTmap;

	}

	/**
	 * Populates the stack through an in order traversal.
	 * 
	 * @param N			initially corresponds to the root of the BST, but defined by
	 * 					recursive calls as subtrees of the BST.
	 */
	private void populateStackInOrder(BSTMapNode<T,?> N){
		if(N.left != null) populateStackInOrder(N.left);
		if(N != null) stack.push(N);
		if(N.right != null) populateStackInOrder(N.right);
	}

	/**
	 * Populates the stack through a pre order traversal.
	 * 
	 * @param N			initially corresponds to the root of the BST, but defined by
	 * 					recursive calls as subtrees of the BST.
	 */
	private void populateStackPreOrder(BSTMapNode<T,?> N){
		if(N != null) stack.push(N);
		if(N.left != null) populateStackPreOrder(N.left);
		if(N.right != null) populateStackPreOrder(N.right);
	}

	/**
	 * Determines whether the iterator has a next element.
	 * 
	 * @return true if the size of {@link #stack} is greater than 0 and the next element is not null.
	 * @throws RuntimException if the version of the iterator is not the same as that of the BSTMap
	 */
	public boolean hasNext() throws RuntimeException{ //Have to make sure that the first element is not null either
		if(mainBST.versionNumberBSTmap != versionNumberIterator) throw new RuntimeException("Can't use the iterator because the underlying data structure has been modified.");
		return stack.size() > 0 && stack.peek() != null;
	}

	/**
	 * Pops the next element from {@link #stack} and saves it in {@link #nextTracker}.
	 * 
	 * @return The key of the node in {@link #nextTracker} if this is not null. Otherwise, null.
	 * @throws RuntimException if the version of the iterator is not the same as that of the BSTMap
	 */
	public T next() throws RuntimeException{
		if(mainBST.versionNumberBSTmap != versionNumberIterator) throw new RuntimeException("Can't use the iterator because the underlying data structure has been modified.");
		if(hasNext()){
			BSTMapNode<T,?> N = stack.pop();
			nextTracker = N;
			return N.key;
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
		if(mainBST.versionNumberBSTmap != versionNumberIterator) throw new RuntimeException("Can't use the iterator because the underlying data structure has been modified.");
		//It has to be removed from the BST
		//Call the host's remove method with removeKey
		try {
			if(nextTracker != null && nextTracker.key != null){
				boolean res = mainBST.remove(nextTracker.key);
				//Increment the iterator after removing it (but the main class will also get incremented)
				//Set the tracker to null to avoid the possibility of the user calling remove() twice in a row
				if(res){ nextTracker = null; versionNumberIterator++; }

			}else throw new RuntimeException("Wasn't removed because .next() hasn't been called.");
		} catch (SortedMapException e) {
			System.out.println("Error calling the iterator's remove() method");
			e.printStackTrace();
		}
	}


}
