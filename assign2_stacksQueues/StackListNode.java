// MIGUEL AMIGOT GONZALEZ - 10.5.2013

import java.util.ArrayList;
import java.util.List;

public class StackListNode<E> {
	protected E value;
	protected StackListNode next;

	//Construct either a node with a link to the next element
	//or one without it
	public StackListNode(E v, StackListNode n) {
		this.value = v;
		this.next = n;
	}
	public StackListNode(E v) {
		this.value = v;
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
