package oop.ex7.main.parameters.variables;

/**
 * This exception indicates there is an error in the array location (abc[-1]).
 * 
 * @author roeia1
 * 
 */
public class IllegalArrayLocationException extends
	IllegalVariableSyntaxException {

	public IllegalArrayLocationException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
