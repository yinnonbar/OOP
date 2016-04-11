package oop.ex6.filescript;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import oop.ex6.filescript.exceptions.IllegalArgumentException;
import oop.ex6.filescript.exceptions.OrderTypeException;
import oop.ex6.filescript.exceptions.Type2Exception;
import oop.ex6.filescript.filters.Filter;
import oop.ex6.filescript.filters.FilterFactory;
import oop.ex6.filescript.order.Order;
import oop.ex6.filescript.order.OrderFactory;

/**
 * This class is the parser. It reads the command file and divide it into 
 * sections, where each section consists a filter and an order.
 * @author yinnonbar
 *
 */
public class Parser {
	/**
	 * This method creates the sections list. it splits the command file into
	 * sections.
	 * @param currentLine 
	 * @return A list of sections.
	 * @throws Type2Exception
	 */
	public static List<Sections> createSectionList(String currentLine) throws 
	Type2Exception {
		// creates an array list which will be the sections list.
		List<Sections> sectionsList = new ArrayList<Sections>();
		// a counter that going to count in which line we are.
		int linesCounter = 1;
		try (BufferedReader linesReader = new BufferedReader
				(new FileReader(currentLine));) {
			// reading the current line with the buffer method readLine. 
			String line = linesReader.readLine();
			// A while loop that running till the current line is not null, 
			// means we still have things to read from the file.
			while (line != null){
				// creates an array list of strings which will consist the 
				// warnings.
				List<String> warning = new ArrayList<String>();
				 Filter filter = null;
				 Order order = null;
				 // if were reading a filter
				 if (line.equals("FILTER")) {
					 line = linesReader.readLine();
					 linesCounter++;
					 try {
						 filter = FilterFactory.createFilter(line);
					 } catch (IllegalArgumentException newException) {
						 try {
							 filter = FilterFactory.createFilter("all");
							 warning.add("Warning in line " + linesCounter);
						 } catch (IllegalArgumentException newException1){
						 }
					 }
				 }
				 else { 
				 throw new Type2Exception();
				 }
				 line = linesReader.readLine();
				 linesCounter++;
				 // if were in a order line
				 if (line != null && line.equals("ORDER")) {
					 line = linesReader.readLine();
					 linesCounter++;
					 try {
						 order = OrderFactory.createOrder(line);
					 } catch(OrderTypeException newException) {
						 try {
							 order = OrderFactory.createOrder("abs");
							 warning.add("Warning in line " + linesCounter);
						 } catch (OrderTypeException newException1) {
						 }
					 }
				 } else {
					 throw new Type2Exception();
				 }
				 // creates the new section with the filter that weve read
				 // the order and the warning and add it to the sections list.
				 Sections section = new Sections(filter, order, warning);
				 sectionsList.add(section);
				 // if the line after the order is a FILTER line
				 if (line != null && !line.equals("FILTER")) {
					 line = linesReader.readLine();
					 linesCounter++;
				 }
			 }

		 } catch (IOException newException) {
			 throw new Type2Exception();
		 } 
		 return sectionsList;
	}

}