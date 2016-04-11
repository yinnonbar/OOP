package oop.ex7.main.parameters;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.CompilationException;
import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.SJavaLine;
import oop.ex7.main.SJavaSyntax;
import oop.ex7.main.parameters.variables.BooleanType;
import oop.ex7.main.parameters.variables.CharType;
import oop.ex7.main.parameters.variables.DoubleType;
import oop.ex7.main.parameters.variables.GenericType;
import oop.ex7.main.parameters.variables.IntegerType;
import oop.ex7.main.parameters.variables.StringType;
import oop.ex7.main.parameters.variables.Variable;
import oop.ex7.main.scopes.Scope;

/**
 * This abstract class is the parameter class, extends SJavaLine and designed to
 * work with initializing variables and method calling.
 * 
 * @author roeia1
 * 
 */
public abstract class Parameter extends SJavaLine {

	/** The regular expression of two parameters */
	protected final static String TWO_PARAMETERS_ASSIGNMENT_REGEX =
		"^\\s*\\-?\\s*(.+?)\\s*(?:\\/|\\*|\\-|\\+)\\s*(.*)\\s*$";
	private final static int FIRST_PARAMETER_GROUP = 1;
	private final static int SECOND_PARAMETER_GROUP = 2;
	private final static int ARRAY_ASSIGNMENT_PARAMETERS_GROUP = 1;

	protected ArrayList<Parameter> parameterList;
	private String name;

	/**
	 * Constructing a raw parameter.
	 */
	protected Parameter() {
		parameterList = new ArrayList<Parameter>();
	}

	/**
	 * Constructing a new parameter.
	 * 
	 * @param name
	 *            - the name of the parameter.
	 * @throws IllegalNameException
	 *             if the parameter name isn't valid (a keyword).
	 */
	protected Parameter(String name) throws IllegalNameException {
		this();
		checkIfNameIsKeyword(name);
		this.name = name;
	}

	/**
	 * This enum include all the parameter types.
	 * 
	 * @author roeia1
	 * 
	 */
	private enum ParameterType {
		INTEGER(IntegerType.INTEGER_REGEX), DOUBLE(DoubleType.DOUBLE_REGEX),
		STRING(StringType.STRING_REGEX), CHAR(CharType.CHAR_REGEX), BOOLEAN(
			BooleanType.BOOLEAN_REGEX), METHOD_CALLING(
			SJavaSyntax.METHOD_CALLING_REGEX), GENERIC(
			GenericType.GENERIC_REGEX);

		private final String typeName;
		private Matcher typeMatcher;

		ParameterType(String type) {
			this.typeName = type;
		}

		public void setTypeMatcher(String line) {
			this.typeMatcher = Pattern.compile(typeName).matcher(line);
		}

		public Matcher getTypeMatcher() {
			return typeMatcher;
		}
	}

	/**
	 * Getting the type of the parameter string.
	 * 
	 * @param parameter
	 *            - the parameter string.
	 * @return The parameter type enum value.
	 * @throws IllegalParameterException
	 *             if the parameter string isn't match for any type.
	 */
	public static ParameterType getType(String parameter)
		throws IllegalParameterException {
		for (ParameterType parameterType : ParameterType.values()) {
			parameterType.setTypeMatcher(parameter);
			if (parameterType.getTypeMatcher().matches()) {
				return parameterType;
			}
		}
		throw new IllegalParameterException("Illegal parameter syntax error");
	}

	/**
	 * Creating one parameter.
	 * 
	 * @param parameter
	 *            - a given string which is the parameter.
	 * @return The created parameter.
	 * @throws IllegalParameterException
	 *             if the parameter string isn't match for any type.
	 * @throws IllegalSyntaxException
	 *             if the parameter is method calling and there is a syntax
	 *             error.
	 */
	public static Parameter createOneParameter(String parameter)
		throws IllegalParameterException, IllegalSyntaxException {
		ParameterType parameterType = getType(parameter);
		switch (parameterType) {
		case INTEGER:
			return new IntegerType();
		case BOOLEAN:
			return new BooleanType();
		case CHAR:
			return new CharType();
		case DOUBLE:
			return new DoubleType();
		case STRING:
			return new StringType();
		case GENERIC:
			// Check if the generic is not an array
			if (parameterType.getTypeMatcher().group(
				SJavaSyntax.GENERIC_ARRAY_LOCATION_GROUP) == null) {
				return new GenericType(parameterType.getTypeMatcher().group(
					SJavaSyntax.GENERIC_VARIABLE_NAME_GROUP));
			} else {
				return new GenericType(parameterType.getTypeMatcher().group(
					SJavaSyntax.GENERIC_VARIABLE_NAME_GROUP), parameterType
					.getTypeMatcher().group(
						SJavaSyntax.GENERIC_ARRAY_LOCATION_GROUP));
			}
		case METHOD_CALLING:
			return new MethodCalling(parameterType.getTypeMatcher().group(
				SJavaSyntax.METHOD_CALLING_NAME_GROUP), parameterType
				.getTypeMatcher().group(
					SJavaSyntax.METHOD_CALLING_PARAMETERS_GROUP));
		default:
			return null;
		}
	}

	/**
	 * Creating one parameter, or two parameters (first, operand, second). If
	 * two, adding the second parameter to the first parameter parameterList.
	 * 
	 * @param value
	 *            - a given value string.
	 * @return The created parameter.
	 * @throws IllegalParameterException
	 *             if the parameter string isn't match for any type.
	 * @throws IllegalSyntaxException
	 *             if the parameter is method calling and there is a syntax
	 *             error.
	 */
	public static Parameter createParameter(String value)
		throws IllegalSyntaxException, IllegalParameterException {
		Pattern twoParametersPattern =
			Pattern.compile(TWO_PARAMETERS_ASSIGNMENT_REGEX);
		Matcher twoParametersMatcher = twoParametersPattern.matcher(value);
		if (twoParametersMatcher.matches()) {
			Parameter newParameter =
				createOneParameter(twoParametersMatcher
					.group(FIRST_PARAMETER_GROUP));
			newParameter.getParameterList().add(
				createOneParameter(twoParametersMatcher
					.group(SECOND_PARAMETER_GROUP)));
			return newParameter;
		} else {
			return createOneParameter(value);
		}
	}

	/**
	 * Creating an ArrayList of parameters.
	 * 
	 * @param assignmentValue
	 *            - the assignment value string of an array.
	 * @return The ArrayList of parameters.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error in the parameters string.
	 * @throws IllegalParameterException
	 *             if one of the parameters string isn't match for any type.
	 */
	public static ArrayList<Parameter> arrayAssignment(String assignmentValue)
		throws IllegalSyntaxException, IllegalParameterException {
		Pattern arrayAssignmentPattern =
			Pattern.compile(SJavaSyntax.ARRAY_ASSIGNMENT_REGEX);
		Matcher arrayAssignmentMatcher =
			arrayAssignmentPattern.matcher(assignmentValue);
		// Checking if the array pattern matches
		if (arrayAssignmentMatcher.matches()) {
			ArrayList<Parameter> arrayAssignmentParameters =
				new ArrayList<Parameter>();
			// Checking if the array assignment not empty {}
			if (!arrayAssignmentMatcher.group(
				ARRAY_ASSIGNMENT_PARAMETERS_GROUP).equals("")) {
				SJavaSyntax.checkIfStartOrEndWithComma(arrayAssignmentMatcher
					.group(ARRAY_ASSIGNMENT_PARAMETERS_GROUP));
				// Splitting the parameters
				String[] parameterStrings =
					arrayAssignmentMatcher.group(
						ARRAY_ASSIGNMENT_PARAMETERS_GROUP).split(
						SJavaSyntax.COMMA);
				// Creating each parameter
				for (String currArrayParameter : parameterStrings) {
					arrayAssignmentParameters
						.add(createParameter(currArrayParameter));
				}

			}
			return arrayAssignmentParameters;
		} else {
			// If array pattern don't match
			throw new IllegalSyntaxException("Illegal assignment syntax error");
		}
	}

	/**
	 * Checking if a string is a restricted keyword.
	 * 
	 * @param value
	 *            - A given String.
	 * @throws IllegalNameException
	 *             being thrown if the given String is equal to one of the
	 *             keywords above.
	 */
	public static void checkIfNameIsKeyword(String value)
		throws IllegalNameException {
		for (String Keyword : SJavaSyntax.KEYWORDS) {
			if (value.equals(Keyword)) {
				throw new IllegalNameException(
					"Parameter name is a keyword error");
			}
		}
	}

	/**
	 * Finding a parameter.
	 * 
	 * @param currentScope
	 *            - the current scope.
	 * @return The found parameter. If it's a method calling then returning a
	 *         variable represents the return type of the method
	 * @throws NotExistVariableException
	 *             if the variable isn't exist.
	 * @throws NotMethodCallingException
	 *             if the method isn't exist.
	 * @throws CompilationException
	 */
	public abstract Variable find(Scope currentScope, boolean creationCheck)
		throws ParameterNotExistException;

	public ArrayList<Parameter> getParameterList() {
		return parameterList;
	}

	public String getName() {
		return name;
	}

}
