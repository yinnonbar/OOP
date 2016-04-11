package oop.ex7.main.scopes;

import oop.ex7.main.CompilationException;


/**
 * This exception indicates that the condition is not a boolean type.
 * 
 * @author roeia1
 * 
 */
public class MismatchConditionTypeException extends CompilationException {

	public MismatchConditionTypeException(String errorMessage) {
		super(errorMessage);
	}

	private static final long serialVersionUID = 1L;

}
