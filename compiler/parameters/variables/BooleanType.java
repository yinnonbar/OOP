package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.parameters.IllegalNameException;

/**
 * This class is the BooleanType extends the variable class (true\false).
 * 
 * @author roeia1
 * 
 */
public class BooleanType extends Variable {

	/** The regular expression of a raw boolean */
	public final static String BOOLEAN_REGEX = "^\\s*true|false\\s*$";

	/**
	 * Constructing a raw boolean.
	 */
	public BooleanType() {
		super();
	}

	/**
	 * Constructing a return value boolean for a method.
	 * 
	 * @param isArray
	 *            - represents if the return value boolean is an array.
	 */
	public BooleanType(boolean isArray) {
		super(isArray);
	}

	/**
	 * Constructing a new boolean variable.
	 * 
	 * @param name
	 *            - the name of the boolean variable.
	 * @param isArray
	 *            - represents if the new boolean variable is an array.
	 * @throws IllegalNameException
	 *             if the boolean variable name isn't valid (a keyword).
	 */
	public BooleanType(String name, boolean isArray)
		throws IllegalNameException {
		super(name, isArray);
	}

	/**
	 * Constructing a new boolean variable with an assignment value.
	 * 
	 * @param name
	 *            - the name of the boolean variable.
	 * @param isArray
	 *            - represents if the new boolean variable is an array.
	 * @param assignmentValue
	 *            - the assignment value string.
	 * @throws IllegalNameException
	 *             if the boolean variable name isn't valid (a keyword).
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error with the assignment string.
	 */
	public BooleanType(String name, boolean isArray, String assignmentValue)
		throws IllegalNameException, IllegalSyntaxException {
		super(name, isArray, assignmentValue);
	}
}
