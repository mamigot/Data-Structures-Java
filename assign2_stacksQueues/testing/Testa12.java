
// ***********************************************************************
//
// Testa12 : BalanceCheck (null characters)
// Check how the BalanceCheck methods work for nulls
//
// ***********************************************************************

import java.util.*;

public class Testa12 extends TestHarness {

    public Testa12(String s) { super(s); }

    public boolean test() {

	if( !BalanceCheck.checkString(null) )
	    return false;

	return true;
    }
}