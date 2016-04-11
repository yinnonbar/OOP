package oop.ex7.main.parameters.variables;

import oop.ex7.main.parameters.IllegalParameterException;

/**
 * This exception indicates that there is a syntax error in a variable.
 * 
 * @author roeia1
 * 
 */
public class IllegalVariableSyntaxException extends IllegalParameterException {

	public IllegalVariableSyntaxException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
