/**
 * This class constructs a binary node.
 * @author Yakup Tezcan CS2210B Assignment 4
 */
public class BinaryNode {
	//left child of the node.
	private BinaryNode leftChild;
	//right child of the node.
	private BinaryNode rightChild;
	//parent of the node
	private BinaryNode parentNode;
	//data stored by the node
	private Pixel data;
	//First constructor for the class. Takes parameters; data, left child, right child ,parent and then initializes a new node.
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		data =  value;
		leftChild = left;
		rightChild = right;
		parentNode = parent;
	}
	//Second constructor. Initializes a leaf node with null data.
	public BinaryNode() {
		data = null;
	}
	//Getter method to access the parent node.
	public BinaryNode getParent() {
		return parentNode;
	}
	//Sets a new parent for the node with the given node.
	public void  setParent(BinaryNode parent) {
		parentNode = parent;
	}
	//Sets a new left child for the node with the given node.
	public void setLeft(BinaryNode left) {
		leftChild = left;
	}
	//Sets a new right child for the node with the given node.
	public void setRight(BinaryNode right) {
		rightChild = right;
	}
	//Sets a new data for the node with the given node.
	public void setData(Pixel value) {
		data = value;
	}
	//Returns true if the node is leaf, false otherwise.
	public boolean isLeaf(){
		if(data == null)
			return true;
		else {
			return false;
		}
	}
	//Getter method to access the data.
	public Pixel getData() {
		return data;
	}
	//Getter method to access the data.
	public BinaryNode getLeft() {
		return leftChild;
	}
	//Getter method to access the data.
	public BinaryNode getRight() {
		return rightChild;
	}
}