package oop.ex6.filescript.filters;

import java.io.File;

/**
 * All files are matched.
 * 
 * @author yinnonbar
 * 
 */
public class AllFilter implements Filter {

	/**
	 * Overriding the isPass method and returns always true.
	 */
	@Override
	public boolean isPass(File file) {
		return true;
	}

}
