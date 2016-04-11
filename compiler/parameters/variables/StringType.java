package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.parameters.IllegalNameException;

/**
 * This class represents a string variable, extends from variable class.
 * 
 * @author roeia1
 *
 */
public class StringType extends Variable {

	/** The regular expression of a raw String */
	public final static String STRING_REGEX = "^\\s*\".*\"\\s*$";

	/**
	 * Constructing a raw String.
	 */
	public StringType() {
		super();
	}

	/**
	 * Constructing a return value String for a method.
	 * 
	 * @param isArray
	 *            - represents if the return value String is an array.
	 */
	public StringType(boolean isArray) {
		super(isArray);
	}

	/**
	 * Constructing a new String variable.
	 * 
	 * @param name
	 *            - the name of the String variable.
	 * @param isArray
	 *            - represents if the new String variable is an array.
	 * @throws IllegalNameException
	 *             if the String variable name isn't valid (a keyword).
	 */
	public StringType(String name, boolean isArray)
		throws IllegalNameException {
		super(name, isArray);
	}

	/**
	 * Constructing a new String variable with an assignment value.
	 * 
	 * @param name
	 *            - the name of the String variable.
	 * @param isArray
	 *            - represents if the new String variable is an array.
	 * @param assignmentValue
	 *            - the assignment value string.
	 * @throws IllegalNameException
	 *             if the String variable name isn't valid (a keyword).
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error with the assignment string.
	 */
	public StringType(String name, boolean isArray, String assignmentValue)
		throws IllegalNameException, IllegalSyntaxException {
		super(name, isArray, assignmentValue);
	}
}
