// ***********************************************************************
// MIGUEL AMIGOT GONZALEZ - 10.5.2013
// List implementation of a Queue
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

import java.util.ArrayList;
import java.util.List;

public class QueueList<E> implements Queue<E> {
	protected QueueListNode firstNode; //Different class for nodes
	public int theSize = 0;

	//Initialize a QueueList that's either empty or one that already contains a first node
	// e.g. QueueList<String> sl = new QueueList<String>(new QueueListNode("firstSir"));
	public QueueList(QueueListNode n) { firstNode = n ; }
	public QueueList() { firstNode = null; }

	// Basic operations
	//Efficiency: O(1)
	public int size() {
		return theSize;
	}
	//Efficiency: O(1)
	public void enqueue(E element) {
		//New elements become the first nodes
		//Only accepted if they're not equal to null
		if(element != null){
			if(firstNode != null){
				//Create a first node whose reference is the current first node
				firstNode = new QueueListNode(element, firstNode);
				//Increase the size of the queue
				this.theSize++;
			}else{
				firstNode = new QueueListNode(element);
				this.theSize++;
			}
		}else{
			System.out.println("Can't enter a null");
		}
	}
	//Efficiency: O(n) because it has to shift through all of the nodes
	public E dequeue() throws InvalidOperationException {
		if(this.theSize>0){
			if(this.theSize == 1){
				//Return the value, set the node to null and decrease the size
				E giveBack = (E) this.firstNode.value;
				this.firstNode = null;
				this.theSize--;
				return giveBack;
			}else{
				//Decrease the size and call the firstNode's function
				this.theSize--;
				return (E) this.firstNode.dequeue();
			}
		}else{
			throw new InvalidOperationException("Queue is empty");
		}
	}

	//Efficiency: O(1)
	public boolean isEmpty() {
		return this.theSize == 0;
	}

	//Output operations
	//Recursively format every value of the stack into a String
	//Efficiency: O(n)
	public String toString() {
		if(firstNode == null){
			return "[]";
		}else{
			return "[" + firstNode.toString() + "]";
		}
	}
	//Efficiency: O(n) because it utilizes .toString()
	public void PrettyPrint() {
		System.out.println(this.toString());
	}
}