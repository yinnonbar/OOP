package oop.ex7.main.parameters.variables;

/**
 * This exception indicates that an uninitialized variable used for assignment,
 * compilation error.
 * 
 * @author Roei
 * 
 */
public class UninitializedVariableException extends
	VariableCompilationException {

	public UninitializedVariableException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
