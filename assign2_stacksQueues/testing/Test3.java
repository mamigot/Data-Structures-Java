// ***********************************************************************
//
// Test3 : Tests the BalanceCheck class
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

import java.util.*;

public class Test3 extends TestHarness {

    public Test3(String s) { super(s); }

    public boolean test() {

	if(! BalanceCheck.checkString("We have one (1) oven."))
	    return false;
	if(BalanceCheck.checkString("We have 1 (one oven."))
	    return false;
	if(BalanceCheck.checkString("this { is a () much [harder] }} example"))
	    return false;
	if(! BalanceCheck.checkString("this { is a () much [harder] } example"))
	    return false;

	return true;
    }
}