package oop.ex7.main;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import oop.ex7.main.parameters.MethodCalling;
import oop.ex7.main.parameters.variables.VariableFactory;
import oop.ex7.main.scopes.ConditionalScope;
import oop.ex7.main.scopes.MethodScope;
import oop.ex7.main.scopes.Scope;

/**
 * This class is the parser. It is reading the lines in the file and turning it
 * to objects while checking each line syntax.
 * 
 * @author roeia1
 * 
 */
public class Parser {
	/**
	 * Parsing the file with the given file location, checking for valid s-java
	 * syntax.
	 * 
	 * @param fileLocation
	 *            - the file location.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error.
	 */
	public static void Parse(String fileLocation)
		throws IllegalSyntaxException, IOException {
		LineNumberReader lineReader =
			new LineNumberReader(new FileReader(fileLocation));
		Scope currentScope = Sjavac.MAIN_SCOPE;
		String currLine = lineReader.readLine();
		// reading the given file while the current line is not null, means
		// there's a line to read, dividing it to cases with switch
		// according to the matching regex
		while (currLine != null) {
			SJavaSyntax.Syntax syntax = SJavaSyntax.getSyntax(currLine);
			switch (syntax) {
			case BLANK_LINE:
				break;
			case COMMENT:
				break;
			case END_OF_SCOPE:
				if (currentScope == null) {
					throw new IllegalSyntaxException("Illegal } usage error");
				} else {
					currentScope = currentScope.getFatherScope();
				}
				break;
			case DECLARATION_METHOD:
				// Method calling
				if (syntax.getLineMatcher().group(
					SJavaSyntax.METHOD_RETURN_TYPE_GROUP) == null) {
					// Checking if called in main scope and throw exception
					// if it does since it's an illegal calling for method
					// at the main scope.
					if (currentScope == Sjavac.MAIN_SCOPE) {
						throw new IllegalSyntaxException(
							"Method calling outside a method "
								+ "without assignment to a member");
					} else {
						currentScope.getLineList().add(
							new MethodCalling(syntax.getLineMatcher().group(
								SJavaSyntax.METHOD_NAME_GROUP), syntax
								.getLineMatcher().group(
									SJavaSyntax.METHOD_PARAMETERS_GROUP)));
					}
					// Method declaration
				} else {
					// Checking if declared in main scope
					if (currentScope == Sjavac.MAIN_SCOPE) {
						Sjavac.MAIN_SCOPE
							.getChildScopeList()
							.add(
								new MethodScope(
									Sjavac.MAIN_SCOPE,
									syntax.getLineMatcher().group(
										SJavaSyntax.METHOD_RETURN_TYPE_GROUP),
									syntax
										.getLineMatcher()
										.group(
											SJavaSyntax
											.METHOD_RETURN_ARRAY_SIGN_GROUP),
									syntax.getLineMatcher().group(
										SJavaSyntax.METHOD_NAME_GROUP),
									syntax.getLineMatcher().group(
										SJavaSyntax.METHOD_PARAMETERS_GROUP)));
						currentScope =
							currentScope.getChildScopeList().get(
								currentScope.getChildScopeList().size() - 1);
					} else {
						throw new IllegalSyntaxException(
							"Method declaration not in main scope error");
					}
				}
				break;
			case RETURN:
				currentScope.getLineList().add(
					new ReturnLine(syntax.getLineMatcher().group(
						SJavaSyntax.RETURN_VALUE_GROUP)));
				break;
			case SIMPLE_SCOPE:
				// Checking if the simple scope opens in the main scope
				// (if / while usage in main scope)
				if (currentScope == Sjavac.MAIN_SCOPE) {
					throw new IllegalSyntaxException(
						"if/while usage out of a method scope error");
					// else adding that simple scope as a child to the
					// current
					// scope.
				} else {
					currentScope.getChildScopeList().add(
						new ConditionalScope(currentScope, syntax
							.getLineMatcher().group(
								SJavaSyntax.SIMPLE_SCOPE_CONDITION_GROUP)));
					currentScope =
						currentScope.getChildScopeList().get(
							currentScope.getChildScopeList().size() - 1);
				}
				break;
			case VARIABLE:
				// in case the line matching to a variable send it to the
				// variable factory.
				currentScope.getLineList().add(
					VariableFactory.createVariable(syntax.getLineMatcher()
						.group(SJavaSyntax.VARIABLE_TYPE_GROUP), syntax
						.getLineMatcher().group(
							SJavaSyntax.VARIABLE_ARRAY_SIGN_GROUP), syntax
						.getLineMatcher().group(
							SJavaSyntax.VARIABLE_NAME_GROUP), syntax
						.getLineMatcher().group(
							SJavaSyntax.VARIABLE_VALUE_GROUP)));
				break;
			case ASSIGNMENT:
				// in case of an assignment (=) sending the current line to
				// assignment line constructor.
				currentScope.getLineList().add(
					new AssignmentLine(syntax.getLineMatcher().group(
						SJavaSyntax.ASSIGNMENT_VARIABLE_GROUP), syntax
						.getLineMatcher().group(
							SJavaSyntax.ASSIGNMENT_VALUE_GROUP)));
				break;
			case CALLING_METHOD:
				currentScope.getLineList().add(
					new MethodCalling(syntax.getLineMatcher().group(
						SJavaSyntax.METHOD_CALLING_NAME_GROUP), syntax
						.getLineMatcher().group(
							SJavaSyntax.METHOD_CALLING_PARAMETERS_GROUP)));
				break;
			default:
				// in case that the line is not matching to any of the
				// cases above throw an illegal syntax exception.
				throw new IllegalSyntaxException("Illegal syntax line error");

			}
			currLine = lineReader.readLine();
		}
		lineReader.close();
	}
}
