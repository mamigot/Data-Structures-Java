
// ***********************************************************************
//
// Testa11 : queues (null characters)
// Check how the q's methods work for null characters
//
// ***********************************************************************

public class Testa11 extends TestHarness {

	public Testa11(String s) { super(s); }

	public boolean test() {
		
		QueueList<Integer> q = new QueueList<Integer>(); //wrapper because generics will not accept primitive types
		boolean popMethod; //no exception should be thrown because q will not be empty
		boolean isEmptyMethod; //if empty then false
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		boolean sizeMethod; //if it's two then true
		
		for(int i=0; i<6; i++){
			q.enqueue(null);
			q.enqueue(i);
		}
		//theSize should be 5 because it shouldn't accept nulls
		sizeMethod = q.theSize == 5; //will be overwritten later if the size method is wrong
		
		try {
			for(int i=0; i<6; i++){
				q.dequeue();
			}
			popMethod = true; //If an exception is thrown then bad
		} catch (InvalidOperationException e) {
			popMethod = false; //An exception cannot be thrown
			e.printStackTrace();
		}
		
		q.enqueue(null);
		q.enqueue((int) '5'); //Cast a character to an int (its int value is 53)
		
		//Should only have the integer value of the character '4'
		isEmptyMethod = ! q.isEmpty();
		toStringMethod = q.toString().equals("[ \"53\" ]");
		q.PrettyPrint();
		pushMethod = ! q.isEmpty();
		sizeMethod = q.size() == 1;
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}