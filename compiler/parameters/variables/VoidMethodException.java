package oop.ex7.main.parameters.variables;

/**
 * This exception indicates that there is a method calling returning void used
 * in assignment.
 * 
 * @author roeia1
 * 
 */
public class VoidMethodException extends VariableCompilationException {

	public VoidMethodException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
