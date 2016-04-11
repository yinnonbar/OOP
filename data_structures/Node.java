package oop.ex5.data_structures;

/**
 * An abstract class defining the design of a node in a binary search tree.
 * 
 * @author yinnonbar
 * 
 */
public abstract class Node {
	// Vars deceleration - the parameters that each node contains.
	protected boolean isLeaf;
	protected int data;
	protected Node parent;
	protected Node leftChild;
	protected Node rightChild;

	/**
	 * A constructor that gets as input a data and put it in node.
	 * 
	 * @param data
	 */
	public Node(int data) {
		this.data = data;
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.isLeaf = true;

	}

	/**
	 * A constructor that gets data and parent and creates the node with the
	 * data and the given parent for this node
	 * 
	 * @param data
	 * @param parent -node to be the parent
	 */
	public Node(int data, Node parent) {
		this.data = data;
		this.parent = parent;
		this.leftChild = null;
		this.rightChild = null;
		this.isLeaf = true;
	}

	/**
	 * A constructor that gets the parameters as given in the node.
	 * 
	 * @param node - a given node.      
	 */
	public Node(Node node) {
		this.data = node.getData();
		if (node.getParent() != null) {
			this.parent = node.getParent();
		}
		if (node.getLeftChild() != null) {
			this.leftChild = node.getLeftChild();
		}
		if (node.getRightChild() != null) {
			this.rightChild = node.getRightChild();
		}
		this.isLeaf = this.isLeaf();
	}

	/**
	 * An abstract method that gives the data of the node.
	 * 
	 * @return the data of the node.
	 */
	public abstract int getData();

	{

	}

	/**
	 * An abstract method that sets given data in a node.
	 * 
	 * @param data
	 */
	public abstract void setData(int data);

	{

	}

	/**
	 * An abstract method that gets the parent of a node.
	 * 
	 * @return the parent of a node.
	 */
	public abstract Node getParent();

	{

	}

	/**
	 * An abstract method that sets to a node a parent as the given node.
	 * 
	 * @param node - a given parent.
	 */
	public abstract void setParent(Node node);

	{

	}

	/**
	 * An abstract method that gets the right child of a node.
	 * 
	 * @return the right child.
	 */
	public abstract Node getRightChild();

	{

	}

	/**
	 * An abstract method that sets to a node the right child as given.
	 * 
	 * @param node - a given right child.        
	 */
	public abstract void setRightChild(Node node);

	{

	}

	/**
	 * An abstract method that gets the left child of a node.
	 * 
	 * @return the left child.
	 */
	public abstract Node getLeftChild();

	{

	}

	/**
	 * An abstract method that sets to a node the left child as given.
	 * 
	 * @param node
	 */
	public abstract void setLeftChild(Node node);

	{

	}

	/**
	 * An abstract method that checks if a node is a leaf (has no children).
	 * 
	 * @return true iff the node is a leaf.
	 */
	public abstract boolean isLeaf();

	{

	}

}
