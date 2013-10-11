
// ***********************************************************************
//
// Test8 : Queues (1 element)
// Check how the queue's methods work for 1 element-queues of Characters
//
// ***********************************************************************

public class Test8 extends TestHarness {

	public Test8(String s) { super(s); }

	public boolean test() {
		
		QueueList<Character> q = new QueueList<Character>();
		boolean popMethod; //no exception should be thrown because q will not be empty
		boolean isEmptyMethod; //if empty then false
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		boolean sizeMethod; //if it's two then true
		
		q.enqueue('a');
		try {
			q.dequeue();
			popMethod = true; //If an exception is thrown then bad
		} catch (InvalidOperationException e1) {
			popMethod = false; //An exception cannot be thrown
			e1.printStackTrace();
		}
		q.enqueue('!');
		
		isEmptyMethod = ! q.isEmpty();
		toStringMethod = q.toString().equals("[ \"!\" ]");
		q.PrettyPrint();
		q.enqueue('c');
		pushMethod = ! q.isEmpty();
		sizeMethod = q.size() == 2;
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}