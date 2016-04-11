package oop.ex7.main;

import java.util.ArrayList;

import oop.ex7.main.parameters.Parameter;
import oop.ex7.main.parameters.ParameterCompilationException;
import oop.ex7.main.parameters.variables.GenericType;
import oop.ex7.main.parameters.variables.IntegerType;
import oop.ex7.main.parameters.variables.Variable;
import oop.ex7.main.scopes.Scope;

/**
 * This class represents an s-java assignment line. (X = Y)
 * 
 * @author roeia1
 * 
 */
public class AssignmentLine extends SJavaLine {

	private GenericType assignedGeneric;
	private Parameter assigningParameter;

	/**
	 * A data constructor.
	 * 
	 * @param assignedVariable
	 *            - the name of the variable being assigned.
	 * @param assignedValue
	 *            - the assignment value.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error in the assignment line.
	 */
	public AssignmentLine(String assignedVariable, String assignedValue)
		throws IllegalSyntaxException {
		Parameter assignedParameter =
			Parameter.createOneParameter(assignedVariable);
		if (assignedParameter instanceof GenericType) {
			this.assignedGeneric = (GenericType) assignedParameter;
		} else {
			throw new IllegalSyntaxException(
				"Illegal assignment line syntax error");
		}
		assigningParameter = Parameter.createParameter(assignedValue);
	}

	public void isValid(Scope currentScope)
		throws ParameterCompilationException {
		// Checking the array location if it is array
		if (assignedGeneric.isArray()) {
			ArrayList<Parameter> arrayLocationParameters =
				new ArrayList<Parameter>();
			// Creating an array from the parameters of the array location
			arrayLocationParameters.add(assignedGeneric
				.getArrayLocationParameter());
			if (!assignedGeneric.getArrayLocationParameter()
				.getParameterList().isEmpty()) {
				arrayLocationParameters.add(assignedGeneric
					.getArrayLocationParameter().getParameterList().get(0));
			}
			for (Parameter currArrayLocationParameter : 
				arrayLocationParameters) {
				// If the parameter is a generic or raw and not being integer,
				// or a method calling not returning integer throw exception
				new IntegerType().checkAssignment(currArrayLocationParameter,
					currentScope);
			}
		}
		// Finding the assigned generic variable
		Variable assignedVariable =
			this.assignedGeneric.find(currentScope, false);
		// Checking if its a valid assignment
		assignedVariable
			.checkAssignment(this.assigningParameter, currentScope);

	}

	public Variable getAssignedGeneric() {
		return assignedGeneric;
	}

	public Parameter getAssigningParameter() {
		return assigningParameter;
	}
}
