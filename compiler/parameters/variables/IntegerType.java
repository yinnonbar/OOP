package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.parameters.IllegalNameException;

/**
 * This class represents the integer type variable, extends from double type.
 * 
 * @author roeia1
 *
 */
public class IntegerType extends DoubleType {

	/** The regular expression of a raw integer */
	public static final String INTEGER_REGEX = "^\\s*\\-?\\s*\\d+\\s*$";

	/**
	 * Constructing a raw integer.
	 */
	public IntegerType() {
		super();
	}

	/**
	 * Constructing a return value integer for a method.
	 * 
	 * @param isArray
	 *            - represents if the return value integer is an array.
	 */
	public IntegerType(boolean isArray) {
		super(isArray);
	}

	/**
	 * Constructing a new integer variable.
	 * 
	 * @param name
	 *            - the name of the integer variable.
	 * @param isArray
	 *            - represents if the new integer variable is an array.
	 * @throws IllegalNameException
	 *             if the integer variable name isn't valid (a keyword).
	 */
	public IntegerType(String name, boolean isArray)
		throws IllegalNameException {
		super(name, isArray);
	}

	/**
	 * Constructing a new integer variable with an assignment value.
	 * 
	 * @param name
	 *            - the name of the integer variable.
	 * @param isArray
	 *            - represents if the new integer variable is an array.
	 * @param assignmentValue
	 *            - the assignment value string.
	 * @throws IllegalNameException
	 *             if the integer variable name isn't valid (a keyword).
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error with the assignment string.
	 */
	public IntegerType(String name, boolean isArray, String assignmentValue)
		throws IllegalNameException, IllegalSyntaxException {
		super(name, isArray, assignmentValue);
	}

}
