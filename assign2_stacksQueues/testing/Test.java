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

		Test2 t2 = new Test2("t2");		
		Test3 t3 = new Test3("t3");

		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// %%%%% Register your new tests here, by extending this method %%%%%
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

		Test4 t4 = new Test4("t4");
		Test5 t5 = new Test5("t5");
		Test6 t6 = new Test6("t6");
		Test7 t7 = new Test7("t7");
		Test8 t8 = new Test8("t8");
		Test9 t9 = new Test9("t9");
		Testa10 t10 = new Testa10("t10");
		Testa11 t11 = new Testa11("t11");
		Testa12 t12 = new Testa12("t12");
		Testa13 t13 = new Testa13("t13");

		TestHarness.run();
	}
}