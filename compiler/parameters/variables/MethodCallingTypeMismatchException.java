package oop.ex7.main.parameters.variables;

/**
 * This exception indicates that there is a method calling return variable
 * mismatch to the assigned variable.
 * 
 * @author roeia1
 * 
 */
public class MethodCallingTypeMismatchException extends
	VariableCompilationException {

	public MethodCallingTypeMismatchException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
