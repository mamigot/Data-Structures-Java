// ***********************************************************************
// MIGUEL AMIGOT GONZALEZ - 10.5.2013
// BalanceCheck Class 
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

import java.util.*;

public class BalanceCheck {

	//Scan through the string, character by character
	//When an open character is observed, push it onto the stack
	//When a close character is observed, pop a character from the stack
	//and ensure that it is the open-counterpart to the close character!
    public static boolean checkString(String s) {
    	
    	//Don't do anything if the supplied string is null
    	if(s == null) return true;
    	
    	//Create a stack of characters
    	StackList<Character> stackChar = new StackList<Character>();
    	
    	//Store the open and close characters in hashmaps to access quickly
    	//We want (, {, [, <, or ` and ), }, ], >, or '
    	Map<Character, Character> openChars = new HashMap<Character, Character>();
    	//openChars.put( **KEY** , **VALUE** )
    	openChars.put( '(' , ')' );
    	openChars.put( '{' , '}' );
    	openChars.put( '[' , ']' );
    	openChars.put( '<' , '>' );
    	openChars.put( '`' , '\'' );
    	
    	Map<Character, Character> closeChars = new HashMap<Character, Character>();
    	closeChars.put( ')' , '(' );
    	closeChars.put( '}' , '{' );
    	closeChars.put( ']' , '[' );
    	closeChars.put( '>' , '<' );
    	closeChars.put( '\'' , '`' );
   
    	//Scan through the string
    	//Determine the length of the string
    	int strLen = s.length();
    	for(int i=0; i<strLen; i++){
    		//Get the character at the position
    		char now = s.charAt(i);
    		//Check if it's a key of the openChars HashMap
    		//true if this map contains a mapping for the specified key
    		if(openChars.containsKey(now)){
    			//If it is, push it to the stack
    			stackChar.push(now);
    		}
    		if(closeChars.containsKey(now)){
    			try {
					char lastElement = stackChar.pop();
	    			if(openChars.get(lastElement) != now){
	    				return false;
	    			}
				} catch (InvalidOperationException e) {
					// Means we're trying to remove an element from an empty stack
					// So there are more closing characters than opening characters
					e.printStackTrace();
					return false;
				}
    		}
    	}
    	//If the stack is not empty, return false
    	if(stackChar.size()>0){
    		return false;
    	}else{
    		return true;
    	}
    
    }



}