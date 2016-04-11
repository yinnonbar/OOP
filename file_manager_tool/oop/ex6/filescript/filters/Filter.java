package oop.ex6.filescript.filters;

import java.io.File;

/**
 * This is the parent interface for all of the filters. Showing basically how a
 * filters should be like, and therefore has one method returns a boolean that
 * determine if a filter is passed or not.
 * 
 * @author yinnonbar
 * 
 */
public interface Filter {

	/**
	 * A method that determine whether a filter passes or not.
	 * 
	 * @param file
	 *            - a given file
	 * @return true if filter passes, else returns false.
	 */
	public boolean isPass(File file);
}
