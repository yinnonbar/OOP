package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;


/**
 * This exception indicates that there is a syntax error in the variable type.
 * 
 * @author roeia1
 * 
 */
public class IllegalVariableTypeException extends IllegalSyntaxException {

	/**
	 * super the constructor with error message.
	 */
	public IllegalVariableTypeException(String errorMessage) {
		super(errorMessage);
	}

	private static final long serialVersionUID = 1L;

}
