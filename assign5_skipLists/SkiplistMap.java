// ***********************************************************************
//
// SkiplistMap -- Implements a sorted map using a skiplist
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

import java.util.Iterator;

public class SkiplistMap<K extends Comparable<K>,V> implements SortedMap<K,V> {

    //Implement this class

	@Override
	public V get(K key) throws SortedMapException {
		//Start at the highest level of the highways (left)
		//Look at the spot on the right (same level) that's closest to it
		//If the key you want (K) is less than that, go down on your current spot
		//If K is larger, go right to that spot and evaluate again
		//Stop at the lowest level present
		//If K is the same as that of the spot you're analyzing, you found it
		return null;
	}
	
	@Override
	public void put(K key, V value) throws SortedMapException {
		//Determine how big the new node's array will be
		//Leave it up to probability (give the option to have multiple)
		//Make the array once you know how tall it will be
		//Put the key and the value at the bottom and set the links accordingly
		//The links can be found in other node's arrays
		
	}

	@Override
	public boolean remove(K key) throws SortedMapException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SortedMap<Integer, Integer> calculateStats() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
