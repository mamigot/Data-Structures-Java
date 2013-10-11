
// ***********************************************************************
//
// Test5 : Stacks (1 element)
// Check how the stack's methods work for 1 element-stacks of Characters
//
// ***********************************************************************

public class Test5 extends TestHarness {

	public Test5(String s) { super(s); }

	public boolean test() {
		
		StackList<Character> stack = new StackList<Character>();
		boolean popMethod; //no exception should be thrown because stack will not be empty
		boolean isEmptyMethod; //if empty then false
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		boolean sizeMethod; //if it's two then true
		
		stack.push('a');
		try {
			stack.pop();
			popMethod = true; //If an exception is thrown then bad
		} catch (InvalidOperationException e) {
			popMethod = false; //An exception cannot be thrown
			e.printStackTrace();
		}
		stack.push('!');
		
		isEmptyMethod = ! stack.isEmpty();
		toStringMethod = stack.toString().equals("[ \"!\" ]");
		stack.PrettyPrint();
		stack.push('c');
		pushMethod = ! stack.isEmpty();
		sizeMethod = stack.size() == 2;
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}