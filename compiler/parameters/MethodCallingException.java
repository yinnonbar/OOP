package oop.ex7.main.parameters;

/**
 * This exception indicates that there is a compilation error in a method
 * calling.
 * 
 * @author roeia1
 * 
 */
public class MethodCallingException extends ParameterCompilationException {

	public MethodCallingException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
