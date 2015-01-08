import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PigLatin extends PApplet {



String lowellHym = "";

public void setup() {
	String lines[] = loadStrings("words.txt");
	String hymLines[] = loadStrings("LowellHymn.txt");
	System.out.println("there are " + lines.length + " lines");
	for (int i = 0 ; i < lines.length; i++) {
		System.out.println(pigLatin(lines[i]));
	}
	for (int i = 0 ; i < hymLines.length; i++) {
		lowellHym+=hymLines[i];
	}
	System.out.println(lowellHymDecoder(lowellHym));
	System.out.println("DONE");
}

public int findFirstVowel(String sWord){
	for(int i=0; i<sWord.length(); i++){
		if(sWord.substring(i, i+1).toLowerCase().equals("a") || sWord.substring(i, i+1).toLowerCase().equals("e") || sWord.substring(i, i+1).toLowerCase().equals("i") || sWord.substring(i, i+1).toLowerCase().equals("o") || sWord.substring(i, i+1).toLowerCase().equals("u"))
			return i;
	}
	return -1;
}

public String lowellHymDecoder(String hym){
	String output = "";
	int start = 0;
	int finish = 0;
	for(int i=0; i<hym.length(); i++){
		if(!hym.substring(i, i+1).equals(",") && !hym.substring(i, i+1).matches("^\\s*$") && !hym.substring(i, i+1).equals(".")){
			finish++;
			continue;
		} else if(hym.substring(i, i+1).equals(",")){
			//System.out.println(hym.substring(i, i+1));
			output = output.substring(0, output.length()-1);
			output+=", ";
			start = finish+1;
			finish++;
		}else if(hym.substring(i, i+1).equals(".")){
			//System.out.println(hym.substring(i, i+1));
			output = output.substring(0, output.length()-1);
			output+=". ";
			start = finish+1;
			finish++;
		}else{
			//System.out.println(hym.substring(start, finish));
			output+=pigLatin(hym.substring(start, finish));
			if(hym.substring(i, i+1).equals(" ")){
				output+=" ";
			}
			start = finish+1;
			finish++;
		}		
	}
	return output;
}

public String pigLatin(String sWord){
	if(findFirstVowel(sWord) == -1){
		return sWord + "ay";
	} else if(findFirstVowel(sWord) == 0){
		return sWord + "way";
	} else if(findFirstVowel(sWord)>0){
		return sWord.substring(findFirstVowel(sWord)) + sWord.substring(0,findFirstVowel(sWord)) + "ay";
	} else if(findFirstVowel(sWord) > 0){
		return sWord.substring(1) + sWord.substring(0,1) + "ay";
	} else {
		return "ERROR";
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "PigLatin" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
