package oop.ex7.main.parameters.variables;

/**
 * This exception indicates that a method calling return variable isn't
 * operandable while using it with operand.
 * 
 * @author roeia1
 * 
 */
public class NotOperandableMethodCallingException extends
	VariableCompilationException {

	public NotOperandableMethodCallingException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
