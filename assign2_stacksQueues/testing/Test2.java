// ***********************************************************************
//
// Test2 : confirms that we cannot pop from an empty stack.
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

public class Test2 extends TestHarness {

	public Test2(String s) { super(s); }

	public boolean test() { 
		QueueList<String> q = new QueueList<String>();

		try {
		    String t = q.dequeue();
		    return false;
		} catch (InvalidOperationException e) { return true; }

	}
}