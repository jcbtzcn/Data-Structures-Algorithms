/**
 * This class constructs nodes of tree.
 * @author CS1027 Assignment 4 Yakup Tezcan
 */
public class NaryTreeNode<T> {
	// data in the node
	private T data;
	// number of children a node has
	private int numChildren;
	// constructs an array of children of a node
	private NaryTreeNode<T>[] children;
	/**
	 * Class has only one constructor which takes one parameter.
	 * @param dataItem. Takes a data item and makes it a tree node.
	 */
	public NaryTreeNode(T dataItem) {
		// initiliazing data with the parameter.
		data=dataItem;
		// array of children null.
		children=null;
		//number of children in a node is 0.
		numChildren=0;
	}
	/**
	 * This method adds a node to the tree.
	 * @param newItem. New item(node) to add to the tree.
	 */
	public void addChild(NaryTreeNode<T> newItem) {
		// gives size of tree if it is null at the beginning.
		if (children==null) {
			children = (NaryTreeNode<T>[]) (new NaryTreeNode[3]);
		}
		// expands capacity if number of children in the tree is equal to length of array.
		if (numChildren==children.length) {
			expandCapacity();
		}
		//next available slot in the children array
		children[numChildren]=newItem;
		// increments number of children after adding
		numChildren++;
	}
	/**
	 * expand capacity method for the tree.
	 */
	public void expandCapacity() {
		// constructs a new larger array.
		NaryTreeNode<T>[] newList = (NaryTreeNode<T>[])(new NaryTreeNode[children.length+3]);
		// adding the elements in children array to the new larger array.
		for (int i=0;i<children.length;i++) {
			newList[i]=children[i];
		}
		children=newList;
	}
	/**
	 * @return number of children a node has.
	 */
	public int getNumChildren() {
		return numChildren;
	}
	/**
	 * @param takes an integer for index.
	 * @return returns a child of a parent node.
	 */
	public NaryTreeNode<T> getChild(int num) {
		return children[num];
	}
	/**
	 * @return data of a node.
	 */
	public T getData() {
		return data;
	}
	/**
	 * @return sequence of datas in the tree.
	 */
	public String toString() {
		// constructs a text and adds the data in the tree.
		String text="Node containing ";
		for(int i=0;i<children.length;i++) {
			text+=children[i];
		}
		// returns the text.
		return text;
	}
}