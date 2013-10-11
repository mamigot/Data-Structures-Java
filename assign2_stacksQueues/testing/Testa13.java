
// ***********************************************************************
//
// Testa13 : BalanceCheck (complex strings)
// Check how the BalanceCheck methods work for more complex strings
//
// ***********************************************************************

import java.util.*;

public class Testa13 extends TestHarness {

    public Testa13(String s) { super(s); }

    public boolean test() {

	if(BalanceCheck.checkString("))##@ [ #@ [] 22) {} ]")) //not balanced
	    return false;
	if(!BalanceCheck.checkString("(())##@ [ #@ [] (`'<>22) {} ]")) //not balanced
	    return false;

	    
	return true;
	}
}