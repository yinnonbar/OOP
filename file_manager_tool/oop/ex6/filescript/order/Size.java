package oop.ex6.filescript.order;

import java.io.File;

/**
 * This class implements the order interface and sorting the files by their 
 * sizes from the smallest to the largest.
 * @author yinnonbar
 *
 */
public class Size implements Order {
	private final static double bytes = 1024;
	/**
	 * This method implementing the compare method from comparator and
	 * compare between the two given files shows who is the bigger.
	 */
	@Override
	public int compare(File o1, File o2) {
		if (o1.length() / bytes > o2.length() / bytes) {
			return 1;
		}
		else if (o1.length() / bytes < o2.length() / bytes) {
			return -1;
		}
		else {
			return o1.compareTo(o2);
		}
	}
}
