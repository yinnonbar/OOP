package oop.ex6.filescript.order;

import java.io.File;

/**
 * This class implements the order interface and means that the file should be 
 * printed opposite to the given order.
 * @author yinnonbar
 *
 */
public class Reverse implements Order {
	private final static int Reversal = -1;
	Order order;
	/**
	 * A constructor which construct the order as given from the user.
	 * @param order
	 */
	public Reverse (Order order){
		this.order = order;
	}
	/**
	 * This method implementing the compare method from comparator and just
	 * multiply be -1 so if the original result is 1 now it should be -1 
	 * means the opposite (of course for 0 stays the same (-1*0 = 0).
	 */
	@Override
	public int compare(File o1, File o2) {
		// multiply by -1 the comparison number.
		return Reversal * (this.order.compare(o1, o2));
	}
}
