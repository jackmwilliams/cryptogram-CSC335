package Long1;

public class CryptogramController {
	
	private CryptogramModel model;	// the model to interact with

	/**
	 * Constructor for the CryptogramController class.
	 * @param model The CryptogramModel object to interact with.
	 */
	public CryptogramController(CryptogramModel model) {
		this.model = model;
	}
	
	/**
	 * The isGameOver() method return a bool, determining if the answer
	 * has been correctly guessed yet.
	 * @return A boolean, true if the answer has been correctly guessed.
	 */
	public boolean isGameOver() {
		return model.getCurrentGuess().equals(model.getAnswer());
	}
	
	/**
	 * The makeReplacement() method inserts the guess char at all locations
	 * of the letterToReplace char in the model.
	 * @param letterToReplace The letter in the encryption to replace at.
	 * @param guess The letter to replace with.
	 */
	public void makeReplacement(char letterToReplace, char guess) {
		model.setReplacement(letterToReplace, guess);
	}
	
	/**
	 * The getEncryptedQuote() method is the getter for the fully encrypted
	 * String input to guess from.
	 * @return The encrypted guess String.
	 */
	public String getEncryptedQuote() {
		return model.getEncryptedString();
	}
	
	/**
	 * The getUsersProgress() method returns the user's guess thus far.
	 * @return The user's guess thus far, showing all guessed letters.
	 */
	public String getUsersProgress() {
		return model.getCurrentGuess();
	}
}
