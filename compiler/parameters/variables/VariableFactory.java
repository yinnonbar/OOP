package oop.ex7.main.parameters.variables;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.parameters.IllegalNameException;

/**
 * This class represents the variable factory.
 * 
 * @author roeia1
 * 
 */
public class VariableFactory {

	/**
	 * Creating a variable.
	 * 
	 * @param type
	 *            - the variable type.
	 * @param arraySign
	 *            - the array sign.
	 * @param name
	 *            - the name of the variable.
	 * @param assignmentvalue
	 *            - the assignment value of the new variable.
	 * @return The new variable.
	 * @throws IllegalNameException
	 *             if the name of the variable is a keyword.
	 * @throws IllegalSyntaxException
	 *             if the assignment string has a syntax error.
	 */
	public static Variable createVariable(String type, String arraySign,
		String name, String assignmentvalue) throws IllegalNameException,
		IllegalSyntaxException {
		boolean isArray = false;
		// Checking if the variable is array
		if (arraySign != null) {
			isArray = !(arraySign.equals(""));
		}
		Variable newVariable;
		// Creating a variable parameter
		switch (type) {
		case "int":
			if (name == null) {
				if (arraySign == null) {
					// Creating a raw parameter
					newVariable = new IntegerType();
				} else {
					// Creating a return type variable for a method declaration
					newVariable = new IntegerType(isArray);
				}
			} else if (assignmentvalue != null) {
				newVariable = new IntegerType(name, isArray, assignmentvalue);
			} else {
				newVariable = new IntegerType(name, isArray);
			}
			break;
		case "boolean":
			if (name == null) {
				if (arraySign == null) {
					// Creating a raw parameter
					newVariable = new BooleanType();
				} else {
					// Creating a return type variable for a method declaration
					newVariable = new BooleanType(isArray);
				}
			} else if (assignmentvalue != null) {
				newVariable = new BooleanType(name, isArray, assignmentvalue);
			} else {
				newVariable = new BooleanType(name, isArray);
			}
			break;
		case "String":
			if (name == null) {
				if (arraySign == null) {
					// Creating a raw parameter
					newVariable = new StringType();
				} else {
					// Creating a return type variable for a method declaration
					newVariable = new StringType(isArray);
				}
			} else if (assignmentvalue != null) {
				newVariable = new StringType(name, isArray, assignmentvalue);
			} else {
				newVariable = new StringType(name, isArray);
			}
			break;
		case "char":
			if (name == null) {
				if (arraySign == null) {
					// Creating a raw parameter
					newVariable = new CharType();
				} else {
					// Creating a return type variable for a method declaration
					newVariable = new CharType(isArray);
				}
			} else if (assignmentvalue != null) {
				newVariable = new CharType(name, isArray, assignmentvalue);
			} else {
				newVariable = new CharType(name, isArray);
			}
			break;
		case "double":
			if (name == null) {
				if (arraySign == null) {
					// Creating a raw parameter
					newVariable = new DoubleType();
				} else {
					// Creating a return type variable for a method declaration
					newVariable = new DoubleType(isArray);
				}
			} else if (assignmentvalue != null) {
				newVariable = new DoubleType(name, isArray, assignmentvalue);
			} else {
				newVariable = new DoubleType(name, isArray);
			}
			break;
		default:
			throw new IllegalVariableTypeException("Variable type error");
		}
		return newVariable;
	}
}
