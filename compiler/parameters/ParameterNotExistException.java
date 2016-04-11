package oop.ex7.main.parameters;

/**
 * This exception indicates that a parameter isn't exist, a compilation error.
 * 
 * @author roeia1
 * 
 */
public class ParameterNotExistException extends ParameterCompilationException {

	public ParameterNotExistException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
