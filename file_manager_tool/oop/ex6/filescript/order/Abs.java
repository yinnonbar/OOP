package oop.ex6.filescript.order;

import java.io.File;

/**
 * Sort files by absolute name going from 'a' to 'z' implementing Order 
 * interface and acts as a comparator between the two given files.
 * @author yinnonbar
 *
 */
public class Abs implements Order {


	/**
	 * This method implementing the compare method from comparator and
	 * compares between the absolute path of two files and return in the 
	 * needed order.
	 */
	@Override
	public int compare(File o1, File o2) {
		return (o1.compareTo(o2));
	}

}
