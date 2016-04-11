package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.parameters.IllegalNameException;
import oop.ex7.main.parameters.Operandable;

/**
 * This class is the DoubleType extends the variable class (5,5.2), and it is
 * also implements Operandable interface (5+3.2).
 * 
 * @author roeia1
 * 
 */
public class DoubleType extends Variable implements Operandable {

	/** The regular expression of a raw double */
	public final static String DOUBLE_REGEX = "^\\s*\\-?\\s*\\d+\\.\\d+\\s*$";

	/**
	 * Constructing a raw double.
	 */
	public DoubleType() {
		super();
	}

	/**
	 * Constructing a return value double for a method.
	 * 
	 * @param isArray
	 *            - represents if the return value double is an array.
	 */
	public DoubleType(boolean isArray) {
		super(isArray);
	}

	/**
	 * Constructing a new double variable.
	 * 
	 * @param name
	 *            - the name of the double variable.
	 * @param isArray
	 *            - represents if the new double variable is an array.
	 * @throws IllegalNameException
	 *             if the double variable name isn't valid (a keyword).
	 */
	public DoubleType(String name, boolean isArray)
		throws IllegalNameException {
		super(name, isArray);
	}

	/**
	 * Constructing a new double variable with an assignment value.
	 * 
	 * @param name
	 *            - the name of the double variable.
	 * @param isArray
	 *            - represents if the new double variable is an array.
	 * @param assignmentValue
	 *            - the assignment value string.
	 * @throws IllegalNameException
	 *             if the double variable name isn't valid (a keyword).
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error with the assignment string.
	 */
	public DoubleType(String name, boolean isArray, String assignmentValue)
		throws IllegalNameException, IllegalSyntaxException {
		super(name, isArray, assignmentValue);
	}
}
