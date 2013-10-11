
// ***********************************************************************
//
// Test6 : Stacks (>1 element)
// Check how the stack's methods work for more than 1 element-stacks of Integers
//
// ***********************************************************************

public class Test6 extends TestHarness {

	public Test6(String s) { super(s); }

	public boolean test() {
		
		StackList<Integer> stack = new StackList<Integer>(); //wrapper because generics will not accept primitive types
		boolean popMethod; //no exception should be thrown because stack will not be empty
		boolean isEmptyMethod; //if empty then false
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		boolean sizeMethod; //if it's two then true
		
		for(int i=0; i<11; i++){
			stack.push(i);
		}
		sizeMethod = stack.theSize == 10; //will be overwritten later if the size method is wrong
		
		try {
			for(int i=0; i<11; i++){
				stack.pop();
			}
			popMethod = true; //If an exception is thrown then bad
		} catch (InvalidOperationException e) {
			popMethod = false; //An exception cannot be thrown
			e.printStackTrace();
		}
		
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		isEmptyMethod = ! stack.isEmpty();
		toStringMethod = stack.toString().equals("[ \"5\" ; \"4\" ; \"3\" ]");
		stack.PrettyPrint();
		pushMethod = ! stack.isEmpty();
		sizeMethod = stack.size() == 3;
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}