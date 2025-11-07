package Long1;

import java.util.Scanner;

public class Cryptograms {
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		// Controller object to perform the game's actions:
		CryptogramController controller = new CryptogramController(new CryptogramModel());
		
		// Play the game until a correct guess is given:
		while (!controller.isGameOver()) {
			// Print encryption:
			System.out.println(controller.getEncryptedQuote());
			
			// Get the user's guess:
			char toReplace = getToReplace(keyboard);
			char replacement = getReplacement(keyboard, controller);
			
			// Make the guess with the controller:
			controller.makeReplacement(toReplace, replacement);
			
			// Print the guess thus far:
			System.out.println(controller.getUsersProgress());
		}
		
		// Success! Print as such:
		System.out.println(controller.getEncryptedQuote());
		System.out.println("You got it!");
		
		keyboard.close();
	}
	
	/**
	 * The getToReplace() method prompts the user for a single
	 * toReplace letter, validates (repeating prompt if needed), and returns.
	 * @param keyboard The Scanner object to gather input with.
	 * @return The input toReplace char (location in encryption).
	 */
	private static char getToReplace(Scanner keyboard) {
		String toReplace;
		
		// Get the toReplace as input, validating:
		do {
			System.out.print("Enter the letter to replace: ");
			toReplace = keyboard.next().toUpperCase();	// upper for consistency
			
			// If letter is wrong length or non-alphabetical, re-prompt:
			if (toReplace.length() != 1 ||
					(toReplace.compareTo("A")<0 || toReplace.compareTo("Z")>0)) {
				System.out.println("Please enter a single letter.");
			} else {	// letter valid
				break;
			}
		} while (true);
		
		return toReplace.charAt(0);	// convert to char for return
	}
	
	/**
	 * The getReplacement() method prompts the user for a single
	 * replacement letter, validates (repeating prompt if needed), and returns.
	 * @param keyboard The Scanner object to gather input with.
	 * @param controller The controller, to determine if char already guessed.
	 * @return The input replacement char (letter to insert into guess).
	 */
	private static char getReplacement(Scanner keyboard, CryptogramController controller) {
		String replacement;
		
		// Get the replacement as input, validating:
		do {
			System.out.print("Enter its replacement: ");
			replacement = keyboard.next().toUpperCase();	// upper for consistency
			
			// If letter is wrong length or non-alphabetical,
			// or guess is already in string, re-prompt:
			if (replacement.length() != 1 ||
					(replacement.compareTo("A")<0 || replacement.compareTo("Z")>0)) {
				System.out.println("Please enter a single letter.");
			} else if (controller.getUsersProgress().indexOf(replacement) != -1) {
				System.out.println("Guessed letter is already in the guess.");
			} else {
				break;
			}
		} while (true);
		
		return replacement.charAt(0);	// convert to char for return
	}
}
