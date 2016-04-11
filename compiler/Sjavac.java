package oop.ex7.main;

import java.io.IOException;

import oop.ex7.main.scopes.ConditionalScope;
import oop.ex7.main.scopes.Scope;

/**
 * This class is the main class of program. This is the compiler, it is taking
 * the lines from the parser and prints the matching message.
 * 
 * @author roeia1
 * 
 */
public class Sjavac {

	public static Scope MAIN_SCOPE;

	/**
	 * This is the main. Prints the matching message - 0 - if the code is legal.
	 * 1 - if the code is illegal. 2- in case of IO errors.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MAIN_SCOPE = new Scope(null);
		// the try and catch structure. try if no exception was thrown, means
		// no error and print 0.
		try {
			Parser.Parse(args[0]);
			CheckScope(MAIN_SCOPE);
			System.out.println("0");
			// if met an IllegalSyntaxException or CompilationException than
			// prints 1 and the matching error message as was given in the
			// throw exception.
		} catch (IllegalSyntaxException | CompilationException e) {
			System.out.println("1");
			System.err.println(e.getMessage());
			e.printStackTrace();
			// another catch for the IO exception.
		} catch (IOException e) {
			System.out.println("2");
		}
	}

	/**
	 * A static method that in each iteration checks all the s-java line of the
	 * current scope, and later checks all of it's child.
	 * 
	 * @param scopeToCheck
	 *            - a given scope.
	 * @throws CompilationException
	 *             if there is a compilation error.
	 */
	protected static void CheckScope(Scope scopeToCheck)
		throws CompilationException {
		// if the scope to check is an if/while scope than check the condition
		if (scopeToCheck instanceof ConditionalScope) {
			((ConditionalScope) scopeToCheck).CheckCondition();
		}
		// sending to is valid for each scope and later for all the child
		// scopes
		for (SJavaLine currLine : scopeToCheck.getLineList()) {
			currLine.isValid(scopeToCheck);
		}
		for (Scope currScope : scopeToCheck.getChildScopeList()) {
			CheckScope(currScope);
		}
	}
}
