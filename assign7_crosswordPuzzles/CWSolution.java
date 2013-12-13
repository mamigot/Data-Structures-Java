/**
 * A crossword-puzzle solution finder.
 * <p>
 * A list of valid words is provided via the constructor and {@link #solutions(String, int)}
 * generates a list of words that match the given criteria. The valid words are stored
 * in ArrayLists along with words of equal length; and these ArrayLists are stored in
 * a HashMap which maps words' lengths to respective ArrayLists.
 * 
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Miguel Amigot <ma2786@nyu.edu>
 * @version     Assignment 7
 * @since       2013-12-13
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.*;

public class CWSolution {

	/**
	 * HashMap which maps words' lengths to respective ArrayLists.
	 */
	private HashMap<Integer,ArrayList<String>> mapWordLengths;

	/**
	 * Class constructor.
	 * <p>
	 * Iterates through the given list of valid words ({@link allWords}), evaluates
	 * their lengths and stores them in ArrayLists along with words of similar lengths.
	 * These ArrayLists are stored in {@link #mapWordLengths}.
	 * 
	 * @param allWords Given list of valid words.
	 */
	public CWSolution(List<String> allWords)
	{	
		mapWordLengths = new HashMap<Integer,ArrayList<String>>();

		//check for null parameter and cease the implementation of the constructor if it's null
		if(allWords == null) return;

		//go through all words
		//place them in arraylist depending on their length
		for(String currWord : allWords){
			//upper-case will facilitate comparisons later on
			currWord = currWord.toUpperCase();

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

	/**
	 * Parses through the ArrayLists in {@link #mapWordLengths} and returns a list
	 * of the words that match the given pattern ({@link pattern}).
	 * <p>
	 * The maximum number of words that must be returned is specified by {@link maxRequired},
	 * and it is established that {@link pattern} may only be comprised of '*' characters or letters.
	 * 
	 * @param pattern The pattern specified by the user, comprised only of '*' characters and letters
	 * e.g. **S*, *AM**** and *****T.
	 * @param maxRequired The maximum number of words that must be returned.
	 * @return List<String> that contains the matching words.
	 */
	public List<String> solutions(String pattern, int maxRequired)
	{ 	//for example: ***A*

		//solutions' list that will get built up
		List<String> solution = new ArrayList<String>();

		//if no words have been provided through the constructor, return an empty list
		if(mapWordLengths.size() == 0) return solution;
		//check for null parameters and maxRequired = 0
		if(pattern == null || maxRequired == 0) return solution; 

		//case-insensitivity
		pattern = pattern.toUpperCase();

		//figure out the length of the pattern and retrieve the appropriate array
		//only iterate through that (relatively) small set of words
		int len = pattern.length();
		ArrayList<String> currArray = mapWordLengths.get(len);

		//only need to supply a number that's equal to maxRequired
		int counter = 0;

		//iterate through the retrieved array and add matching words to the solution
		int size = currArray.size();
		for(int i=0; i<size; i++){

			//check if the word on the current position of the array matches pattern
			if(checkMatch(pattern, currArray.get(i), len)){
				//the word from the array matches!!!
				solution.add(currArray.get(i));

				//make sure we aren't adding more words than we need
				counter++;
				if(counter >= maxRequired) break;
			}

		}
		//return the list of matching words
		return solution;
	}

	/**
	 * Helper method for {@link #solutions(String, int)}.
	 * Determines whether {@link pattern} and {@link arrWord} match each other, which is only
	 * if {@link pattern}'s letters are present on {@link arrWord} at the correct positions.
	 * 
	 * 
	 * @param pattern The pattern specified by the user, comprised only of '*' characters and letters.
	 * @param arrWord The word provided by the relevant ArrayList, comprised only of letters.
	 * @param lengthPattern The length of {@link pattern}, provided as a parameter
	 * to increase the speed of the method.
	 * @return True if {@link pattern} and {@link arrWord} match, false if not;
	 * e.g. pattern="VEHICLE" and arrWord="**H****" will return true.
	 */
	private boolean checkMatch(String pattern, String arrWord, int lengthPattern){

		//iterate through every character in the string (both are the same length)
		for(int p=0; p<lengthPattern; p++){
			//evaluate the current character
			//if it's not a '*', it must match arrWord
			char curr = pattern.charAt(p);
			if(curr != '*'){
				//compare it to the array's word
				if(arrWord.charAt(p) != curr) return false;
			}
		}

		return true;
	}




}

