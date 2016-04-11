package oop.ex7.main.scopes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.SJavaLine;
import oop.ex7.main.SJavaSyntax;
import oop.ex7.main.parameters.IllegalNameException;
import oop.ex7.main.parameters.Parameter;
import oop.ex7.main.parameters.variables.Variable;
import oop.ex7.main.parameters.variables.VariableFactory;


/**
 * This class represents a method scope in the s-java file. Extends the Scope
 * class.
 * 
 * @author roeia1
 * 
 */
public class MethodScope extends Scope {

	private final static String DECLARATION_PARAMETER_REGEX = "(?:\\s*"
		+ SJavaSyntax.TYPES_REGEX + "\\s*" + SJavaSyntax.METHOD_NAME_REGEX
		+ "\\s*)";
	private final static int PARAMETER_TYPE_GROUP = 1;
	private final static int PARAMETER_ARRAY_SIGN_GROUP = 2;
	private final static int PARAMETER_NAME_GROUP = 3;
	private String name;
	private Variable returnType;
	private int numOfParameters;

	/**
	 * A data constructor.
	 * 
	 * @param fatherScope
	 *            - the father scope of this method scope.
	 * @param returnType
	 *            - the return type of the method.
	 * @param arraySign
	 *            - the return type array sign.
	 * @param name
	 *            - the method name.
	 * @param parameters
	 *            - the declaration parameters of the method.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error in the method scope.
	 */
	public MethodScope(Scope fatherScope, String returnType, String arraySign,
		String name, String parameters) throws IllegalSyntaxException {
		super(fatherScope);
		Parameter.checkIfNameIsKeyword(name);
		this.name = name;
		if (returnType.equals("void")) {
			this.returnType = null;
		} else {
			this.returnType =
				VariableFactory.createVariable(returnType, arraySign, null,
					null);
		}
		/*
		 * Dividing the given parameters string, and creating each parameter
		 * while counting them.
		 */
		this.numOfParameters = 0;
		if (!parameters.equals("")) {
			SJavaSyntax.checkIfStartOrEndWithComma(parameters);
			String[] parametersArray = parameters.split(SJavaSyntax.COMMA);
			Pattern parameterPattern =
				Pattern.compile(DECLARATION_PARAMETER_REGEX);
			Matcher parameterMatcher;
			for (String currParameter : parametersArray) {
				parameterMatcher = parameterPattern.matcher(currParameter);
				if (parameterMatcher.matches()) {
					Variable declarationParameter =
						VariableFactory
							.createVariable(parameterMatcher
								.group(PARAMETER_TYPE_GROUP), parameterMatcher
								.group(PARAMETER_ARRAY_SIGN_GROUP),
								parameterMatcher.group(PARAMETER_NAME_GROUP),
								null);
					// Check if the variable name already exist
					// in the declaration
					for (SJavaLine currLine : this.getLineList()) {
						if (declarationParameter.getName().equals(
							((Variable) currLine).getName())) {
							throw new IllegalNameException(""
								+ "Already exist variable error");
						}
					}
					// Making the declaration parameter initialized by
					// adding to it a raw parameter from the same type
					declarationParameter.getParameterList().add(
						VariableFactory.createVariable(parameterMatcher
							.group(PARAMETER_TYPE_GROUP), null, null, null));
					this.getLineList().add(declarationParameter);
					this.numOfParameters++;
				} else {
					throw new IllegalSyntaxException(
						"Illegal parameter of method decleration error");
				}
			}

		}
	}

	public int getNumOfParameters() {
		return numOfParameters;
	}

	public String getName() {
		return name;
	}

	public Variable getReturnValue() {
		return returnType;
	}

}
