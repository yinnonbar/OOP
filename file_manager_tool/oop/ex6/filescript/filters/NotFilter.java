package oop.ex6.filescript.filters;

import java.io.File;

/**
 * This class is the NOT filter, means this filter satisfies exactly all files
 * not satisfied by the original filter.
 * 
 * @author yinnonbar
 * 
 */
public class NotFilter implements Filter {
	Filter filter;

	/**
	 * This is the constructor for this class, gets the requested filter.
	 * 
	 * @param filter
	 */
	public NotFilter(Filter filter) {
		this.filter = filter;
	}

	/**
	 * Overriding the isPass method and returning the negation for the filter.
	 */
	@Override
	public boolean isPass(File file) {

		return (!filter.isPass(file));
	}
}
