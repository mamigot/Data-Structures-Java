////////////////////////////////////////////////////////////////////////////////
//The interface for a SORTEDMAP
//YOU DO NOT NEED TO MODIFY THIS CLASS
////////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;

public interface SortedMap<K extends Comparable<K>,V> extends Iterable<K> {
    public void put(K key, V value) throws SortedMapException;
    public V get(K key) throws SortedMapException;
    public boolean remove(K key) throws SortedMapException;
    public SortedMap<Integer,Integer> calculateStats();
}
