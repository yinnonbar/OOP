package oop.ex7.main;

import oop.ex7.main.scopes.Scope;

/**
 * This class represents an s-java line in the file being read.
 * 
 * @author roeia1
 * 
 */
public abstract class SJavaLine {

	/**
	 * This method check if the current s-java line is compilation valid.
	 * 
	 * @param currentScope
	 *            - the current scope.
	 * @throws CompilationException
	 *             if the s-java line has a compilation error.
	 */
	public abstract void isValid(Scope currentScope)
		throws CompilationException;
}
