/**
 * Test3 -- test class extending {@link TestHarness}
 * <p>
 * Tests the findNode() method. Looks for "null", an existing
 * and a non-existing element.
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Miguel Amigot <ma2786@nyu.edu>
 * @version     Assignment 6
 * @since       2013-11-30
 */

public class Test3 extends TestHarness {

    public Test3(String s) { super(s); }

    public boolean test() {
    	Graph<String,Integer> g = new Graph<String,Integer>();
    	
    	//should return null if the parameter is null
    	boolean nullTest = (g.findNode(null) == null);
    	
    	try {
			g.addNode("dawg"); //add test node
		} catch (InvalidOperationException e1) {
			System.out.println("Failure in Test3");
		}
    	
    	//we just added it so it better be there
    	boolean existingTest = (g.findNode("dawg").getLabel().equals("dawg"));
    	
    	//shouldn't be there
    	boolean nonExistingTest = (g.findNode("not there") == null);

    	System.out.println(g.toString());
    	return nullTest && existingTest && nonExistingTest;
    }

}