import java.lang.reflect.Array;

// ***********************************************************************
//
// Test4 : Stacks (0 elements)
// Check how the stack's methods work for 0 element-stacks of Strings
//
// ***********************************************************************

public class Test4 extends TestHarness {

	public Test4(String s) { super(s); }

	public boolean test() {
		
		StackList<String> stack = new StackList<String>();
		boolean popMethod; //try-catch makes it true
		boolean sizeMethod; //if it's one then true
		boolean isEmptyMethod; //if empty then true
		boolean toStringMethod; //check if it's printed correctly
		boolean pushMethod; //if it's not empty then true
		
		try {
			stack.pop();
			popMethod = false; //If no exception is thrown then bad
		} catch (InvalidOperationException e) {
			popMethod = true; //An exception must be thrown
			e.printStackTrace();
		}
		
		sizeMethod = stack.size() == 0;
		isEmptyMethod = stack.isEmpty();
		toStringMethod = stack.toString().equals("[]");
		stack.PrettyPrint();
		stack.push("push");
		pushMethod = ! stack.isEmpty();
		
		
		return popMethod && isEmptyMethod && pushMethod && toStringMethod && sizeMethod;
	}

}