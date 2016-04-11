package oop.ex5.data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class Bst - Binary search tree is an abstract class defining how a
 * binary search tree should be and which methods it must have.
 * 
 * @author yinnonbar
 * 
 */
public abstract class Bst {
	// An ArrayList of integers which will contains all the values in the
	// Bst in an ascending order.
	protected ArrayList<Integer> numsList;
	protected Node root;

	/**
	 * A default constructor.
	 */
	public Bst() {
		this.root = null;
		numsList = new ArrayList<Integer>();
	}

	/**
	 * A data constructor. a constructor that builds the tree by adding the
	 * elements in the input array one by one. if the same value appears twice
	 * or more in the list it's ignored. Also adding the elements to the
	 * ArrayList and sort it.
	 * 
	 * @param data
	 *            values to add to the tree.
	 */
	public Bst(int[] data) {
		for (int i = 0; i < data.length; i++) {
			numsList.add(data[i]);
			this.add(data[i]);
		}
		Collections.sort(numsList);
	}

	/**
	 * Add anew node with key newValue into the tree.
	 * 
	 * @param newValue
	 *            new value to add to the tree
	 * @return false iff newValue already exist in the tree.
	 */
	public abstract boolean add(int newValue);

	{

	}

	/**
	 * Does tree contains a given input value.
	 * 
	 * @param searchVal
	 *            val value to search for
	 * @return if val is found in the tree, return the depth of its node (where
	 *         0 is the root). otherwise returns -1.
	 */

	public abstract int contains(int searchVal);

	{

	}

	/**
	 * An abstract method that removes a node from the tree if it's exist.
	 * @param toDelete
	 *            value to delete
	 * @return true iff toDelete is found and deleted.
	 */
	public abstract boolean delete(int toDelete);

	{

	}

	/**
	 * 
	 * @return number of nodes in the tree.
	 */
	public abstract int size();

	{
	}

	/**
	 * An abstract method that gets the ArrayList of the tree.
	 * 
	 * @return
	 */
	public abstract ArrayList<Integer> getArrayList();

	{
	}

	/**
	 * An abstract method that gives the minimal node in a subtree.
	 * 
	 * @param node
	 *            - a given node
	 * @return the minimal node in the given subtree.
	 */
	public abstract Node minimal(Node node);

	{

	}

	/**
	 * An abstract method that gives the maximal node in a subtree.
	 * 
	 * @param node
	 *            - a given node
	 * @return the minimal node in the given subtree.
	 */
	public abstract Node maximal(Node node);

	{

	}

	/**
	 * An abstract method that return the successor (the bigger next value) 
	 * to a given node.
	 * 
	 * @param node
	 *            - a given node.
	 * @return the successor.
	 */
	public abstract Node successor(Node node);

	{

	}

	/**
	 * An abstract method that returns the height of a node.
	 * 
	 * @param node
	 *            - a given node.
	 * @return - the height of the node. if node is null returns -1.
	 */
	public abstract int getHeight(Node node);

	{

	}

	/**
	 * An abstract method that checks for violations in the tree and fixes it 
	 * as needed.
	 * 
	 * @param node
	 *            - a given node to check for violations.
	 */
	public abstract void checkViolation(Node node);

	{

	}

	/**
	 * 
	 * @return iterator to the Tree. the returned iterator can pass over the
	 *         tree nodes in ascending order.
	 */
	public abstract Iterator<Integer> iterator();

	{

	}

	/**
	 * An abstract method that returns the node that his data matches to the
	 * given val.
	 * 
	 * @param node
	 *            - the given subtree to look in.
	 * @param searchValue
	 *            - the search val.
	 * @return the node that his data matches to the given val. if not found
	 *         returns null.
	 */
	public abstract Node searchNode(Node node, int searchValue);

	{

	}

}
