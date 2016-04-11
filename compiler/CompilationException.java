package oop.ex7.main;

/**
 * An exception for compilation errors.
 * 
 * @author roeia1
 * 
 */
public class CompilationException extends Exception {

	/**
	 * super the constructor with error message.
	 */
	public CompilationException(String errorMessage) {
		super(errorMessage);
	}

	private static final long serialVersionUID = 1L;

}
