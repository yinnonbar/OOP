package oop.ex7.main.parameters;

/**
 * This exception indicates that the method calling isn't exist, a compilation
 * error.
 * 
 * @author roeia1
 * 
 */
public class MethodCallingNotExistException extends ParameterNotExistException {

	public MethodCallingNotExistException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
