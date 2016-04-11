package oop.ex6.filescript.order;
import oop.ex6.filescript.exceptions.OrderTypeException;

/**
 * This class is the factory for the orders, "creates" the match order as given
 * by user.
 * @author yinnonbar
 *
 */
public class OrderFactory {
	private final static String numberSymbol = "#";
	/**
	 * This method creates the order depends on the given string in the input.
	 * @param order - a string which is a given order.
	 * @return the order that the user entered.
	 * @throws OrderTypeException if a wrong order was given.
	 */
	public static Order createOrder (String order) throws OrderTypeException {
		// As written in the PDF in case the order does not appear than the 
		// Abs should be used.
		if (order == null || order.equals("FILTER")){
			return new Abs();
		}
		// Splitting where the # appears
		String[] cutCommand = order.split(numberSymbol);
		Order newOrder;
		// This is the switch where the factory creates the matching order as
		// given in the input from the user.
		switch (cutCommand[0]){
		case "abs":
			newOrder = new Abs();
			break;
		case "type":
			newOrder = new Type();
			break;
		case "size":
			newOrder = new Size();
			break;
		default:
			throw new OrderTypeException();
		}
		// If a "REVERSE" is found in the end of the line than reverse it
		// using the reverse order.
		if(order.endsWith("REVERSE")){
			newOrder = new Reverse(newOrder);
		}
		return newOrder;
	}
}
