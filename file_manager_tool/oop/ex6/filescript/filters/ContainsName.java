package oop.ex6.filescript.filters;

import java.io.File;

/**
 * This filter checks if a string is contained in a file name.
 * 
 * @author yinnonbar
 * 
 */
public class ContainsName extends StringFilters implements Filter {
	public ContainsName(String fileName) {
		super(fileName);
	}

	/**
	 * Implementation of the isPass method. gets a file and checks if a string
	 * is contained in the file's name.
	 */
	@Override
	public boolean isPass(File file) {
		return (file.getName().contains(this.name));
	}

}
