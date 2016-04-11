package oop.ex6.filescript.filters;

import java.io.File;

/**
 * This class checks if a given string is the suffix of the file.
 * 
 * @author yinnonbar
 * 
 */
public class SuffixName extends StringFilters implements Filter {
	/**
	 * The constructor for this class.
	 * 
	 * @param fileName
	 */
	public SuffixName(String fileName) {
		super(fileName);
	}

	/**
	 * Overriding the isPass method and returns true if the file's name ends
	 * with the given string, else returns false.
	 */
	@Override
	public boolean isPass(File file) {
		return file.getName().endsWith(name);

	}

}
