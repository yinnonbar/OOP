package oop.ex7.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents all the valid syntax for a line in s-java file.
 * 
 * @author roeia1
 * 
 */
public class SJavaSyntax {

	public final static int RETURN_VALUE_GROUP = 1;
	public final static int VARIABLE_TYPE_GROUP = 1;
	public final static int VARIABLE_ARRAY_SIGN_GROUP = 2;
	public final static int VARIABLE_NAME_GROUP = 3;
	public final static int VARIABLE_VALUE_GROUP = 4;
	public final static int ASSIGNMENT_VARIABLE_GROUP = 1;
	public final static int GENERIC_VARIABLE_NAME_GROUP = 1;
	public final static int GENERIC_ARRAY_LOCATION_GROUP = 2;
	public final static int ASSIGNMENT_VALUE_GROUP = 4;
	public final static int METHOD_RETURN_TYPE_GROUP = 1;
	public final static int METHOD_RETURN_ARRAY_SIGN_GROUP = 2;
	public final static int METHOD_NAME_GROUP = 3;
	public final static int METHOD_PARAMETERS_GROUP = 4;
	public final static int METHOD_CALLING_NAME_GROUP = 1;
	public final static int METHOD_CALLING_PARAMETERS_GROUP = 2;
	public final static int TYPE_LOCATION = 1;
	public final static int ARRAY_LOCATION = 2;
	public final static int NAME_LOCATION = 3;
	public final static int EQUAL_SIGN_LOCATION = 4;
	public final static int VALUE_LOCATION = 5;
	public final static int SIMPLE_SCOPE_CONDITION_GROUP = 2;
	public final static String COMMA = "\\s*\\,\\s*";
	public final static String ARRAY_SIGN = "\\[\\s*\\]";
	public final static String ASSIGNMENT_ARRAY_SIGN = "\\[\\s*(.*)\\s*\\]";
	public final static String TYPES_REGEX = "(int|boolean|char|double|"
		+ "String)\\s*(" + ARRAY_SIGN + ")?";
	public final static String METHOD_RETURN_TYPE_REGEX = "(int|boolean|"
		+ "char|double|String|void)\\s*(" + ARRAY_SIGN + ")?";
	public final static String SIMPLE_SCOPE_NAME = "(if|while)";
	public final static String SIMPLE_SCOPE_REGEX = "^\\s*"
		+ SIMPLE_SCOPE_NAME + "\\s*\\(\\s*(.*?)\\s*\\)\\s*\\{\\s*$";
	public final static String COMMENT_PATTERN = "\\s*//.*\\s*";
	public final static String METHOD_NAME_REGEX = "([A-Za-z]\\w*)";
	public final static String VARIABLE_NAME_REGEX = "([A-Za-z]\\w*|_\\w+)";
	public final static String VARIABLE_REGEX = "^\\s*(?:" + TYPES_REGEX
		+ ")\\s+" + VARIABLE_NAME_REGEX + "\\s*(?:\\=\\s*(.*)\\s*)?\\;\\s*$";
	public final static String ASSIGNMENT_REGEX = "^\\s*("
		+ VARIABLE_NAME_REGEX + "(?:\\s*" + ASSIGNMENT_ARRAY_SIGN
		+ ")?)\\s*\\=\\s*(.*)\\s*\\;\\s*$";
	public final static String ARRAY_ASSIGNMENT_REGEX = "^\\{\\s*(.*)\\s*\\}$";
	public final static String DECLARATION_METHOD_REGEX = "^\\s*"
		+ METHOD_RETURN_TYPE_REGEX + " +" + METHOD_NAME_REGEX + "\\s*"
		+ "\\(\\s*(.*)\\s*\\)\\s*\\{\\s*\\;?\\s*$";
	public final static String METHOD_CALLING_REGEX = "^\\s*"
		+ METHOD_NAME_REGEX + "\\s*" + "\\(\\s*(.*)\\s*\\)\\s*\\;?\\s*$";
	public final static String OPERAND_REGEX = "\\/|\\*|\\-|\\+";
	public final static String ANYTHING_BUT_OPERAND_REGEX =
		"([^\\/|\\*|\\-|\\+]+)";
	public final static String[] KEYWORDS = new String[] { "void", "int",
		"double", "boolean", "char", "String" };
	public final static String BLANK_LINE_PATTERN = "\\s*";
	public final static String RETURN_REGEX =
		"^\\s*return\\s*(.*)\\s*\\;\\s*$";
	public final static String END_OF_SCOPE_REGEX = "^\\s*\\}\\s*$";

	/**
	 * This enum containing all the valid syntax for a line in a s-java file.
	 * 
	 * @author roeia1
	 * 
	 */
	public enum Syntax {
		VARIABLE(VARIABLE_REGEX),
		DECLARATION_METHOD(DECLARATION_METHOD_REGEX), CALLING_METHOD(
			METHOD_CALLING_REGEX), COMMENT(COMMENT_PATTERN), SIMPLE_SCOPE(
			SIMPLE_SCOPE_REGEX), BLANK_LINE(BLANK_LINE_PATTERN), RETURN(
			RETURN_REGEX), END_OF_SCOPE(END_OF_SCOPE_REGEX), ASSIGNMENT(
			ASSIGNMENT_REGEX);

		private final String syntax;
		private Matcher lineMatcher;

		/**
		 * A data constructor.
		 * 
		 * @param syntax
		 *            - the valid syntax of the given line option.
		 */
		Syntax(String syntax) {
			this.syntax = syntax;
		}

		public void setLineMatcher(String line) {
			this.lineMatcher = Pattern.compile(syntax).matcher(line);
		}

		public Matcher getLineMatcher() {
			return lineMatcher;
		}

	}

	/**
	 * This static method checking a given s-java line from the file and return
	 * the valid syntax value from the enum.
	 * 
	 * @param line
	 *            - the line from the s-java file.
	 * @return The matched value from the enum.
	 * @throws IllegalSyntaxException
	 *             if there is a syntax error in the line.
	 */
	public static Syntax getSyntax(String line) throws IllegalSyntaxException {
		for (Syntax syntax : Syntax.values()) {
			syntax.setLineMatcher(line);
			if (syntax.getLineMatcher().matches()) {
				return syntax;
			}
		}
		throw new IllegalSyntaxException("Syntax error");
	}

	public static void checkIfStartOrEndWithComma(String value)
		throws IllegalSyntaxException {
		if (value.matches(COMMA + ".*") || value.matches(".*?" + COMMA)) {
			throw new IllegalSyntaxException(
				"Illegal comma placemnt syntax error)");
		}
	}
}
