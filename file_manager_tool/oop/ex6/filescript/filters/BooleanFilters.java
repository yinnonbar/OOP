package oop.ex6.filescript.filters;

import oop.ex6.filescript.exceptions.IllegalArgumentException;

/**
 * This is the parent abstract class for all the filters that returns a boolean
 * value and depends on the answer given YES or NO.
 * 
 * @author yinnonbar
 * 
 */
public abstract class BooleanFilters implements Filter {
	protected boolean value;

	/**
	 * This is the constructor of this class.
	 * 
	 * @param answer
	 *            - the given "answer".
	 * @throws IllegalArgumentException
	 */
	public BooleanFilters(String answer) throws IllegalArgumentException {
		// If the answer is "YES" than the value is true, if "NO" the value
		// is false.
		if (answer.equals("YES")) {
			this.value = true;
		} else if (answer.equals("NO")) {
			this.value = false;
		}
		// If a not matching answer was given than throw exception.
		else {
			throw new IllegalArgumentException();
		}
	}
}
