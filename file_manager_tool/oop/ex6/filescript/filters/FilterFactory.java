package oop.ex6.filescript.filters;

import oop.ex6.filescript.exceptions.IllegalArgumentException;

/**
 * This class is the factory for the filters, "creates" the match filter as
 * given by user.
 * 
 * @author yinnonbar
 * 
 */
public class FilterFactory {
	private final static String numberSymbol = "#";

	/**
	 * This method creates the filter depends on the given filter the user
	 * entered.
	 * 
	 * @param filter
	 *            - a string - the given input for filter
	 * @return the filter the user entered.
	 * @throws IllegalArgumentException
	 *             if a wrong input waas entered.
	 */
	public static Filter createFilter(String filter)
			throws IllegalArgumentException {
		// Splitting where the # appears
		String[] cutCommand = filter.split(numberSymbol);
		Filter newfilter;
		// This is the switch where the factory creates the matching order as
		// given in the input from the user.
		switch (cutCommand[0]) {
		case "greater_than":
			newfilter = new GreaterThan(Double.parseDouble(cutCommand[1]));
			break;
		case "between":
			newfilter = new Between(Double.parseDouble(cutCommand[1]),
					Double.parseDouble(cutCommand[2]));
			break;
		case "smaller_than":
			newfilter = new SmallerThan(Double.parseDouble(cutCommand[1]));
			break;
		case "file":
			newfilter = new FileName(cutCommand[1]);
			break;
		case "contains":
			newfilter = new ContainsName(cutCommand[1]);
			break;
		case "prefix":
			newfilter = new PrefixName(cutCommand[1]);
			break;
		case "suffix":
			newfilter = new SuffixName(cutCommand[1]);
			break;
		case "writable":
			newfilter = new WritableFilter(cutCommand[1]);
			break;
		case "executable":
			newfilter = new ExecutableFilter(cutCommand[1]);
			break;
		case "hidden":
			newfilter = new HiddenFilter(cutCommand[1]);
			break;
		case "all":
			newfilter = new AllFilter();
			break;
		default:
			throw new IllegalArgumentException();
		}
		// case for "NOT" filter
		if (filter.endsWith("NOT")) {
			newfilter = new NotFilter(newfilter);
		}
		return newfilter;
	}
}
