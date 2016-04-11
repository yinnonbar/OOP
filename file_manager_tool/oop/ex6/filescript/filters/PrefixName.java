package oop.ex6.filescript.filters;

import java.io.File;

/**
 * This class checks if a given string is the prefix of the file.
 * 
 * @author yinnonbar
 * 
 */
public class PrefixName extends StringFilters implements Filter {
	/**
	 * The constructor for this class.
	 * 
	 * @param fileName
	 */
	public PrefixName(String fileName) {
		super(fileName);

	}

	/**
	 * Overriding the isPass method and returns true if the file's name starts
	 * with the given string, else returns false.
	 */
	@Override
	public boolean isPass(File file) {
		return file.getName().startsWith(name);
	}

}
