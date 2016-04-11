package oop.ex7.main.scopes;

import oop.ex7.main.IllegalSyntaxException;
import oop.ex7.main.parameters.IllegalParameterException;
import oop.ex7.main.parameters.MethodCalling;
import oop.ex7.main.parameters.Parameter;
import oop.ex7.main.parameters.ParameterNotExistException;
import oop.ex7.main.parameters.variables.BooleanType;
import oop.ex7.main.parameters.variables.GenericType;
import oop.ex7.main.parameters.variables.Variable;

/**
 * This class represents a scope containing a condition. (if / while scope).
 * Extending the Scope class.
 * 
 * @author roeia1
 * 
 */
public class ConditionalScope extends Scope {

	private Parameter condition;

	/**
	 * A data constructor.
	 * 
	 * @param fatherScope
	 *            - the father scope of this scope.
	 * @param condition
	 *            - the condition of this scope.
	 * @throws IllegalParameterException
	 *             if the condition isn't match for any parameter type.
	 * @throws IllegalSyntaxException
	 *             if the condition is a method calling and there is a syntax
	 *             error.
	 */
	public ConditionalScope(Scope fatherScope, String condition)
		throws IllegalParameterException, IllegalSyntaxException {
		super(fatherScope);
		this.condition = Parameter.createOneParameter(condition);
	}

	/**
	 * Checking if the condition is a boolean parameter.
	 * 
	 * @throws MismatchConditionTypeException
	 *             if the condition isn't a boolean parameter.
	 * @throws ParameterNotExistException
	 *             if the condition is a parameter and it doesn't exist.
	 */
	public void CheckCondition() throws MismatchConditionTypeException,
		ParameterNotExistException {
		if (!(this.condition instanceof BooleanType)) {
			if (this.condition instanceof GenericType
				|| this.condition instanceof MethodCalling) {
				Variable foundVariable =
					this.condition.find(this.getFatherScope(), false);
				if (!(foundVariable instanceof BooleanType)) {
					throw new MismatchConditionTypeException(
						"Mismatch condition type error");
				}
				if (this.condition instanceof GenericType
					&& !foundVariable.isInitialized()) {
					throw new MismatchConditionTypeException(
						"Boolean variable not initialized in the condition");
				}
			} else {
				throw new MismatchConditionTypeException(
					"Mismatch condition type error");
			}
		}
	}

	public Parameter getCondition() {
		return condition;
	}
}
