package oop.ex6.filescript.filters;

/**
 * This is an abstract class parent for all the filters that deals with 
 * strings.
 * 
 * @author yinnonbar
 * 
 */
public abstract class StringFilters {
	protected String name;

	/**
	 * This is the constructor for this class.
	 * 
	 * @param name
	 *            - a given name.
	 */
	public StringFilters(String name) {
		this.name = name;
	}

}
