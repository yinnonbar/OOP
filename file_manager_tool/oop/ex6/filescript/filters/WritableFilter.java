package oop.ex6.filescript.filters;

import java.io.File;

import oop.ex6.filescript.exceptions.IllegalArgumentException;

/**
 * This filter checks if a file has writing permission or not.
 * 
 * @author yinnonbar
 * 
 */
public class WritableFilter extends BooleanFilters implements Filter {
	/**
	 * The constructor for this class.
	 * 
	 * @param answer
	 *            - the given answer.
	 * @throws IllegalArgumentException
	 */
	public WritableFilter(String answer) throws IllegalArgumentException {
		super(answer);
	}

	/**
	 * Overriding the isPass method and returns true if the file has writing
	 * permission, else false.
	 */
	@Override
	public boolean isPass(File file) {
		return file.canWrite() == this.value;
	}

}
