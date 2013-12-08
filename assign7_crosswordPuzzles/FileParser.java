////////////////////////////////////////////////////////////////////////////////
//Parses a file into a set of words
//YOU DO NOT NEED TO MODIFY THIS FILE
////////////////////////////////////////////////////////////////////////////////

import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileParser {
    private Pattern word;
    private BufferedReader fileReader;
    private String currentLine;
    private Matcher m;
    private List<String> allWords;

    FileParser(String file) throws IOException{
	allWords = null;
	//Create the regexp we will use.
	word = Pattern.compile("[a-zA-Z']+");

	//open the file
	fileReader = new BufferedReader(new FileReader(file));
	//read in the first line.  Will return null if file is done
	currentLine = fileReader.readLine();
	//Build a matcher for the current line.
	m = word.matcher(currentLine);
    }

    public List<String> getAllWords() throws IOException{
	String nextWord;
	if (allWords == null){
	    //enough for a fair sized novel / dictionary
	    allWords = new ArrayList<String>(200000);
	    while((nextWord = getNextWord()) != null){
		allWords.add(nextWord);
	    }
	}
	return allWords;
    }

    private String getNextWord() throws IOException{
	while(true){
	    if (m.find()){
		//if there is a word ready, return it
		return m.group().toUpperCase();
	    } else {
		//we need to start a new line
		currentLine = fileReader.readLine();
		if(currentLine == null){
		    //Reached the end of the file
		    return null;
		} else {
		    m = word.matcher(currentLine);
		}
	    }
	}
	//should never reach here
    }
}
