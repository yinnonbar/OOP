package oop.ex6.filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import oop.ex6.filescript.filters.Filter;
import oop.ex6.filescript.order.Order;

/**
 * This class checks if the files that were given passes the filter and 
 * sort them.
 * @author yinnonbar
 *
 */
public class Sections {
	private Filter filter;
	private Order order;
	
	private List <String> warnings;
	
	/**
	 * The constructor for this class, gets the filter, order and the warnings
	 * list.
	 * @param filter - a filter.
	 * @param order - an order.
	 * @param warnings - a warnings list.
	 */
	public Sections(Filter filter, Order order ,List<String>warnings) {
		this.filter = filter;
		this.order = order;
		this.warnings = warnings;
	}
	/**
	 * This method gets a files list and checks which of the files in the list
	 * passes the filter and then it add it to the list that should be printed
	 * and sort it.
	 * @param filesList - a list of files.
	 * @return a sorted list of files that passed the filter.
	 */
	public List<File> passedFiles(File[] filesList) {
		// creates a new list of files which will consists the files that
		// should be printed.
		List<File> toPrintFiles = new ArrayList<File>();
		// checks for each file if it meets the requirements of the filter
		// and if it does than add it to the list that has to be printed.
		for(File file:filesList){
			if (file.isFile()){
				if(this.filter.isPass(file)){
					toPrintFiles.add(file);
				}
			}
		}
		// sorts the list than return it.
		Collections.sort(toPrintFiles, this.order);
		return toPrintFiles;
	}
	/**
	 * A getter for the warnings list.
	 * @return the warnings list.
	 */
	public List<String> getWarnings() {
		return warnings;
	}
}
