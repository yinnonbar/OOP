package oop.ex6.filescript.filters;

import java.io.File;
import oop.ex6.filescript.exceptions.IllegalArgumentException;

/**
 * This filter checks if a file is a hidden file.
 * 
 * @author yinnonbar
 * 
 */
public class HiddenFilter extends BooleanFilters implements Filter {

	/**
	 * A constructor for this class.
	 * 
	 * @param answer
	 * @throws IllegalArgumentException
	 */
	public HiddenFilter(String answer) throws IllegalArgumentException {
		super(answer);
	}

	/**
	 * Overriding the isPass method and return true if the file is hidden, else
	 * false.
	 */
	@Override
	public boolean isPass(File file) {
		return file.isHidden() == value;
	}

}
