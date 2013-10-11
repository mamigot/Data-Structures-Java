
// ***********************************************************************
//
// Test9 : Queues (>1 element)
// Check how the queue's methods work for more than 1 element-queues of Integers
//
// ***********************************************************************

public class Test9 extends TestHarness {

	public Test9(String s) { super(s); }

	public boolean test() {
		
		QueueList<Integer> q = new QueueList<Integer>(); //wrapper because generics will not accept primitive types
		boolean popMethod; //no exception should be thrown because q will not be empty
		boolean isEmptyMethod; //if empty then false
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		boolean sizeMethod; //if it's two then true
		
		for(int i=0; i<11; i++){
			q.enqueue(i);
		}
		sizeMethod = q.theSize == 10; //will be overwritten later if the size method is wrong
		
		try {
			for(int i=0; i<11; i++){
				q.dequeue();
			}
			popMethod = true; //If an exception is thrown then bad
		} catch (InvalidOperationException e) {
			popMethod = false; //An exception cannot be thrown
			e.printStackTrace();
		}
		
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		
		isEmptyMethod = ! q.isEmpty();
		toStringMethod = q.toString().equals("[ \"5\" ; \"4\" ; \"3\" ]");
		q.PrettyPrint();
		pushMethod = ! q.isEmpty();
		sizeMethod = q.size() == 3;
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}