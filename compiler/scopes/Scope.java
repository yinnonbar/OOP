package oop.ex7.main.scopes;

import java.util.ArrayList;

import oop.ex7.main.SJavaLine;

/**
 * This class represents a scope in the s-java file.
 * 
 * @author roeia1
 * 
 */
public class Scope {

	private ArrayList<SJavaLine> lineList;
	private ArrayList<Scope> childScopeList;
	private Scope fatherScope;

	/**
	 * A data constructor.
	 * 
	 * @param fatherScope
	 *            - The father scope of this scope.
	 */
	public Scope(Scope fatherScope) {
		this.fatherScope = fatherScope;
		this.lineList = new ArrayList<SJavaLine>();
		this.childScopeList = new ArrayList<Scope>();
	}

	public Scope getFatherScope() {
		return fatherScope;
	}

	public ArrayList<SJavaLine> getLineList() {
		return lineList;
	}

	public ArrayList<Scope> getChildScopeList() {
		return childScopeList;
	}
}
