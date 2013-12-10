//YOU SHOULD MODIFY THIS FILE.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.*;

public class CWSolution {
	
	//maps word length integers to arraylists
	private HashMap<Integer,ArrayList<String>> mapWordLengths;
	
    public CWSolution(List<String> allWords)
    {	
    	mapWordLengths = new HashMap<Integer,ArrayList<String>>();
    	
    	//check for null parameter
    	if(allWords == null) return;
    	
    	//go through all words
    	//place them in arraylist depending on their length
    	for(String currWord : allWords){
    		int length = currWord.length();
    		ArrayList<String> currArr = mapWordLengths.get(length);
    		
    		if(currArr == null){
        		//if currArray is null then there is not a mapped array
    			//create a new arraylist and have that length map to it
    			ArrayList<String> newArr = new ArrayList<String>();
    			newArr.add(currWord);
    			mapWordLengths.put(length, newArr);
    		}else{
    			//add this word to the current array
    			currArr.add(currWord);
    		}
    		
    	}
    }

    //All words will be the actual words, not funny characters
    public List<String> solutions(String pattern, int maxRequired)
    { 	//for example: ***A*
    	
    	//solutions' list that will get built up
    	List<String> solution = new ArrayList<String>();
    	
    	//if no words have been provided through the constructor, return an empty list
    	if(mapWordLengths.size() == 0) return solution;
    	//check for null parameters and maxRequired = 0
    	if(pattern == null || maxRequired == 0) return solution; 
    	
    	//only uppercase characters are valid
//    	pattern = pattern.toUpperCase();
    	
    	//figure out the length of the pattern and retrieve the appropriate array
    	//only iterate through that (relatively) small set of words
    	int len = pattern.length();
    	ArrayList<String> currArray = mapWordLengths.get(len);
    	    	
    	//only need to supply a number that's equal to maxRequired
    	int counter = 0;
		
    	//iterate through the retrieved array and add matching words to the solution
		int size = currArray.size();
		for(int i=0; i<size; i++){
			//use regex to increase performance and speed when matching
			//'*' symbols throw regex exceptions so change them to '.'
			//add .CASE_INSENSITIVE parameter to match lower and upper case words
			Pattern regex = Pattern.compile(pattern.replace('*', '.'), Pattern.CASE_INSENSITIVE);
			Matcher matcher = regex.matcher(currArray.get(i));
			
			if(matcher.matches()){
				//the word from the array matches!!!
				solution.add(currArray.get(i));
				
				//make sure we aren't adding more words than we need
				counter++;
				if(counter >= maxRequired) break;
			}
		}
		
		return solution;
    }
    
    
}

