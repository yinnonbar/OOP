package oop.ex7.main;

/**
 * This exception extends the CompilationException and being thrown when an
 * illegal value is given as the return location.
 * 
 * @author roeia1
 * 
 */
public class IllegalReturnLocationUsageException extends CompilationException {

	/**
	 * super the constructor with error message.
	 */
	public IllegalReturnLocationUsageException(String errorMessage) {
		super(errorMessage);
	}

	private static final long serialVersionUID = 1L;

}
