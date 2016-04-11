package oop.ex5.data_structures;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * This class is the class that implements all the methods for an Avl tree.
 * This class extends from Bst - binary search tree since Avl tree is a kind 
 * of Bst and implements the method as required in an Avl tree.
 * 
 * @author yinnonbar
 *
 */
public class AvlTree extends Bst {
	// Counter elements.
	protected int elementsCounter = 0; 
	
	/**
	 * A default constructor.
	 */
	public AvlTree() {
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
	public AvlTree(int[] data) {
		for (int i = 0; i < data.length; i++) {
			this.add(data[i]);
		}
		Collections.sort(numsList);
	}
	
	/**
	 * A copy constructor - a constructor that builds the tree a copy of an
	 * existing tree.
	 * @param tree - an avl tree.
	 */
	public AvlTree(AvlTree tree) {
		AvlTree newTree;
		// Initializing a new ints array in the size of the tree 
		int[] treeToCopy = new int[tree.getArrayList().size()];
		// copying all the data in the tree's ArrayList and then using the 
		//counstructor to put all the data in the list to the new tree.
		for (int i = 0; i < tree.getArrayList().size(); i++) {
			treeToCopy[i] = tree.getArrayList().get(i);
		}
		newTree = new AvlTree(treeToCopy);
	}

	/**
	 * Add anew node with key newValue into the tree.
	 * 
	 * @param newValue
	 *            new value to add to the tree
	 * @return false iff newValue already exist in the tree.
	 */
	@Override
	public boolean add(int newValue) {
		// pointing to the root.
		Node node = this.root;
		// if node is null (first time the root) than adding the node to the 
		// list as the root increasing the counter and adding the element to 
		// the ArrayList and returns true.
		if (node == null){
			treeNode newNode = new treeNode(newValue, null);
			this.root = newNode;
			this.numsList.add(newValue);
			elementsCounter++;
			return true;
		}else {
			// if it's not null then while the current node is not a leaf
			// there are 3 cases first if the value is bigger than the data
			// in the current node and checks if the right child of the node
			// is null means we have to add the value to the list as the right 
			// child, increasing by one the counter adding to the ArrayList
			// and checks for violations.
			while (!node.isLeaf()){
				if (newValue > node.getData()){
					if (node.getRightChild() == null){
					elementsCounter++;
					this.numsList.add(newValue);
					treeNode rightNode = new treeNode(newValue, node);
					node.setRightChild(rightNode);
					checkViolation(rightNode);
					return true;
				}
				node = node.getRightChild();
				}
				// symmetrical case for new value smaller than the data in the 
				// node. 
				else if (newValue < node.getData()){
					if (node.getLeftChild() == null){
						this.numsList.add(newValue);
						elementsCounter++;
						treeNode leftNode = new treeNode(newValue, node);
						node.setLeftChild(leftNode);
						checkViolation(leftNode);
						return true;
					}
					node = node.getLeftChild();
				}
				// if the value already exist in the tree returns false.
				else if (newValue == node.getData()){
					return false;
				}
		}
		
		// if the value is as the data in the node returns false.
		if (newValue == node.getData()){
			return false;
		}
		// if new value is bigger than the data in the node again add it as
		// right child and checks violations, same for symmetrical case and 
		// add to the list increasing counter by one and return true.
		else if (newValue > node.getData()){
			treeNode rightNode = new treeNode(newValue, node);
			node.setRightChild(rightNode);
			checkViolation(rightNode);
		} else {
			treeNode leftNode = new treeNode(newValue, node);
			node.setLeftChild(leftNode);
			checkViolation(leftNode);
		}
		this.numsList.add(newValue);
		elementsCounter++;
		return true;
		}
	}


	@Override
	/**
	 * Does tree contains a given input value
	 * @param searchVal
	 *            val value to search for
	 * @return if val is found in the tree, return the depth of its node 
	 * ( where 0 is the root). otherwise returns -1.
	 */
	public int contains(int searchVal) {
		int depth = 0;
		Node node = this.root;
		// if nothing on the root returns -1
		if (node == null){
			return -1;
		}
		// a while loop running while node is not null and checks 3 cases :
		// if the searchVal is equal to the current node's data return the 
		//current depth
		while (node != null) {
			if (searchVal == node.getData()){
				return depth;
			}
			// if the search val is bigger than the current node's data 
			// than going to the right child and increase the depth by one
			// since were going 1 "floor" down. 
			// if we met our searchVal then we return it.
			else if (searchVal > node.getData()) {
				if (node.getRightChild() != null && node.getRightChild().
						getData() == searchVal ){
					depth++;
					return depth;
				} else {
					depth++;
					node = node.getRightChild();
				}
			// same as above but just for case that searchVal is smaller than
			// the data in the node than going left.
			} else if (searchVal < node.getData()) {
				if (node.getLeftChild() != null && node.getLeftChild().
						getData() == searchVal){
					depth++;
					return depth;
				} else {
					depth++;
					node = node.getLeftChild();
				}
			} 
		}
		// if not found
		return -1;
	}
	
	@Override
	/**
	 * A method that removes a node from the tree if it's exist.
	 * @param toDelete
	 *            value to delete
	 * @return true iff toDelete is found and deleted.
	 */
	public boolean delete(int toDelete) {
		// three nodes for making the rotations
		Node child,temp,savedNode;
		// using searchNode method to checks if the toDelete is in the tree 
		// if not returns false.
		Node toDeleteNode = this.searchNode(this.root, toDelete);
		if (toDeleteNode == null){
			return false;
		}
		// if the delete node has no left child or right child then makes
		// child node as toDeleteNode.
		if (toDeleteNode.getLeftChild() == null || toDeleteNode.getRightChild()
				== null){
			child = toDeleteNode;
		}
		// else setting child as the successor of toDeleteNode means the number 
		// one bigger than toDeleteNode so we can make the rotation since its 
		// lower.
		else {
			child = this.successor(toDeleteNode);
		}
		savedNode = child.getParent();
		// if the left child of child is not null then save in temp that child,
		// else temp is the right child.
		if (child.getLeftChild() != null){
			temp = child.getLeftChild();
		}
		else {
			temp = child.getRightChild();
		}
		// if temp is not null means there was a child then his parent will be 
		// child parent
		if (temp != null){
			temp.setParent(child.getParent());
		}
		// if child parent is null means the root is temp since he has no 
		// parent, but if child is the children parent left child so we set the
		// children parent left child as temp
		if (child.getParent() == null){
			this.root = temp;
		}
		else if (child == child.getParent().getLeftChild()){
			child.getParent().setLeftChild(temp);
		}
		// if nothing of this happened it means that child parent should have
		// temp as right child.
		else {
			child.getParent().setRightChild(temp);
		}
		// if child is not the node we wanted to delete than we set on to 
		// delete data as child's data.
		if (child != toDeleteNode){
			toDeleteNode.setData(child.getData());
		}
		elementsCounter--;
		// if saved node is not null than we have to check for violations
		if (savedNode != null){
			checkViolation(savedNode);
		}
		return true;
	}	
		
			

	/**
	 * 
	 * @return number of nodes in the tree.
	 */
	@Override
	public int size() {
		return elementsCounter;
	}

	/**
	 * Returns iterator to the Avl tree. the returned iterator can pass over 
	 * the tree nodes in ascending order.
	 */
	@Override
	public Iterator<Integer> iterator() {
		// creating a new show of the Avl iterator starting on the root.
		Iterator<Integer> iter = new Avliterator(this.root);
		return iter;
	}

	/**
	 * A getter for the ArrayList of the tree.
	 */
	@Override
	public ArrayList<Integer> getArrayList() {
		return this.numsList;
	}

	
	/**
	 * rotate Left method -  the left rotation, which results in a movement 
	 * in a counter-clockwise direction.  
	 * @param node - a given node to rotate.
	 */
	private void rotateLeft(Node node) {
		Node temp = node.getRightChild();
		// if the node is the root of the tree than setting temp as null 
		// because it has no parent, else setting temp parent as node's parent.
		if (node == this.root) {
			temp.setParent(null);
		} else {
			temp.setParent(node.getParent());
		}
		// setting nodes right child as temp's left child
		node.setRightChild(temp.getLeftChild());
		// if node's right child is not null than setting nodes right child
		// parent as node
		if (node.getRightChild() != null) {
			node.getRightChild().setParent(node);
		}
		temp.setLeftChild(node);
		node.setParent(temp);
		// if temp's parent is not null 
		if (temp.getParent() != null) {
			// if temp parent right child is node than setting temp parent as
			// temp right child.
			if (temp.getParent().getRightChild() == node) {
				temp.getParent().setRightChild(temp);
			// if temp parent left child is node than setting temp parent now 
			// as the left child.
			} else if (temp.getParent().getLeftChild() == node) {
				temp.getParent().setLeftChild(temp);
			}
		// if not got into the if means the temp parent is not null than the
		// root of the tree is temp.
		} else {
			this.root = temp;
		}
	}

	/**
	 *  rotate Right method -results in a rotation of the tree in the 
	 *  clockwise direction. 
	 * @param node
	 */
	private void rotateRight(Node node) {
		// the symmetrical method for rotate left.
		Node temp = node.getLeftChild();
		temp.setParent(node.getParent());
		node.setLeftChild(temp.getRightChild());
		if (node.getLeftChild() != null) {
			node.getLeftChild().setParent(node);
		}
		temp.setRightChild(node);
		node.setParent(temp);
		if (temp.getParent() != null) {
			if (temp.getParent().getRightChild() == node) {
				temp.getParent().setRightChild(temp);
			} else if (temp.getParent().getLeftChild() == node) {
				temp.getParent().setLeftChild(temp);
			}
		} else {
			this.root = temp;
		}
	}
	/**
	 * this method is double rotate right left.
	 * @param node
	 */
	private void doubleRotateRightLeft (Node node){
		// rotate first right the node right children and then rotate node to
		// the left.
		rotateRight(node.getRightChild());
		rotateLeft(node);
	}
	
	/**
	 * this method is double rotate left right.
	 * @param node
	 */
	private void doubleRotateLeftRight (Node node){
		// the symmetrical method for double rotate right left.
		rotateLeft(node.getLeftChild());
		rotateRight(node);
		
	}
	


	/**
	 * checks violation method is a method that gets a node and checks if 
	 * there's no violations in it balance, if do than re-balance it.
	 */
	@Override
	public void checkViolation(Node node) {
		// if the node is leaf than setting it as his parent
		if (node.isLeaf()){
			node = node.getParent();
		}
		// a while loop that runs while the node is not null and the height 
		// difference between the children is smaller than 2 in absolute value.
		// if it is keep climbing to the parent.
		while ((node != null) && ((Math.abs(getHeight(node.getLeftChild()) -
				getHeight(node.getRightChild())) <= 1))){
			node = node.getParent();
		}
		
		if (node == null){
			return;
		}
		// if there's a violation to the left checks if there's a need for
		// a double rotation if node right child is null and node left child
		// left child is null, else makes a simple right rotation.
		if (getHeight(node.getLeftChild())-getHeight(node.getRightChild()) == 
				2){
			if (node.getRightChild() == null){
				if (node.getLeftChild().getLeftChild() == null){
					doubleRotateLeftRight(node);
				}
				else {
					rotateRight(node);
				}
			}
			// if the height of node left left child is bigger or equal to 
			// the height of left children right child than rotate right else
			// a double left right rotation is needed on node.
			else if(getHeight(node.getLeftChild().getLeftChild()) >= 
					getHeight(node.getLeftChild().getRightChild())){
				rotateRight(node);
			}
			else {
				doubleRotateLeftRight(node);
			}
		}
		else {
			// if node left child is null and node right right child is null
			// than double rotate right than left, else only left rotate.
			if (node.getLeftChild() == null){
				if (node.getRightChild().getRightChild() == null){
					doubleRotateRightLeft(node);
				}
				else {
					rotateLeft(node);
				}
			}
			// if its not null and the height of right child right child
			//  is bigger or equal to the height of the right child left child
			// than rotate left, else double rotate right and left.
			else if (getHeight(node.getRightChild().getRightChild()) >= 
					getHeight(node.getRightChild().getLeftChild())){
				rotateLeft(node);
			}
			else {
				doubleRotateRightLeft(node);
			}
		}
		// calling CheckViolation recursively on node to check it now so 
		// there's no other violations.
		checkViolation(node);
		
	}

	/**
	 * A method that returns the maximal node in a subtree.
	 * I implemented this method recursively and minimal the symmetric method
	 * with a loop but it's the same just other implementation.
	 */
	@Override
	public Node maximal(Node node) {
		// base case - if the right child is null than the node is the maximal
		// node in the subtree
		if (node.getRightChild() == null)
			return node;
		// recursive call to node right child.
		return maximal(node.getRightChild());
	}
	
	/**
	 *  A method that returns the minimal node in a subtree.
	 *  Here I implemented it with a loop but it's the same as recursive way.
	 */
	public Node minimal(Node node) {
		// if the node is null return null. 
		if (node == null){
			return null;
		}
		// if the left child is null return the node (symmetric to the base 
		// case).
		else if (node.getLeftChild() == null)
			return node;
		// a while loop running till the null and going left
		while (node.getLeftChild() != null){
			node = node.getLeftChild();
		}
		return node;
	}
	
	
	@Override
	/**
	 * A method that return the successor (the bigger next value) to a
	 * given node.
	 * 
	 * @param node
	 *            - a given node.
	 * @return the successor.
	 */
	public Node successor(Node node) {
		// if node right child is not null than call minimal function on the
		// right subtree so it will return the lowest value that is bigger
		// than the given node's data.
		if (node.getRightChild() != null){
			return this.minimal(node.getRightChild());
		}
		Node parent = node.getParent();
		// while nodes parent is not null and node is equal to parent right 
		// child than node is the parent and parent is the parent's parent
		// (running up in the "floors".
		while ((parent != null) && (node == parent.getRightChild())){
			node = parent;
			parent = node.getParent();
		}
		return parent;
	}
	@Override
	/**
	 * A method that returns the height of a node.
	 * 
	 * @param node
	 *            - a given node.
	 * @return - the height of the node. if node is null returns -1.
	 */
	public int getHeight(Node node) {
		// if node is null means the height is -1 by definition, else return
		// 1 + the max between the recursive calls of left child and right 
		// child.
		if (node == null){
			return -1;
		}else {
			return 1 +Math.max(getHeight(node.getLeftChild()), 
					getHeight(node.getRightChild()));
		}
			
	}
	@Override
	/**
	 * A method that returns the node that his data matches to the
	 * given val.
	 * 
	 * @param node
	 *            - the given subtree to look in.
	 * @param searchValue
	 *            - the search val.
	 * @return the node that his data matches to the given val. if not found
	 *         returns null.
	 */
	public Node searchNode(Node node, int searchValue) {
		// if node is null or the data is equal to the search value returns
		// the node.
		if ((node == null) || (node.getData() == searchValue)){
			return node;
		// if the data is bigger than the data in node than calling it 
		// recursive to the right node and search value and symmetrically to 
		// the left.
		} if (node.getData() < searchValue){
			return searchNode(node.getRightChild(), searchValue);
			
		}
		return searchNode(node.getLeftChild(), searchValue);
		
	}
	
	/**
	 * This methods calculates the minimum number of nodes in an AVL tree of 
	 * height h. 
	 * this is a static method since it needs no specific show of an AVL tree.
	 * @param h - a given height
	 * @return the minimal nodes that can be in an AVL tree in the height
	 * of h.
	 */
	public static int findMinNodes(int h){
		// base cases if h is -1 than there no nodes by definition, if the h
		//  is 0 than there's only the root by definition.
		if (h == -1){
			return 0;	
		} else if (h==0){
			return 1;
		}
		// recursive call to the method with h-1 + h-2 +1
		else return findMinNodes(h-1) + findMinNodes(h-2) +1;
		
	}
	/**
	 * An inner class that implements the iterator.
	 * @author yinnonbar
	 *
	 */
	private class Avliterator implements Iterator<Integer>{

		private Node node;
		
		private Avliterator(Node node){
			node = minimal(node);
			
		}
		@Override
		/**
		 * implementing the method hasNext, that checks if there's a value
		 * we can iterate.
		 */
		public boolean hasNext() {
			// if node is null return false because there's no node to 
			// iterate, else return true.
			if (node == null){
				return false;		
			} else {
				return true;	
			}
		}

		@Override
		/**
		 * Implementing the next method that iterate the next node.
		 */
		public Integer next() throws NoSuchElementException {
			// if the successor of the current node is null and we asked to
			// iterate it than throw an exception that there's no such 
			// element.
			if (successor(node) == null) {
				throw new NoSuchElementException(); 
			}
			// moving node to its successor and returning the data in the node
			// (the current one not the successor.
			int returnedData = node.getData();
			node = successor(node);
			return returnedData;
		}

		@Override
		/**
		 * remove method - have to implement it on the API of iterator but
		 * there's no need in it in this implementation so only throwing an
		 * exception in case of user trying to use it.
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}
		
	}
}