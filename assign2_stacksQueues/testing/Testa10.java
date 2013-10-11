
// ***********************************************************************
//
// Testa10 : Stacks (null characters)
// Check how the stack's methods work for null characters
//
// ***********************************************************************

public class Testa10 extends TestHarness {

	public Testa10(String s) { super(s); }

	public boolean test() {
		
		StackList<Integer> stack = new StackList<Integer>(); //wrapper because generics will not accept primitive types
		boolean popMethod; //no exception should be thrown because stack will not be empty
		boolean isEmptyMethod; //if empty then false
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		boolean sizeMethod; //if it's two then true
		
		for(int i=0; i<6; i++){
			stack.push(null);
			stack.push(i);
		}
		//theSize should be 5 because it shouldn't accept nulls
		sizeMethod = stack.theSize == 5; //will be overwritten later if the size method is wrong
		
		try {
			for(int i=0; i<6; i++){
				stack.pop();
			}
			popMethod = true; //If an exception is thrown then bad
		} catch (InvalidOperationException e) {
			popMethod = false; //An exception cannot be thrown
			e.printStackTrace();
		}
		
		stack.push(null);
		stack.push((int) '4'); //Cast a character to an int (its int value is 52)
		
		//Should only have the integer value of the character '4'
		isEmptyMethod = ! stack.isEmpty();
		toStringMethod = stack.toString().equals("[ \"52\" ]");
		stack.PrettyPrint();
		pushMethod = ! stack.isEmpty();
		sizeMethod = stack.size() == 1;
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}