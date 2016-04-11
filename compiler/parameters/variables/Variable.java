package oop.ex7.main.parameters.variables;

import java.util.ArrayList;

import oop.ex7.main.AssignmentLine;
import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.ReturnLine;
import oop.ex7.main.SJavaLine;
import oop.ex7.main.Sjavac;
import oop.ex7.main.parameters.IllegalNameException;
import oop.ex7.main.parameters.MethodCalling;
import oop.ex7.main.parameters.Operandable;
import oop.ex7.main.parameters.Parameter;
import oop.ex7.main.parameters.ParameterCompilationException;
import oop.ex7.main.parameters.ParameterNotExistException;
import oop.ex7.main.scopes.Scope;

/**
 * This abstract class represent all of the legitimate variables in s-java. It
 * extends the parameter class.
 * 
 * @author roeia1
 * 
 */
public abstract class Variable extends Parameter {

	private boolean isArray;

	/**
	 * Constructing a raw variable.
	 */
	protected Variable() {
		super();
	}

	/**
	 * Constructing a new variable.
	 * 
	 * @param name
	 *            - the name of the variable.
	 * @throws IllegalNameException
	 *             if the variable name isn't valid (a keyword).
	 */
	protected Variable(String name) throws IllegalNameException {
		super(name);
	}

	/**
	 * Constructing a new variable.
	 * 
	 * @param isArray
	 *            - represents if the variable is an array.
	 */
	protected Variable(boolean isArray) {
		this.isArray = isArray;
	}

	/**
	 * Constructing a new variable.
	 * 
	 * @param name
	 *            - the name of the variable.
	 * @param isArray
	 *            - represents if the variable is an array.
	 * @throws IllegalNameException
	 *             if the variable name isn't valid (a keyword).
	 */
	protected Variable(String name, boolean isArray)
		throws IllegalNameException {
		super(name);
		this.isArray = isArray;
	}

	/**
	 * Constructing a new variable with an assignment value.
	 * 
	 * @param name
	 *            - the name of the variable.
	 * @param isArray
	 *            - represents if the variable is an array.
	 * @param assignmentValue
	 *            - the assignment value string.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error with the assignment string.
	 */
	protected Variable(String name, boolean isArray, String assignmentValue)
		throws IllegalSyntaxException {
		super(name);
		this.isArray = isArray;
		if (isArray) {
			this.parameterList = arrayAssignment(assignmentValue);
		} else {
			this.parameterList.add(createParameter(assignmentValue));
		}
	}

	public void isValid(Scope currentScope)
		throws VariableAlreadyExistException, ParameterCompilationException {
		try {
			this.find(currentScope, true);
			throw new VariableAlreadyExistException(
				"Already exist variable error");
		} catch (VariableNotExistException e) {
			for (Parameter currParameter : this.getParameterList()) {
				this.checkAssignment(currParameter, currentScope);
			}
		}
	}

	/**
	 * Checking if the assignment of the parameter input is valid for this
	 * variable.
	 * 
	 * @param parameter
	 *            - the assigning parameter.
	 * @param currentScope
	 *            - the current scope of the variable.
	 * @throws ParameterNotExistException
	 *             if the parameter doesn't exist.
	 * @throws NotOperandableVariableException
	 *             if assigning parameters with operand and one of them is a
	 *             variable that isn't operandable.
	 * @throws VariableTypeMismatchException
	 *             if assigning a variable that isn't from the same type as the
	 *             assigned one.
	 * @throws UninitializedVariableException
	 *             if assigning a variable that isn't initialized.
	 * @throws VoidMethodException
	 *             if assigning a method that returns void.
	 * @throws NotOperandableMethodCallingException
	 *             if assigning parameters with operand and one of them is a
	 *             method calling that returns a variable that isn't
	 *             operandable.
	 * @throws MethodCallingTypeMismatchException
	 *             if assigning a method calling that returns a variable that
	 *             isn't from the same type as the assigned one.
	 * @throws ParameterCompilationException
	 *             if the parameter is a method calling and there is a
	 *             compilation error when checking validation.
	 */
	public void checkAssignment(Parameter parameter, Scope currentScope)
		throws ParameterNotExistException, NotOperandableVariableException,
		VariableTypeMismatchException, UninitializedVariableException,
		VoidMethodException, NotOperandableMethodCallingException,
		MethodCallingTypeMismatchException, ParameterCompilationException {
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		Variable foundVariable;
		parameters.add(parameter);
		// Checking if the assignment is with two parameters with operand
		if (parameter instanceof MethodCalling) {
			if (parameter.getParameterList().size() > 
			((MethodCalling) parameter).getNumOfParameters()) {
				parameters.add(parameter.getParameterList().get(
					((MethodCalling) parameter).getNumOfParameters()));
			}
		} else if (!parameter.getParameterList().isEmpty()) {
			parameters.add(parameter.getParameterList().get(0));
		}
		for (Parameter currParameter : parameters) {
			if (currParameter instanceof GenericType) {
				foundVariable = currParameter.find(currentScope, false);
				if (parameters.size() == 2
					&& !(foundVariable instanceof Operandable)) {
					throw new NotOperandableVariableException(
						"Assignment using operand with a parameter that"
						+ " is not operanable");
				}
				if (!this.getClass().isInstance(foundVariable)) {
					throw new VariableTypeMismatchException(
						"Assignment of a variable not from same type");
				}
				if (!foundVariable.isInitialized()) {
					throw new UninitializedVariableException(
						"Assignment of an uninitialized variable");
				}
			} else if (currParameter instanceof MethodCalling) {
				((MethodCalling) currParameter).isValid(currentScope);
				foundVariable = currParameter.find(currentScope, false);
				if (foundVariable == null) {
					throw new VoidMethodException(
						"Assignment using a method calling that return void");
				}
				if (parameters.size() == 2
					&& !(foundVariable instanceof Operandable)) {
					throw new NotOperandableMethodCallingException(
						"Assignment using operand with a parameter that is"
						+ " not operanable");
				}
				if (!this.getClass().isInstance(foundVariable)) {
					throw new MethodCallingTypeMismatchException(
						"Assignment of a method calling that not returning "
						+ "same type");
				}
			} else if (parameters.size() == 2
				&& !(this instanceof Operandable)) {
				throw new NotOperandableVariableException(
					"Assignment using operand with a parameter that is not "
					+ "operanable");
			} else if (!this.getClass().isInstance(currParameter)) {
				throw new VariableTypeMismatchException(
					"Assignment of a raw variable that not from the same "
					+ "type");
			}
		}
	}

	public Variable find(Scope currentScope, boolean creationCheck)
		throws VariableNotExistException {
		for (SJavaLine currLine : currentScope.getLineList()) {
			// check for all the cases which there are not the return line
			if (!(currLine instanceof ReturnLine)) {
				// check if the variable exists
				if (this == currLine
					|| (currLine instanceof Variable && ((Variable) currLine)
						.getParameterList().contains(this))
					|| (currLine instanceof AssignmentLine && 
						((AssignmentLine) currLine)
						.getAssignedGeneric() == this)) {
					break;
				}
				if (currLine instanceof Variable
					&& ((Variable) currLine).getName().equals(this.getName())) {
					return (Variable) currLine;
				}
			}
		}
		// going to the father scope.
		currentScope = currentScope.getFatherScope();
		if (creationCheck) {
			if (currentScope != null && currentScope != Sjavac.MAIN_SCOPE) {
				return (Variable) recursiveFind(currentScope, creationCheck);
			}
		} else {
			if (currentScope != null) {
				return (Variable) recursiveFind(currentScope, creationCheck);
			}
		}
		throw new VariableNotExistException("Variable doesn't exist");
	}

	private Parameter recursiveFind(Scope currentScope, boolean creationCheck)
		throws VariableNotExistException {
		// running on the lines of current scope.
		for (SJavaLine currLine : currentScope.getLineList()) {
			// if found it than return the current line as variable or
			// parameter depends on the instance it has.
			if (currLine instanceof Variable
				&& ((Variable) currLine).getName().equals(this.getName())) {
				return (Variable) currLine;
			}
		}
		// going to the father scope.
		currentScope = currentScope.getFatherScope();
		if (creationCheck) {
			if (currentScope != Sjavac.MAIN_SCOPE) {
				return (Variable) recursiveFind(currentScope, creationCheck);
			}
		} else {
			if (currentScope != null) {
				return (Variable) recursiveFind(currentScope, creationCheck);
			}
		}
		throw new VariableNotExistException("Variable doesn't exist");
	}

	/**
	 * Checking if a variable is initialized.
	 * 
	 * @return True if the variable initialized, false otherwise.
	 */
	public boolean isInitialized() {
		return !this.getParameterList().isEmpty();
	}

	public boolean isArray() {
		return isArray;
	}
}
