import java.util.ArrayList;
import java.util.LinkedList;

public abstract class SimpleHashSet implements SimpleSet {
	// default values for the table size and upper and lower factors.
	protected int tableSize = 16;
	protected int hashValue;
	protected float upperLoadFactor = (float) 0.75;
	protected float lowerLoadFactor = (float) 0.25;

	@Override
	public abstract boolean add(String newValue);

	@Override
	public abstract boolean contains(String searchVal);

	@Override
	public abstract boolean delete(String toDelete);

	@Override
	public abstract int size();

	public abstract int capacity();

}
