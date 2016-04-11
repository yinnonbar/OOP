package oop.ex7.main.parameters.variables;

import oop.ex7.main.parameters.ParameterCompilationException;

/**
 * This exception indicates that there is a compilation error with the variable.
 * 
 * @author roeia1
 * 
 */
public class VariableCompilationException extends
	ParameterCompilationException {

	public VariableCompilationException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
