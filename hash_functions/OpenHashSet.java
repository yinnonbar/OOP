import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class OpenHashSet extends SimpleHashSet {
	private String[] hashTable;
	private String[] newHashTable;
	private boolean[] booleanArray;
	private int elementCounter = 0;

	/**
	 * A default constructor. Constructs a new, empty table with default initial
	 * capacity (16), upper load factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet() {
		hashTable = new String[tableSize];
		booleanArray = new boolean[tableSize];
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
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		hashTable = new String[tableSize];
		// an array of booleans in the size of tableSize filled with false
		// by default.
		booleanArray = new boolean[tableSize];
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one.
	 * Duplicate values should be ignored. The new table has the default values
	 * of :
	 * initial capacity (16), 
	 * upper load factor (0.75), 
	 * lower load factor (0.25).
	 * 
	 * @param data
	 *            - Values to add to the set.
	 */
	public OpenHashSet(java.lang.String[] data) {
		hashTable = new String[tableSize];
		booleanArray = new boolean[tableSize];
		for (int i = 0; i < data.length; i++) {
			add(data[i]);
		}
	}

	@Override
	/**
	 * Add a specified element to the set.
	 */
	public boolean add(String newValue) {
		int i = 0;
		int position;
		// if the value already in the list return false and do nothing.
		if (contains(newValue)) {
			return false;
		}
		hashValue = newValue.hashCode();
		// the position the value should be placed using the quadratic formula
		position = (int) (hashValue + (i + i * i) / 2) & (capacity() - 1);
		// a while loop running while there's a value in the checked position.
		// if there's a value in there and it's not null than keep doing the
		// quadratic formula.
		while (this.hashTable[position] != null) {
			i++;
			position = (int) (hashValue + (i + i * i) / 2) & (capacity() - 1);
		}
		// after the while loop ends putting the value in the place that got by
		// the quadratic formula and changing that place in the boolean array
		// to true so we can know that there's a value in that place.
		// also increasing the element counter by 1.
		hashTable[position] = newValue;
		booleanArray[position] = true;
		elementCounter++;
		// check's if it's necessary to double the size of the table and if
		// does than sending to reHashing method.
		if ((float) size() / capacity() > upperLoadFactor) {
			reHashing(tableSize <<= 1);
		}
		return true;

	}

	/**
	 * Look for a specified value in the set. returns True iff searchVal is
	 * found in the set.
	 */
	@Override
	public boolean contains(String searchVal) {
		int i = 0;
		int position;
		// a while loop running on the capacity of the table and check's if the
		// value in the position as calculated in the quadratic formula is
		// equal to the searched value than return true.
		while (i < capacity()) {
			hashValue = searchVal.hashCode();
			position = (int) (hashValue + (i + i * i) / 2) & (capacity() - 1);
			if (hashTable[position] != null
					&& hashTable[position].equals(searchVal)) {
				return true;
				// if this place in the table is null and there was no value in
				// there so return true because there's no need to keep the
				// quadratic because the value had to be there. else keep
				// counting.
			} else if (hashTable[position] == null) {
				if (booleanArray[position] == false) {
					return false;
				} else {
					i++;
				}
			}
			i++;
		}
		return false;
	}

	/**
	 * Remove the input element from the set. True iff toDelete is found and
	 * deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		int i = 0;
		int position;
		// if the element is not in the table than return false.
		if (!contains(toDelete)) {
			return false;
		}
		hashValue = toDelete.hashCode();
		position = (int) (hashValue + (i + i * i) / 2) & (capacity() - 1);
		// if the value in the position in the table is not the value that has
		// to be deleted than keep the quadratic formula check.
		if (hashTable[position] != null) {
			while (!hashTable[position].equals(toDelete) && i < tableSize) {
				i++;
				position = (int) (hashValue + (i + i * i) / 2)
						& (capacity() - 1);

			}
		}
		// now the value found and changes that value to null, means delete it
		// and change that position in the boolean array to true so we'll know
		// that a value in that position was deleted.
		hashTable[position] = null;
		booleanArray[position] = true;
		elementCounter--;
		// checks if necessary to change the table's size and if does than
		// send it to reHashing method.
		if ((float) size() / capacity() < lowerLoadFactor) {
			reHashing(tableSize >>= 1);
		}
		return true;
	}

	/**
	 * The number of elements currently in the set
	 */
	@Override
	public int size() {
		return elementCounter;
	}

	/**
	 * 
	 * @return The current capacity (number of cells) of the table.
	 */
	public int capacity() {
		return tableSize;
	}

	/**
	 * takes a new size wanted and rehashing the table with the new size.
	 * 
	 * @param newSize
	 *            - the size wanted for the table.
	 */
	public void reHashing(int newSize) {
		elementCounter = 0;
		// inserting hash table to a new temp hash table.
		newHashTable = hashTable;
		// Initializing the hash table and changing the size of it and the
		// boolean array to the new size.
		hashTable = new String[newSize];
		tableSize = newSize;
		booleanArray = new boolean[newSize];
		// making the rehash. adding each of the elements to the place it
		// has to be in the table with the new size.
		for (int i = 0; i < newHashTable.length; i++) {
			if (newHashTable[i] != null) {
				this.add(newHashTable[i]);
			}
		}
	}
}
