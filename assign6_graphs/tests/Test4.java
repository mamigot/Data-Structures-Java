/**
 * Test4 -- test class extending {@link TestHarness}
 * <p>
 * Tests the addNode(T lab) method. Tries to input a null parameter, an
 * already-existing element and a non-existing element into the graph.
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

public class Test4 extends TestHarness {

    public Test4(String s) { super(s); }

    public boolean test() {
    	Graph<String,Integer> g = new Graph<String,Integer>();
    	
    	//null parameter
    	boolean nullTest = false;
    	try {
			g.addNode(null);
		} catch (InvalidOperationException e) {
			//Should throw an exception
			nullTest = true;
		}
    	
    	//non-existing node
    	boolean nonExistingTest = false;
    	try {
			g.addNode("dawg");
			nonExistingTest = true;
		} catch (InvalidOperationException e) {
			//shouldn't throw an exception here
			nonExistingTest = false;
		}
    	
    	//existing node
    	boolean existingTest = false;
    	try {
			g.addNode("dawg");
		} catch (InvalidOperationException e) {
			//better throw an exception here
			existingTest = true;
		}
    

    	System.out.println(g.toString());
    	return nullTest && existingTest && nonExistingTest;
    }

}