package oop.ex7.main.parameters;


/**
 * This exception indicates that a name is a keyword.
 * 
 * @author roeia1
 * 
 */
public class IllegalNameException extends IllegalParameterException {

	public IllegalNameException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
