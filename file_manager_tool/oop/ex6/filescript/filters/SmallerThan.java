package oop.ex6.filescript.filters;

import java.io.File;
import oop.ex6.filescript.exceptions.PositiveNumExpectedException;

/**
 * This filter checks if the file is smaller than a given size.
 * 
 * @author yinnonbar
 * 
 */
public class SmallerThan extends SizeFilters implements Filter {

	/**
	 * The constructor for this class.
	 * 
	 * @param size
	 *            - the given size.
	 * @throws PositiveNumExpectedException
	 */
	public SmallerThan(double size) throws PositiveNumExpectedException {
		super(size);
	}

	/**
	 * Overriding the isPass method returns true if the file's size is smaller
	 * than the given size, else false.
	 */
	@Override
	public boolean isPass(File file) {
		return file.length() / bytes < this.size;
	}

}
