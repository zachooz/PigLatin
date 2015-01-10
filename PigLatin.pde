import java.util.*;

String lowellHym = "";

public void setup() {
	String lines[] = loadStrings("words.txt");
	String hymLines[] = loadStrings("LowellHymn.txt");
	System.out.println("there are " + lines.length + " lines");
	for (int i = 0 ; i < lines.length; i++) {
		System.out.println(pigLatin(lines[i]));
	}
	System.out.println("Lines Decoded");
	System.out.println();
	for (int i = 0 ; i < hymLines.length; i++) {
		lowellHym+=hymLines[i];
	}
	System.out.println(lowellHymDecoder(lowellHym));
	System.out.println();
	System.out.println("Hymn Decoded");
	System.out.println();
}

public int findFirstVowel(String sWord){
	for(int i=0; i<sWord.length(); i++){
		if(sWord.substring(i, i+1).toLowerCase().equals("a") || sWord.substring(i, i+1).toLowerCase().equals("e") || sWord.substring(i, i+1).toLowerCase().equals("i") || sWord.substring(i, i+1).toLowerCase().equals("o") || sWord.substring(i, i+1).toLowerCase().equals("u"))
			return i;
	}
	return -1;
}

public String pigLatin(String sWord){
	Boolean isUpperCase = !sWord.equals(sWord.toLowerCase());
	if(isUpperCase){
		sWord = sWord.toLowerCase();
	}
	if(sWord.equals("") || sWord.equals(" ")){
		return "";
	}
	if(findFirstVowel(sWord) == -1){
		if(isUpperCase)
			return sWord.substring(0, 1).toUpperCase() + sWord.substring(1) + "ay";
		return sWord + "ay";
	} else if(findFirstVowel(sWord) == 0){
		if(isUpperCase)
			return sWord.substring(0, 1).toUpperCase() + sWord.substring(1) + "way";
		return sWord + "way";
	} else if(sWord.length() > 3 && sWord.substring(0,2).equals("qu")) {
		return sWord.substring(2) + "quay";
	}else if(findFirstVowel(sWord)>0){
		String output = sWord.substring(findFirstVowel(sWord)) + sWord.substring(0,findFirstVowel(sWord)) + "ay";
		if(isUpperCase){
			return output.substring(0, 1).toUpperCase() + output.substring(1);
		} else {
			return output;
		}
	} else {
		return "ERROR";
	}
}


public String lowellHymDecoder(String hym){
	String output = " ";
	int start = 0;
	int finish = 0;
	for(int i=0; i<hym.length(); i++){
		if(!hym.substring(i, i+1).equals(",") && !hym.substring(i, i+1).matches("^\\s*$") && !hym.substring(i, i+1).equals(".")){
			finish++;
			continue;
		} else if(hym.substring(i, i+1).equals(",")){
			//System.out.println(hym.substring(i, i+1));
			output+=pigLatin(hym.substring(start, finish));
			//output = output.substring(0, output.length()-1);
			output+=",\n";
			start = finish+1;
			finish++;
		}else if(hym.substring(i, i+1).equals(".")){
			//System.out.println(hym.substring(i, i+1));
			output+=pigLatin(hym.substring(start, finish));
			output+=".\n\n";
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