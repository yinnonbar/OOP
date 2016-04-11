package oop.ex7.main;

import java.util.ArrayList;

import oop.ex7.main.parameters.IllegalParameterException;
import oop.ex7.main.parameters.Parameter;
import oop.ex7.main.parameters.variables.Variable;
import oop.ex7.main.scopes.MethodScope;
import oop.ex7.main.scopes.Scope;

/**
 * This class represents a return line (Consist the "return" expression).
 * Extending the SjavaLine class.
 * 
 * @author roeia1
 * 
 */
public class ReturnLine extends SJavaLine {

	private ArrayList<Parameter> parameterList;
	private boolean isRawArray;

	/**
	 * A data constructor.
	 * 
	 * @param returnValue
	 *            - the return value.
	 * @throws IllegalParameterException
	 *             if one of the parameters string isn't match for any type.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error in the parameters string.
	 */
	public ReturnLine(String returnValue) throws IllegalParameterException,
		IllegalSyntaxException {
		isRawArray = false;
		parameterList = new ArrayList<Parameter>();
		if (!returnValue.equals("")) {
			// Check if the return value is an array
			if (returnValue.matches(SJavaSyntax.ARRAY_ASSIGNMENT_REGEX)) {
				parameterList = Parameter.arrayAssignment(returnValue);
				isRawArray = true;
			} else {
				parameterList.add(Parameter.createParameter(returnValue));
			}
		}
	}

	public void isValid(Scope currentScope) throws CompilationException {
		if (currentScope instanceof MethodScope) {
			// Checking if the return line in the method scope is not the last
			// line
			if (currentScope.getLineList().indexOf(this) != currentScope
				.getLineList().size() - 1) {
				throw new IllegalReturnLocationUsageException(
					"Illegal return location usage inside a method scope");
			}
		} else {
			currentScope = currentScope.getFatherScope();
			while (!(currentScope instanceof MethodScope)) {
				currentScope = currentScope.getFatherScope();
			}
		}
		Variable methodReturnValue =
			((MethodScope) currentScope).getReturnValue();
		// Return line inside a void return_type method
		if (methodReturnValue == null) {
			if (!this.parameterList.isEmpty()) {
				throw new CompilationException(
					"Return a value in a void return_type method");
			}
		} else {
			if (methodReturnValue.isArray()) {
				if (this.parameterList.isEmpty() && !this.isRawArray) {
					throw new CompilationException(
						"Mismatch type between the return value and line, "
							+ "one is array and one isn't");
				}
			} else {
				if (this.isRawArray) {
					throw new CompilationException(
						"Returning raw array when the return "
							+ "value of the method isn't array");
				}
				if (this.parameterList.isEmpty()) {
					throw new CompilationException(
						"No return parameter in a method "
							+ "that doesn't return void");
				}
			}
			// Checking if every parameter in the return line matching the
			// return type of the method
			for (Parameter currParameter : this.parameterList) {
				methodReturnValue.checkAssignment(currParameter, currentScope);
			}
		}
	}

	public ArrayList<Parameter> getParameterList() {
		return parameterList;
	}

	public boolean isRawArray() {
		return isRawArray;
	}
}
