package oop.ex7.main.parameters;

import oop.ex7.main.IllegalSyntaxException;


/**
 * This exception indicates that there is a parameter syntax exception.
 * 
 * @author roeia1
 * 
 */
public class IllegalParameterException extends IllegalSyntaxException {

	public IllegalParameterException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
