/**
 * This class constructs a tree by using nodes of nary tree.
 * @author CS1027 Assignment 4 Yakup Tezcan
 */
// imports Iterator
import java.util.Iterator;
public class LinkedNaryTree<T> {
	// root of the tree.
	private NaryTreeNode<T> root;
	// first constructor method for the class.
	public LinkedNaryTree() {
		root=null;
	}
	// second constructor for the class.
	public LinkedNaryTree(NaryTreeNode<T> newRoot) {
		// initiliazes root with the parameter.
		root=newRoot;
	}
	/**
	 * @param parent node.
	 * @param child node.
	 */
	public void addNode(NaryTreeNode<T> parent, NaryTreeNode<T> child) {
		// adds a child node to the parent node.
		parent.addChild(child);
	}
	/**
	 * @return root of a tree.
	 */
	public NaryTreeNode<T> getRoot() {
		return root;
	}
	/**
	 * @return the data of root element.
	 */
	public T getRootElement() {
		return root.getData();
	}
	/**
	 * @return true if tree is empty.
	 */
	public boolean isEmpty(){
		return root==null;
	}
	/**
	 * @param takes a tree as a parameter.
	 * @return size of a tree.
	 */
	public int size(NaryTreeNode<T> tree) {         // not sure
		int numOfElements=0;
		for(int i=0;i<tree.getNumChildren();i++) {
			// if a child of a parent node is not null then we go into that child and check if they have children or not and incrementing the number of elements
			if(tree.getChild(i)!=null) {
				numOfElements+=size(tree.getChild(i));
			}
		}
		// we are adding 1 because of the root element.
		return numOfElements+1;
	}
	/**
	 * This class constructs a preorder traverse.
	 * @param node. Takes the first node in the array.
	 * @param arrayList. Takes an array of unorderedlist to store the nodes of the tree.
	 */
	public void preorder(NaryTreeNode<T> node, ArrayUnorderedList<T> arrayList) {
		// if array is empty, we are adding the data item of the root of the tree to the front.
		if(arrayList.isEmpty()) arrayList.addToFront(getRootElement());
		// after adding the root, we are iterating through the elements in the tree.
		for(int i=0;i<node.getNumChildren();i++) {
			// we find here the children of every node(if there is).
			if(node.getChild(i)!=null) {
				// adding the new nodes to the end of the list.
				arrayList.addToRear(node.getChild(i).getData());;
				// using recursion to reach the another node in the tree.
				preorder(node.getChild(i), arrayList);
			}
		}
	}
	/**
	 * @return iterator of the preorder list.
	 */
	public Iterator<T> iteratorPreorder() {
		// new list.
		ArrayUnorderedList<T> newList = new ArrayUnorderedList<T>();  // we will check this
		preorder(root, newList);
		// constructing a new list by using the list that is built in preorder.
		Iterator iterator = newList.iterator();
		return iterator;
	}
	/**
	 * toString method for the class.
	 * @return sequence of the data items in the list.
	 */
	public String toString() {
		String text="";
		// if the tree is empty.
		if (root==null) {
			return "Tree is empty.";
		}
		// using iterator methods while calling the data items in the list.
		while (iteratorPreorder().hasNext()) {
			text+=iteratorPreorder().next() + "\n";
		}
		// returns the whole sequence.
		return text;
	}
}