package oop.ex6.filescript.order;

import java.io.File;

/**
 * This class implements the order interface and sort files by file type,
 * going from 'a' to 'z'.
 * @author yinnonbar
 *
 */
public class Type implements Order {
	private final static String dot = ".";
	/**
	 * This method implementing the compare method and compares between the
	 * names of the types.
	 */
	@Override
	public int compare(File o1, File o2) {
		// looking for the index of the last '.' in the files names
		// since after that point is the name of the type of the file
		// substringing from there till the end and compares between the 
		// names.
		int o1LastPoint = o1.getName().lastIndexOf(dot);
		String firstType = o1.getName().substring(o1LastPoint);
		int o2LastPoint = o2.getName().lastIndexOf(dot);
		String secondType = o2.getName().substring(o2LastPoint);
		if (firstType.compareTo(secondType) == 0) {
			return o1.compareTo(o2);
		}
		return firstType.compareTo(secondType);
	}
}
