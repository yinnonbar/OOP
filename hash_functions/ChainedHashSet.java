import java.awt.image.ReplicateScaleFilter;
import java.util.*;

public class ChainedHashSet extends SimpleHashSet {
	private ArrayList<LinkedList<String>> hashTable;
	private int elementCounter = 0;

	/**
	 * A default constructor. Constructs a new, empty table with default initial
	 * capacity (16), upper load factor (0.75) and lower load factor (0.25).
	 */
	public ChainedHashSet() {
		hashTable = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < tableSize; i++) {
			hashTable.add(new LinkedList<String>());
		}
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the
	 * default initial capacity (16).
	 * 
	 * @param upperLoadFactor
	 *            - The upper load factor of the hash table.
	 * @param lowerLoadFactor
	 *            - The lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		this();
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
	}

	public ChainedHashSet(java.lang.String[] data) {
		this();
		for (int i = 0; i < data.length; i++) {
			add(data[i]);

		}
	}

	/**
	 * Add a specified element to the set. 
	 * newValue - New value to add to the
	 * set returns False iff newValue already exists in the set.
	 */
	@Override
	public boolean add(String newValue) {
		boolean booleanCheck;
		this.hashValue = (newValue.hashCode()) & (tableSize - 1);
		// if the value is already in the matching linked_list return false
		if (hashTable.get(hashValue).contains(newValue)) {
			return false;
		}
		// else, adding the value to the matching linked_list and adding 1
		// to the element counter
		hashTable.get(hashValue).add(newValue);
		elementCounter++;
		// checks if its necessary to double the size of the table, if does than
		// send it to the reHashing method
		if ((float) size() / capacity() > upperLoadFactor) {
			booleanCheck = true;
			reHashing(booleanCheck, tableSize <<= 1);
		}
		return true;
	}

	/**
	 * returns The current capacity (number of cells) of the table.
	 * 
	 */
	public int capacity() {
		return tableSize;
	}

	/**
	 * Look for a specified value in the set. returns true if searchVal is in
	 * the table, else returns false.
	 */
	@Override
	public boolean contains(String searchVal) {
		hashValue = (searchVal.hashCode()) & (tableSize - 1);
		if (hashTable.get(hashValue).contains(searchVal)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Remove the input element from the set. returns true if toDelete is found
	 * and deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		boolean booleanCheck = false;
		hashValue = (toDelete.hashCode()) & (tableSize - 1);
		// if the value is not contained in the table than return false because
		// it can't be deleted
		if (hashTable.get(hashValue).contains(toDelete) == false) {
			return false;
		} else {
			// else removing the value from the matching linked list and
			// decreasing the element counter by 1.
			hashTable.get(hashValue).remove(toDelete);
			elementCounter--;
			// checks if its necessary to divide the table's size and if does
			// than send to reHashing method.
			if ((float) size() / capacity() < lowerLoadFactor) {
				booleanCheck = false;
				reHashing(booleanCheck, tableSize >>= 1);
			}
			return true;
		}
	}

	/**
	 * The number of elements currently in the set
	 */
	@Override
	public int size() {
		return elementCounter;
	}

	/**
	 * This method gets a new size for the hash table and rehash it as this new
	 * size.
	 * 
	 * @param booParameter
	 *            - true for double size, false for divide.
	 * @param newSize
	 *            - the size that the new table size should be
	 */
	public void reHashing(boolean booParameter, int newSize) {
		elementCounter = 0;
		int oldLength;
		// new temp hash table
		ArrayList<LinkedList<String>> newHashTable = hashTable;
		// Initializing the hash table and adding to it empty linked list as
		// requested in the newSize parameter.
		hashTable = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < newSize; i++) {
			hashTable.add(new LinkedList<String>());
		}
		tableSize = newSize;
		// if got boolean parameter as true then we doubled the size and the
		// old length is half of the new size, else its double.
		if (booParameter) {
			oldLength = tableSize / 2;
		} else {
			oldLength = tableSize * 2;
		}
		// filling in the table all the values and now added with the newSize
		// parameter
		for (int j = 0; j < oldLength; j++) {
			for (int h = 0; h < newHashTable.get(j).size(); h++) {
				if (newHashTable.get(j).get(h) != null) {
					this.add(newHashTable.get(j).get(h));
				}
			}
		}

	}
}
