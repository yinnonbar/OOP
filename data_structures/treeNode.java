package oop.ex5.data_structures;

/**
 * A class extending Node - the abstract class that defines how a node in a
 * binary search tree should be and defining the methods such a node has.
 * 
 * @author yinnonbar
 * 
 */
public class treeNode extends Node {

	/**
	 * Constructor super from Node.
	 * 
	 * @param data
	 */
	public treeNode(int data) {
		super(data);
	}

	/**
	 * Constructor super from Node.
	 * 
	 * @param data
	 * @param parent
	 */
	public treeNode(int data, Node parent) {
		super(data, parent);

	}

	/**
	 * Constructor super from Node.
	 * 
	 * @param node
	 */
	public treeNode(treeNode node) {
		super(node);
	}

	@Override
	/**
	 * Implementing the abstract method from Node class. 
	 */
	public int getData() {
		return this.data;
	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public void setData(int data) {
		this.data = data;

	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public Node getParent() {
		return this.parent;
	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public Node getRightChild() {
		return this.rightChild;
	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public Node getLeftChild() {
		return this.leftChild;
	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public boolean isLeaf() {
		if (this.getLeftChild() == null && this.getRightChild() == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public void setRightChild(Node node) {
		this.rightChild = node;

	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public void setLeftChild(Node node) {
		this.leftChild = node;

	}

	@Override
	/**
	 * Implementing the abstract method from Node class.
	 */
	public void setParent(Node node) {
		this.parent = node;

	}

}
