package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.parameters.IllegalNameException;

/**
 * This class is the CharType extends the variable class.
 * 
 * @author roeia1
 * 
 */
public class CharType extends Variable {

	/** The regular expression of a raw char */
	public final static String CHAR_REGEX = "^\\s*\\'.?\\'\\s*$";

	/**
	 * Constructing a raw char.
	 */
	public CharType() {
		super();
	}

	/**
	 * Constructing a return value char for a method.
	 * 
	 * @param isArray
	 *            - represents if the return value char is an array.
	 */
	public CharType(boolean isArray) {
		super(isArray);
	}

	/**
	 * Constructing a new char variable.
	 * 
	 * @param name
	 *            - the name of the char variable.
	 * @param isArray
	 *            - represents if the new char variable is an array.
	 * @throws IllegalNameException
	 *             if the char variable name isn't valid (a keyword).
	 */
	public CharType(String name, boolean isArray) throws IllegalNameException {
		super(name, isArray);
	}

	/**
	 * Constructing a new char variable with an assignment value.
	 * 
	 * @param name
	 *            - the name of the char variable.
	 * @param isArray
	 *            - represents if the new char variable is an array.
	 * @param assignmentValue
	 *            - the assignment value string.
	 * @throws IllegalNameException
	 *             if the char variable name isn't valid (a keyword).
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error with the assignment string.
	 */
	public CharType(String name, boolean isArray, String assignmentValue)
		throws IllegalNameException, IllegalSyntaxException {
		super(name, isArray, assignmentValue);
	}
}
