package oop.ex6.filescript.filters;

import java.io.File;

/**
 * This filter checks if a given name is equal to the file's name.
 * 
 * @author yinnonbar
 * 
 */
public class FileName extends StringFilters implements Filter {
	public FileName(String fileName) {
		super(fileName);

	}

	/**
	 * Overriding the isPass method and checks if the name is equal to the 
	 * file. If it is returns true, else false.
	 */
	@Override
	public boolean isPass(File file) {
		return (file.getName().equals(this.name));
	}

}
