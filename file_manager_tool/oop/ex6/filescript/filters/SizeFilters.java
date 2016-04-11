package oop.ex6.filescript.filters;

import oop.ex6.filescript.exceptions.PositiveNumExpectedException;

/**
 * This is an abstract class and is the parent class for all the filters that
 * checks for size values.
 * 
 * @author yinnonbar
 * 
 */
public abstract class SizeFilters implements Filter {
	protected double size;
	protected final static double bytes = 1024;
	protected final double negativeNum = 0;

	/**
	 * The constructor for this class.
	 * 
	 * @param size
	 *            - a given size for file.
	 * @throws PositiveNumExpectedException
	 */
	public SizeFilters(double size) throws PositiveNumExpectedException {
		// if an illegal size was given (negative number because there's no
		// file with a negative size then throws an exception.
		if (size < negativeNum) {
			throw new PositiveNumExpectedException();
		}
		this.size = size;

	}
}
