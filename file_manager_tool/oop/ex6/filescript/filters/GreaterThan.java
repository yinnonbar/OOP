package oop.ex6.filescript.filters;

import java.io.File;

import oop.ex6.filescript.exceptions.PositiveNumExpectedException;

/**
 * This is the filter for greater than that checks if a file size is bigger 
 * than a given size.
 * 
 * @author yinnonbar
 * 
 */
public class GreaterThan extends SizeFilters implements Filter {

	/**
	 * A constructor for this class.
	 * 
	 * @param size
	 *            - a given size.
	 * @throws PositiveNumExpectedException
	 */
	public GreaterThan(double size) throws PositiveNumExpectedException {
		super(size);
	}

	/**
	 * Overriding the isPass method and returns true if the file's size is
	 * bigger than the given size, else false.
	 */
	@Override
	public boolean isPass(File file) {
		return file.length() / bytes > this.size;
	}

}
