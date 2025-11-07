package Long1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class CryptogramModel {

	private HashMap<Character, Character> encryption;	// encryption key
	private String lineToGuess;	// unedited input line
	private String encryptedLineToGuess;	// encrypted input line
	private String curGuess;	// user guess thus far

	/**
	 * Constructor for the CryptogramModel class.
	 * Reads a random line from input.txt to generate encryption from.
	 * Sets starting fields based on this input line.
	 */
	public CryptogramModel() {
		Random randLineGen = new Random();	// to get a random input line
		
		// Get Scanner for input.txt:
		Scanner fScanner = null;
		try {
			fScanner = new Scanner(new File("src/Long1/input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error: input.txt not found.");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Read all input from file, then select random line to use:
		ArrayList<String> lines = new ArrayList<>();
		while (fScanner.hasNextLine()) {
			lines.add(fScanner.nextLine());
		}
		lineToGuess = lines.get(randLineGen.nextInt(lines.size())).toUpperCase();
		// ^ Convert every input to uppercase for consistency
		
		// Create the encryption key and result for the user to guess:
		encryption = genEncryption();
		encryptedLineToGuess = encrypt(lineToGuess, encryption);
		
		// Create the current (empty) guess from the input:
		curGuess = makeEmptyGuess(encryptedLineToGuess);
		
		fScanner.close();
	}
	
	/**
	 * The genEncryption() method creates a HashMap<Character,Character> that
	 * maps each letter of the alphabet to a random, unique letter.
	 * e.g., 'A' is mapped to 'G', 'B' is mapped to 'A', etc.
	 * There are 26! possible permutations to map to.
	 * @return The HashMap<Character, Character> with the inorder alphabet as
	 * 	the keys, and the shuffled alphabet as the values.
	 */
	private HashMap<Character, Character> genEncryption() {
		ArrayList<Character> alpha = new ArrayList<>();	// inorder alphabet
		for (char c = 'A'; c <= 'Z'; c++)
			alpha.add(c);
		
		// Generate a shuffled alphabet:
		@SuppressWarnings("unchecked")
		ArrayList<Character> shuffledAlpha = (ArrayList<Character>)alpha.clone();
		Collections.shuffle(shuffledAlpha);
		
		// Make a HashMap between the alphabet and its shuffled version:
		HashMap<Character, Character> mapAlphaToEncrypted = new HashMap<>();
		for (int i = 0; i < alpha.size(); i++) {
			mapAlphaToEncrypted.put(alpha.get(i), shuffledAlpha.get(i));
		}
		
		return mapAlphaToEncrypted;
	}
	
	/**
	 * The encrypt() method accepts a String to encrypt, and a HashMap to
	 * encrypt with. It changes the letters in the String (according to
	 * the encryption) and returns the encrypted String.
	 * @param toEncrypt The String to encrypt.
	 * @param encryption The HashMap<Character,Character> with the conversion.
	 * @return The encrypted String, with all alphabetical characters changed.
	 */
	private String encrypt(String toEncrypt, HashMap<Character, Character> encryption) {
		String encrypted = "";	// encrypted str destination
		
		// Loop through toEncrypt, adding non-alphabetical chars to encrypted
		// directly, and putting letters through the encryption:
		for (int i = 0; i < toEncrypt.length(); i++) {
			char charToEncrypt = toEncrypt.charAt(i);
			
			if (charToEncrypt >= 'A' && charToEncrypt <= 'Z') {	// if letter
				encrypted += encryption.get(charToEncrypt);
			} else {	// if not letter
				encrypted += charToEncrypt;
			}
		}
		
		return encrypted;
	}
	
	/**
	 * The makeEmptyGuess() method takes a line as input and replaces all
	 * alphabetical characters with spaces. This String will then be ready
	 * to be filled with user guesses.
	 * @param line The input to remove letters from, making a guess String.
	 * @return The mostly empty guess String with letters removed.
	 */
	private String makeEmptyGuess(String line) {
		String emptyGuess = "";
		
		// Iterate on lineToGuess, adding spaces for letters, keeping else intact:
		for (int i = 0; i < line.length(); i++) {
			char insertChar = line.charAt(i);
			
			if (insertChar >= 'A' && insertChar <= 'Z') {	// if letter
				emptyGuess += " ";
			} else {	// if not letter
				emptyGuess += insertChar;
			}
		}
		
		return emptyGuess;
	}
	
	/**
	 * The setReplacement() method accepts an encrypted char and a guess char,
	 * and inserts the guess to where the encrypted char is in the guess.
	 * @param encryptedChar The char in the encryption to insert at.
	 * @param guess The guessed char to insert.
	 */
	public void setReplacement(char encryptedChar, char guess) {
		// Iterate through the guess thus far, inserting with each match:
		for (int i = 0; i < encryptedLineToGuess.length(); i++) {
			char thisToGuessChar = encryptedLineToGuess.charAt(i);
			
			if (thisToGuessChar == encryptedChar) {	// if letter to insert at
				// Insert the char guess, overwriting previous values:
				curGuess = curGuess.substring(0,i) +
						guess +
						curGuess.substring(i+1);
			}
		}
	}
	
	/**
	 * The getEncryptedString() method is the getter for the encrypted input.
	 * @return The encrypted line to guess.
	 */
	public String getEncryptedString() {
		return encryptedLineToGuess;
	}
	
	/**
	 * The getCurrentGuess() method is the getter for the current user guess.
	 * @return The current user guess, in progress.
	 */
	public String getCurrentGuess() {
		return curGuess;
	}
	
	/**
	 * The getAnswer() method is the getter for the correct, unencrypted answer.
	 * @return The unencrypted answer to the cryptogram.
	 */
	public String getAnswer() {
		return lineToGuess;
	}
}
