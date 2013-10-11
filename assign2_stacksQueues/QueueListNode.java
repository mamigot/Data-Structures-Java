// MIGUEL AMIGOT GONZALEZ - 10.5.2013
import java.util.ArrayList;
import java.util.List;

public class QueueListNode<E> {
	protected E value;
	protected QueueListNode next;
	
	//Construct either a node with a link to the next element
	//or one without it
	public QueueListNode(E v, QueueListNode n) {
		this.value = v;
		this.next = n;
	}
	public QueueListNode(E v) {
		this.value = v;
	}

	//Removes the last node from the list and returns it
	//Concern: since we are just setting the QueueListNode in the base case to null,
	//it's still being linked to by the newer node. Shouldn't be a problem though.
	public E dequeue() {
		//Base case: if the element in the linked-list has a link of "null"
		if(this.next == null){
			//Save its value in the returnVal variable and set it to null
			E returnVal = this.value;
			this.value = null;
			return returnVal;
		}else{
			//Recursively call on the next link
			return (E) this.next.dequeue();
		}
	}
	//Iterate through the levels of nodes recursively
	public String toString() {
		if (next  == null) {
		    return  " \"" + value + "\" ";
		} else {
		    return  " \"" + value + "\" ;" + next.toString();
		}
	}

}
