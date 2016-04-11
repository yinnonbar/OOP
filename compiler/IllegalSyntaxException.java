package oop.ex7.main;

/**
 * This exception extends the Exception class and being thrown when an illegal
 * syntax line is given.
 * 
 * @author roeia1
 * 
 */
public class IllegalSyntaxException extends Exception {

	/**
	 * super the constructor with error message.
	 */
	public IllegalSyntaxException(String errorMessage) {
		super(errorMessage);
	}

	private static final long serialVersionUID = 1L;

}
