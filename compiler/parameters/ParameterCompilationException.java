package oop.ex7.main.parameters;

import oop.ex7.main.CompilationException;


/**
 * This exception indicates that there is a compilation error in a parameter.
 * 
 * @author roeia1
 * 
 */
public class ParameterCompilationException extends CompilationException {

	public ParameterCompilationException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
