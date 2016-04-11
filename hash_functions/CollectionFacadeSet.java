import java.util.Collection;

public class CollectionFacadeSet implements SimpleSet {
	private Collection<String> collection;

	/**
	 * Creates a new facade wrapping the specified collection.
	 * 
	 * @param collection
	 *            - The Collection to wrap.
	 */
	CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
		this.collection = collection;
	}

	/**
	 * Add a specified element to the set.
	 * Parameters: newValue - New value to
	 * add to the set Returns False iff newValue already exists in the set.
	 */
	public boolean add(java.lang.String newValue) {
		return collection.add(newValue);
	}

	/**
	 * Look for a specified value in the set. 
	 * searchVal - Value to search for
	 * Returns True iff searchVal is found in the set.
	 */
	public boolean contains(java.lang.String searchVal) {
		return collection.contains(searchVal);
	}

	/**
	 * Remove the input element from the set. 
	 * toDelete - Value to delete True
	 * iff toDelete is found and deleted
	 */
	public boolean delete(java.lang.String toDelete) {
		return collection.remove(toDelete);
	}

	/**
	 * Returns The number of elements currently in the set.
	 */
	public int size() {
		return collection.size();
	}

}
