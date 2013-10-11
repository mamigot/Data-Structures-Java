// ***********************************************************************
// MIGUEL AMIGOT GONZALEZ - 10.5.2013
// List implementation of a Stack
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

public class StackList<E> implements Stack<E> {
	protected StackListNode firstNode;
	public int theSize = 0;
	
	//Initialize a StackList that's either empty or one that already contains a first node
	// e.g. StackList<String> sl = new StackList<String>(new StackListNode("firstSir"));
    public StackList(StackListNode n) { firstNode = n ; }
    public StackList() { firstNode = null; }

    // Basic operations
    //Efficiency: O(1)
    public int size() {
		return theSize;
	}
    //Efficiency: O(1)
    public void push(E element) {
    	//New elements become the first nodes
    	//Only accepted if they're not equal to null
    	if(element != null){
    		//Create a first node whose reference is the current first node
    		firstNode = new StackListNode(element, firstNode);
    		//Increase the size of the stack
    		this.theSize++;
    	}else{
    		System.out.println("Can't enter a null");
    	}
    }
    //Efficiency: O(1)
    public E pop() throws InvalidOperationException {
    	if(this.theSize > 0){
    		//Prepare the value of the first node to return it
	    	E takeOut = (E) this.firstNode.value;
	    	//Set the first node to the previous node's next reference
	    	this.firstNode = this.firstNode.next;
	    	//Decrease the size of the StackList
	    	this.theSize--;
			return takeOut;
    	}else{
    		throw new InvalidOperationException("Stack is empty");
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