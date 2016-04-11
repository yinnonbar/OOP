package oop.ex7.main.parameters.variables;

/**
 * This exception indicates that a variable isn't operandable while using it
 * with operand.
 * 
 * @author roeia1
 * 
 */
public class NotOperandableVariableException extends
	VariableCompilationException {

	public NotOperandableVariableException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
