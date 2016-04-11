package oop.ex7.main.parameters;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.SJavaSyntax;
import oop.ex7.main.Sjavac;
import oop.ex7.main.parameters.variables.Variable;
import oop.ex7.main.scopes.MethodScope;
import oop.ex7.main.scopes.Scope;

/**
 * This class represents a s-java line method calling. Extending the Parameter
 * class.
 * 
 * @author roeia1
 * 
 */
public class MethodCalling extends Parameter {

	private int numOfParameters;

	/**
	 * Constructing a new method calling.
	 * 
	 * @param name
	 *            - the name of the method.
	 * @param parameters
	 *            - the string of all the parameters.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error in the method calling.
	 */
	public MethodCalling(String name, String parameters)
		throws IllegalSyntaxException {
		super(name);
		numOfParameters = 0;
		if (!parameters.equals("")) {
			SJavaSyntax.checkIfStartOrEndWithComma(parameters);
			String[] parameterStrings = parameters.split(SJavaSyntax.COMMA);
			for (String currParameter : parameterStrings) {
				parameterList.add(Parameter.createParameter(currParameter));
				numOfParameters++;
			}

		}
	}

	public void isValid(Scope currentScope)
		throws ParameterCompilationException, MethodCallingNotExistException {
		MethodScope calledMethodScope = null;
		// Searching for the method declaration
		for (Scope currMethodScope : Sjavac.MAIN_SCOPE.getChildScopeList()) {
			if (((MethodScope) currMethodScope).getName().equals(
				this.getName())) {
				calledMethodScope = (MethodScope) currMethodScope;
				break;
			}
		}
		if (calledMethodScope == null) {
			throw new MethodCallingNotExistException(
				"Calling a method that don't exist error");
		}
		// Checking the method declaration and calling have the same number of
		// parameters
		if (calledMethodScope.getNumOfParameters() != this
			.getNumOfParameters()) {
			throw new MethodCallingException(
				"Mismatch number of variables in method calling error");
		} else {
			// Checking if each parameter in the calling match the type
			// in the declaration
			for (int currParameterIndex = 0; currParameterIndex < 
				calledMethodScope.getNumOfParameters(); currParameterIndex++) {
				((Variable) calledMethodScope.getLineList().get(
					currParameterIndex)).checkAssignment(this
					.getParameterList().get(currParameterIndex), currentScope);
			}
		}
	}

	public Variable find(Scope currentScope, boolean creationCheck)
		throws MethodCallingNotExistException {
		for (Scope currMethodScope : Sjavac.MAIN_SCOPE.getChildScopeList()) {
			if (((MethodScope) currMethodScope).getName().equals(
				this.getName())) {
				return ((MethodScope) currMethodScope).getReturnValue();
			}
		}
		throw new MethodCallingNotExistException("Method isn't found");
	}

	public int getNumOfParameters() {
		return numOfParameters;
	}
}
