/**
 * This class constructs a binary search tree.
 * @author Yakup Tezcan CS2210B Assignment 4
 */
public class BinarySearchTree implements BinarySearchTreeADT {
	//root of the tree
	BinaryNode root;
	//Constructor constructs a binary search tree with a leaf node.
	public BinarySearchTree() {
		//Initializes the root node.
		root = new BinaryNode();
	}
	//Get method to get the current Pixel data.
	public Pixel get(BinaryNode r, Location key ) {
		//Access to the node that stores the current data.
		BinaryNode node = getNode(r, key);
		//if node is leaf, then this method returns null, otherwise returns the data that stored by the node.
		if(node.isLeaf())
			return null;
		else
			return node.getData();
	}
	//This method find the current node with the given key. If the node is leaf which means not storing any data, then it returns a leaf node.
	private BinaryNode getNode(BinaryNode r, Location key) {
		//returns r, if r is leaf.
		if (r.isLeaf()) {
			return r;
		}
		else {
			//base case for recursive method. If r stores the key, then it returns r.
			if (r.getData().getLocation().compareTo(key) == 0) {
				return r;
			}
			//if the data stored by r is greater than key, recursive method goes to left side of the node(r).
			else if(r.getData().getLocation().compareTo(key) > 0)
				return getNode(r.getLeft(), key);
			//else it goes to the right side of the node(r).
			else
				return getNode(r.getRight(), key);
		}
	}
	//This method inserts a new node, if it not already in the tree. 
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		//Access the node with the help of getNode.
		BinaryNode newNode = getNode(r, data.getLocation());
		//If newNode is not leaf, it means the tree has a node with the given data. So it throws an exception.
		if(!newNode.isLeaf()) {
			throw new DuplicatedKeyException();
		}
		else {
			//if the tree size is 0, we set root data with the given data.
			if(root.getData() == null) {
				//Create new leaf children for the root.
				BinaryNode leftC = new BinaryNode();
				BinaryNode rightC = new BinaryNode();
				//Set data, left and right child for the root.
				root.setData(data);
				root.setLeft(leftC);
				root.setRight(rightC);
				root.setParent(null);
				//Set parent of the children as root.
				leftC.setParent(root);
				rightC.setParent(root);
			}else {
				//Create a new node with leaf children.
				BinaryNode leftChild = new BinaryNode();
				BinaryNode rightChild = new BinaryNode();
				//Set data, left and right child for the new node.
				newNode.setData(data);
				newNode.setLeft(leftChild);
				newNode.setRight(rightChild);
				//Set parent of the children as the new node.
				leftChild.setParent(newNode);
				rightChild.setParent(newNode);
			}
		}
	}
	//This method removes a node from a binary search tree. It throws an exception, if the node with the given key is not in the tree.
	public void remove(BinaryNode r, Location key) throws InexistentKeyException{
		//Access node with the help of getNode.
		BinaryNode deleteNode = getNode(r, key);
		//If deleteNode returns a leaf node, it means the key is not stored in the tree.
		if(deleteNode.isLeaf())
			throw new InexistentKeyException();
		else { 
			//if the node is not an internal node.
			if(deleteNode.getLeft().isLeaf() && deleteNode.getRight().isLeaf()) {
				//Removing the node by setting the data, left and child null.
				deleteNode.setData(null);
				deleteNode.setLeft(null);
				deleteNode.setRight(null);
		    }
			//if the node's right child is not leaf.
		    else if(deleteNode.getLeft().isLeaf() && !deleteNode.getRight().isLeaf()) {
		    	BinaryNode parent = deleteNode.getParent();
			    BinaryNode child = deleteNode.getRight();
			    //if the deleteNode is root, it sets the child of the deleteNode as root.
			    if(deleteNode == root) {
			        root.setData(child.getData());		
			    //else it sets the right child of deleteNode as the right child of the parent node of the deleteNode.
			    //Also, it sets the parent of the child as the parent of the deleteNode. So the links to the deleteNode are gone.
			    }else {
			    	parent.setRight(child);
			    	child.setParent(parent);
			    }
			//if the node's left child is not leaf.
		    }else if(deleteNode.getRight().isLeaf() && !deleteNode.getLeft().isLeaf()) {
		    	BinaryNode parent = deleteNode.getParent();
			    BinaryNode child = deleteNode.getLeft();
			  //if the deleteNode is root, it sets the child of the deleteNode as root.
			    if(deleteNode == root) {
			    	root.setData(child.getData());
			    //else it sets the left child of deleteNode as the left child of the parent node of the deleteNode.
			    //Also, it sets the parent of the child as the parent of the deleteNode. So the links to the deleteNode are gone.
			    }else {
			    	parent.setLeft(child);
			    	child.setParent(parent);
			    }
		   }
		    else {
		    	//if the deleteNode has two children that are not leaf.
		    	//Gets the smallestNode after the deleteNode.
			    BinaryNode s = smallestNode(deleteNode.getRight());
			    //deleteNode's data is swapped by the smallest key after the deleteNode. 
			    deleteNode.setData(s.getData());
			    //If the right child of the smallest node is leaf, then we can remove the s.
			    if(s.getRight().isLeaf()) {
			    	s.setData(null);
			    	s.setLeft(null);
			    	s.setRight(null);
			    //else we set the left node of the parent node of the s with right child of s.
			    }else
			    	s.getParent().setLeft(s.getRight());
		    }
		}
	}
	//Successor method for the smallest key larger than the given key.
	public Pixel successor(BinaryNode r, Location key) {
		//We get pixel from get method to check if the node with the given key is in the tree.
		Pixel pix = get(r, key);
		//if root is leaf, it returns null.
		if(root.isLeaf())
			return null;
		//if there is a node in the tree with the given key.
		if(pix != null) {
			//We get the node with the given key.
			BinaryNode node = getNode(r, key);
			//If the right child of the node is not leaf, then we simply get the smallest node after the right child of the node.
			if(!node.getRight().isLeaf())
				return smallest(node.getRight());
			//else we check parents of the node. We can say that, we climb to the tree to reach the smallest key larger than the given one.
			else {
				BinaryNode parent = node.getParent();
				//We go up until we reach the root of the tree or node is the right child of its parent node.
				while(node != root && node == parent.getRight()) {
					node = parent;
					parent = node.getParent();
				}
				//if the node is root, then there is no successor for the node with the given key.
				if(node == root)
					return null;
				//else the successor is the parent of the node.
				else
					return parent.getData();
			}
		}else {
			//if the key is not stored in any node of the tree.
			//We initialize a variable called tmpSucc to store the current successor.
			Pixel tmpSucc=null;
			//If the given key is larger than the root and the nodes, then we keep going to the left child of the root or the nodes.
			if(r.getData().getLocation().compareTo(key) > 0) {
				//Successor is the data of the current node.
				tmpSucc = r.getData();
				successor(r.getLeft(), key);
		    }
			return tmpSucc;
		}
		
	}
	//Predecessor node to get the 
	public Pixel predecessor(BinaryNode r, Location key) {
		//We get pixel from get method to check if the node with the given key is in the tree.
		Pixel pix = get(r, key);
		//if root is leaf, it returns null.
		if(root.isLeaf())
			return null;
		//if there is a node in the tree with the given key
		if (pix != null){
			//We get the node with the given key.
			BinaryNode node = getNode(r, key);
			//If the left child of the node is not leaf, then we simply get the largest node after the left child of the node.
			if(!node.isLeaf() && !node.getLeft().isLeaf())
				return largest(node.getLeft());
			//else we check parents of the node. We can say that, we climb to the tree to reach the largest key smaller than the given one.
			else{
				//We go up until we reach the root of the tree or node is the left child of its parent node.
				while(node.getParent()!=null && node == node.getParent().getLeft()) {
					node = node.getParent();
				}
				//if the node is root, then there is no successor for the node with the given key.
				if(node == root)
					return null;
				//else the successor is the parent of the node.
				else
					return node.getParent().getData();
			}
		}else {
			//if the key is not stored in any node of the tree.
			//We initialize a variable called tmpSucc to store the current successor
			Pixel tmpSucc=null;
			//If the given key is larger than the root and the nodes, then we keep going to the left child of the root or the nodes.
			if(r.getData().getLocation().compareTo(key) < 0) {
				//Successor is the data of the current node.
				tmpSucc = r.getData();
				successor(r.getLeft(), key);
		    }
			return tmpSucc;
		}
	}
	//Returns the data of the smallest node after the given node.
	public Pixel smallest(BinaryNode r) throws EmptyTreeException{
		//Gets the smallest node.
		BinaryNode smallest = smallestNode(r);
		//If r is leaf, then it throws an empty tree exception.
		if(r.isLeaf())
			throw new EmptyTreeException();
		//else returns the data of the smallest node.
		else{
			return smallest.getData();
		}
	}
	//This method gets the smallest node after the given node. Throws an exception, if the given node is a leaf.
	private BinaryNode smallestNode(BinaryNode r) throws EmptyTreeException{
		if(r.isLeaf())
			throw new EmptyTreeException();
		else{
			//We go up until, r has a left internal child. When it does not have the while loop stops and there we have the smallest node after the given node.
			BinaryNode p = r;
			while(!p.getLeft().isLeaf()) {
				p = p.getLeft();
			}
			return p;
		}
	}
	//This method gets the largets node after the given node. Throws an exception, if the given node is a leaf.
	public Pixel largest(BinaryNode r) throws EmptyTreeException{
		if(r.isLeaf())
			throw new EmptyTreeException();
		else {
			//We go up until, r has a right internal child. When it does not have the while loop stops and there we have the largest node after the given node.
			BinaryNode p = r;
			while(!p.getRight().isLeaf()) {
				p = p.getRight();
			}
			return p.getData();
		}
	}
	//Access to the root of the tree.
	public BinaryNode getRoot() {
		return root;
	}
}