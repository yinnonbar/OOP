package oop.ex7.main.parameters.variables;

import oop.ex7.main.parameters.ParameterNotExistException;

/**
 * This exception indicates that a variable isn't exist, compilation error.
 * 
 * @author roeia1
 * 
 */
public class VariableNotExistException extends ParameterNotExistException {

	public VariableNotExistException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
