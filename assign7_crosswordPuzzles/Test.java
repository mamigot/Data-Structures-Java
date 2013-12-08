// ***********************************************************************
//
// Test Class -- The main function that registers and executes tests.
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

public class Test {
    
    public static void main(String [] args) {
	Test1 t1 = new Test1("t1");

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// %%%%% Register your new tests here, by extending this method %%%%%
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	Test2 t2 = new Test2("t2");
	Test3 t3 = new Test3("t3");
	Test4 t4 = new Test4("t4");
	
	TestHarness.run();


    }
}
