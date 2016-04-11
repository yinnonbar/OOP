import java.util.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {
	long timeBefore;
	long timeAfter;
	long difference;
	long fastest;
	String[] data1 = Ex4Utils
			.file2array("/cs/stud/yinnonbar/workspace/ex4/data1.txt");
	String[] data2 = Ex4Utils
			.file2array("/cs/stud/yinnonbar/workspace/ex4/data2.txt");
	SimpleSet fastestName;

	/**
	 * the constructor method which creates a new simple set which is an array
	 * of all of the structures
	 * 
	 * @return a new structure contains all the structures.
	 */
	public SimpleSet[] newStructure() {
		SimpleSet[] allData = new SimpleSet[] {
				new CollectionFacadeSet(new LinkedList<String>()),
				new CollectionFacadeSet(new TreeSet<String>()),
				new CollectionFacadeSet(new HashSet<String>()),
				new ChainedHashSet(), new OpenHashSet() };
		return allData;
	}

	/**
	 * This method gets as input a text and calculates how much time it takes 
	 * to add it to each structure.
	 * 
	 * @param dataName
	 *            the name of the text we're using.
	 * @param data
	 *            an array with all the words in that text
	 * @param allData
	 *            a structure which contains all the structures.
	 */
	public void add(String dataName, String[] data, SimpleSet[] allData) {
		// putting in fastest a very large number (2^63-1) so it would be like
		// infinity and we can put the first value as fastest.
		fastest = Long.MAX_VALUE;
		for (int i = 0; i < allData.length; i++) {
			// checks the time before
			timeBefore = new Date().getTime();
			for (String str : data) {
				allData[i].add(str);
			}
			// takes the time after and then checking the difference between
			// them by subtracting timeBefore from timeAfter
			timeAfter = new Date().getTime();
			difference = timeAfter - timeBefore;
			// checks if the difference is smaller than fastest than it's the
			// most fastest therefore save it's name.
			if (difference < fastest) {
				fastest = difference;
				fastestName = allData[i];
			}
			System.out.println("The Time adding all the data in " + dataName
					+ "to " + allData[i] + " is " + difference);

		}
		System.out.println("The fastest structure initilization " + dataName
				+ ", is " + fastestName);

	}

	/**
	 * This method gets as input a text and calculates how much time it takes 
	 * to find if it on the text.
	 * @param dataName
	 *            - the name of the text were using.
	 * @param searchVal
	 *            -the value which were looking for.
	 * @param allData
	 *            - a structure which contains all the structures.
	 */
	private void contains(String dataName, String searchVal, SimpleSet[] 
			allData) {
		// run over all the structures in simpleSet
		for (int i = 0; i < allData.length; i++) {
			// checks the time before and after and make the differ.
			timeBefore = new Date().getTime();
			allData[i].contains(searchVal);
			timeAfter = new Date().getTime();
			difference = timeAfter - timeBefore;
			System.out.println("Check if contains(" + searchVal + ") in "
					+ allData[i] + " with " + dataName + "" + " takes: "
					+ difference);
		}
	}

	public static void main(String[] args) {

		//reading from the text to a array containing the strings.
		String[] data1 = Ex4Utils.file2array("data1.txt");
		String[] data2 = Ex4Utils.file2array("data2.txt");
		// creating a new structure for data1
		SimpleSetPerformanceAnalyzer newStructure1 = new 
				SimpleSetPerformanceAnalyzer();
		SimpleSet[] simpleSetList1 = newStructure1.newStructure();
		// adding all the data to new structure and check contains for data 2
		newStructure1.add("data1", data1, simpleSetList1);
		newStructure1.contains("data1", "hi", simpleSetList1);
		newStructure1.contains("data1", "-1317089015", simpleSetList1);

		// creating a new structure for data2
		SimpleSetPerformanceAnalyzer newStructure2 = new 
				SimpleSetPerformanceAnalyzer();
		SimpleSet[] simpleSetList2 = newStructure2.newStructure();
		// adding all the data to new structure and check contains for data 2
		newStructure2.add("data2", data2, simpleSetList2);
		newStructure2.contains("data2", "hi", simpleSetList2);
		newStructure2.contains("data2", "23", simpleSetList2);

	}
}
