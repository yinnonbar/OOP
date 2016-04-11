package oop.ex6.filescript.filters;

import java.io.File;

import oop.ex6.filescript.exceptions.IllegalArgumentException;

/**
 * This filter checks if a given file has execution permission.
 * 
 * @author yinnonbar
 * 
 */
public class ExecutableFilter extends BooleanFilters implements Filter {
	public ExecutableFilter(String answer) throws IllegalArgumentException {
		super(answer);
	}

	/**
	 * Overriding the isPass method and return true if the file is exectuable.
	 */
	@Override
	public boolean isPass(File file) {
		return file.canExecute() == this.value;
	}

}
