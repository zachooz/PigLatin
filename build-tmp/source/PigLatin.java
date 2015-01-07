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



public void setup() {
	String lines[] = loadStrings("words.txt");
	System.out.println("there are " + lines.length + " lines");
	for (int i = 0 ; i < lines.length; i++) {
	  System.out.println(pigLatin(lines[i]));
	}
}

public int findFirstVowel(String sWord){
	for(int i=0; i<sWord.length(); i++){
		if(sWord.substring(i, i+1).equals("a") || sWord.substring(i, i+1).equals("e") || sWord.substring(i, i+1).equals("i") || sWord.substring(i, i+1).equals("o") || sWord.substring(i, i+1).equals("u"))
			return i;
	}
	return -1;
}

public String pigLatin(String sWord){
	if(findFirstVowel(sWord) == -1){
		return sWord + "ay";
	} else if(findFirstVowel(sWord) == 0){
		return sWord + "way";
	} else if(sWord.substring(0,2).equals("qu")){
		return sWord.substring(2) + "quay";
	} else if(sWord.substring(0,2).equals("sh")){
		return sWord.substring(2) + "shay";
	} else if(sWord.substring(0,2).equals("ch")){
		return sWord.substring(2) + "chay";
	} else if(sWord.substring(0,2).equals("th")){
		return sWord.substring(2) + "thay";
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
