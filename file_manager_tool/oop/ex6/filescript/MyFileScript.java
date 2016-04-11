package oop.ex6.filescript;

import java.io.File;
import java.util.List;

import oop.ex6.filescript.exceptions.Type2Exception;
/**
 * This is the manager class of the program. It makes all the running of the 
 * program.
 * @author Yinnon Bratspiess
 *
 */
public class MyFileScript {

	/**
	 * This method gets the two given arguments and create section with the 
	 * command file and shows the matching files.  
	 * @param args - The source directory and the file command.
	 */
	public static void main(String[] args) {
		try {
		// gets the 2 given arguments.
		String sourceDir = args[0];
		String commandFile = args[1];
		// parsing the file.
		List<Sections> sectionsList = Parser.createSectionList(commandFile);
	    // getting a directory
	    File directory = new File(sourceDir);
	    File[] filesList = directory.listFiles();
	    // checking each section
	    for (Sections section : sectionsList) {
	        List<String> warningsList = section.getWarnings();
	        for (String warning : warningsList) {
	            System.out.println(warning);
	        }
	        List<File> returnedFiles = section.passedFiles(filesList);
	        // printing all the files that passed the filter
	        
	        for (File file : returnedFiles) {
	            System.out.println(file.getName());

	        }
	    }

	} catch (IndexOutOfBoundsException | Type2Exception newException) {
	    System.err.print("ERROR");
	    return;
	}	
	}
}
