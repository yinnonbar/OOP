package oop.ex7.main.parameters.variables;

/**
 * This exception indicates that there is a variable mismatch to the assigned
 * variable.
 * 
 * @author roeia1
 * 
 */
public class VariableTypeMismatchException extends
	VariableCompilationException {

	public VariableTypeMismatchException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
