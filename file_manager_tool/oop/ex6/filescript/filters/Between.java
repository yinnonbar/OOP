package oop.ex6.filescript.filters;

import java.io.File;

import oop.ex6.filescript.exceptions.BetweenNumbersException;
import oop.ex6.filescript.exceptions.IllegalArgumentException;
import oop.ex6.filescript.exceptions.PositiveNumExpectedException;

/**
 * Determine whether a file size is between the given numbers.
 * 
 * @author yinnonbar
 * 
 */
public class Between extends SizeFilters implements Filter {
	private final double size2;

	/**
	 * The constructor for this class.
	 * 
	 * @param size1
	 *            - the size of the first file.
	 * @param size2
	 *            - the size of the second file.
	 * @throws IllegalArgumentException
	 */
	public Between(double size1, double size2) throws IllegalArgumentException 
	{
		super(size1);
		this.size2 = size2;
		// checks for exceptions if size 2 is negative and
		if (size2 < negativeNum) {
			throw new PositiveNumExpectedException();
		}
		if (size1 > size2) {
			throw new BetweenNumbersException();
		}
	}

	/**
	 * This method determine if a file's size is between two given sizes and
	 * return true if it is, else false.
	 */
	public boolean isPass(File file) {
		return (file.length() / bytes >= this.size && file.length() / bytes 
				<= size2);
	}

}
