package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.SJavaSyntax;
import oop.ex7.main.parameters.IllegalNameException;
import oop.ex7.main.parameters.IllegalParameterException;
import oop.ex7.main.parameters.Parameter;

/**
 * This class is the GenericType variable, extends the variable class.
 * GenericType is a variable which his type is unknown in the syntax check
 * phase.
 * 
 * @author roeia1
 * 
 */
public class GenericType extends Variable {

	/** The regular expression of a generic variable */
	public final static String GENERIC_REGEX = "^\\s*\\-?\\s*"
		+ SJavaSyntax.VARIABLE_NAME_REGEX + "\\s*(?:"
		+ SJavaSyntax.ASSIGNMENT_ARRAY_SIGN + ")?\\s*$";
	private Parameter arrayLocationParameter;

	/**
	 * Constructing a new generic variable.
	 * 
	 * @param name
	 *            - the name of the generic variable.
	 * @throws IllegalNameException
	 *             if the generic variable name isn't valid (a keyword).
	 */
	public GenericType(String name) throws IllegalNameException {
		super(name, false);
	}

	/**
	 * Constructing a new generic variable that's inside an array (abc[1]).
	 * 
	 * @param name
	 *            - the name of the generic variable.
	 * @param arrayLocationParameter
	 *            - the location inside the array.
	 * @throws IllegalSyntaxException
	 *             if the array location parameter is a method calling with a
	 *             syntax error.
	 * @throws IllegalParameterException
	 *             if the array location parameter string isn't match for any
	 *             type.
	 * @throws IllegalArrayLocationException
	 *             if the array location is a raw integer that is negative.
	 */
	public GenericType(String name, String arrayLocationParameter)
		throws IllegalParameterException, IllegalSyntaxException,
		IllegalArrayLocationException {
		super(name, true);
		this.arrayLocationParameter =
			Parameter.createParameter(arrayLocationParameter);
		// Checking if the location is a negative number
		if (this.arrayLocationParameter instanceof IntegerType
			&& this.arrayLocationParameter.getParameterList().isEmpty()
			&& arrayLocationParameter.contains("-")) {
			throw new IllegalArrayLocationException(
				"Illegal array location error");
		}

	}

	public Parameter getArrayLocationParameter() {
		return arrayLocationParameter;
	}
}
