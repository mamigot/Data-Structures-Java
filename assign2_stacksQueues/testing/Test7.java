
// ***********************************************************************
//
// Test7 : Queues (0 elements)
// Check how the queue's methods work for 0 element-queues of Strings
//
// ***********************************************************************

public class Test7 extends TestHarness {

	public Test7(String s) { super(s); }

	public boolean test() {
		
		QueueList<String> q = new QueueList<String>();
		boolean popMethod; //try-catch makes it true
		boolean sizeMethod; //if it's one then true
		boolean isEmptyMethod; //if empty then true
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		
		
		try {
			q.dequeue();
			popMethod = false; //If no exception is thrown then bad
		} catch (InvalidOperationException e1) {
			popMethod = true; //An exception must be thrown
			e1.printStackTrace();
		}

		sizeMethod = q.size() == 0;
		isEmptyMethod = q.isEmpty();
		toStringMethod = q.toString().equals("[]");
		q.PrettyPrint();
		q.enqueue("push");
		pushMethod = ! q.isEmpty();
		
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}