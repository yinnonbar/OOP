package oop.ex7.main.parameters.variables;

/**
 * This exception extends the CompilationException and being thrown when a
 * variable is already exist.
 * 
 * @author roeia1
 * 
 */
public class VariableAlreadyExistException extends
	VariableCompilationException {

	/**
	 * super the constructor with error message.
	 * 
	 * @param errorMessage
	 */
	public VariableAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}

	private static final long serialVersionUID = 1L;

}
