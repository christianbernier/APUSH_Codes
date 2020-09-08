import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<String> lines;
	static String byLine;
	static Scanner input = new Scanner(System.in);
	static char[] letters = new char[26];

	public static void main(String[] args) {
		for(int i = 0; i < 26; i++) {
			letters[i] = '_';
		}
		System.out.print("What is the last line (include punc.)? ");
		byLine = input.nextLine();
		lines = getLines("code.txt");
		getCodesFromByLine();
		System.out.println("\n");
		ArrayList<String> newLines = translateDocument();
		printList(newLines);
		
		char letter = '.';
		do {
			System.out.print("\nWhat letter would you like to replace? ");
			letter = input.next().charAt(0);
			if(letter != '.') {
				System.out.print("What would you like to replace " + letter + " with? ");
				char repLetter = input.next().charAt(0);
				letters[(int) letter - 65] = repLetter;
				System.out.println("\n");
				
				newLines = translateDocument();
				printList(newLines);
			}
		} while(letter != '.');
	}
	
	public static ArrayList<String> translateDocument() {
		ArrayList<String> translated = new ArrayList<String>();
		for(String s : lines) {
			String newString = "";
			for(int i = 0; i < s.length(); i++){
				char c = s.charAt(i);
				c = translateLetter(c);
				if(c == '_') {
					newString += "[" + s.charAt(i) + "]";
				} else {
					newString += c;
				}
			}
			translated.add(newString);
		}
		
		return translated;
	}
	
	public static char translateLetter(char c) {
		if((int) c - 65 >= 0 && (int) c - 65 < 26) {
			int index = (int) c - 65;
			return letters[index];
		} else {
			return c;
		}
	}
	
	public static void getCodesFromByLine() {
		String lastLine = lines.get(lines.size() -1);
		if(lastLine.length() == byLine.length()) {
			int byLineLength = byLine.length();
			for(int i = 0; i < byLineLength; i++) {
				char letter = byLine.charAt(i);
				if(letter != '_') {
					int charInt = (int) (lastLine.charAt(i)) - 65;
					if(charInt >= 0 && charInt <= 25) {
						letters[charInt] = letter;
					}
				}
			}
		}
	}
	
	public static void printEncodedLetterList() {
		for(int i = 0; i < 26; i++) {
			System.out.println(((char) (i + 65)) + " - " + letters[i]);
		}
	}
	
	@SuppressWarnings("resource")
	public static ArrayList<String> getLines(String filepath){
		ArrayList<String> fileLines = new ArrayList<String>();
		File f = new File(filepath);
		Scanner c = null;
		try {
			c = new Scanner(f);
		} catch(FileNotFoundException e) {
			throw new Error(e);
		}
		
		while(c.hasNextLine()) {
			fileLines.add(c.nextLine());
		}
		
		return fileLines;
	}
	
	public static void printList(ArrayList<String> list) {
		for(String s : list) {
			System.out.println(s);
		}
	}

}
