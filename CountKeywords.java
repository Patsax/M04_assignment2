package javaDevelop.M04.M04_assignment2;

import java.util.*;
import java.io.*;

public class CountKeywords {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Java source file: ");
		String filename = input.nextLine();
		File file = new File(filename);
		if (file.exists()) {
			System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
		} else {
			System.out.println("File " + filename + " does not exist");
		}
	}

	public static int countKeywords(File file) throws Exception {
		// Array of all Java keywords + true, false and null
		String[] keywordString = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
				"const", "continue", "default", "do", "double", "else", "enum", "extends", "for", "final", "finally",
				"float", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
				"new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super",
				"switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while",
				"true", "false", "null" };
		Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
		int count = 0;
		Scanner input = new Scanner(file);
		// using a custom delimiter for the input, so that the words from the file will
		// be tokenized based on non alphabetic characters that are not '/' or '*'
		// characters either.
		input.useDelimiter("[^\\w/*]");
		while (input.hasNext()) {
			String word = input.next();
			// checking if word starts with '/*'
			if (word.startsWith("/*")) {
				// looping as long as word does not end with */ (multiline comment closing) and
				// there's content from the file available.
				while (!word.endsWith("*/") && input.hasNext()) {
					// reading off next word so that it is not processed
					word = input.next();
				}
			}
			// otherwise if current word starts with a '//' character (single line comment),
			// reading off the rest of the line, so that the content inside that comment is
			// not processed
			else if (word.startsWith("//")) {
				input.nextLine();
			}
			// else checking if keywordset contains word, and if yes, incrementing count
			else if (keywordSet.contains(word)) {
				count++;
			}

		}
		return count;
	}
}
