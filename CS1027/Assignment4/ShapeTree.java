/**
 * This class constructs a tree with some shapes that are specified in Shape class.
 * Shapes are circles, triangles, squares and stars.
 * Circles can have 1 child, triangles 3, squares 4 and stars 5.
 * Siblings' nodes cannot have same colour.
 * Parent and child node cannot have same colour.
 * @author CS1027 Assignment 4 Yakup Tezcan
 */
public class ShapeTree {
	// tree variable
	private LinkedNaryTree<Shape> tree;
	// getter method for tree.
	public LinkedNaryTree<Shape> getTree(){
		return tree;
	}
	// getter method to reach the root of the tree
	public NaryTreeNode<Shape> getRoot(){
		return tree.getRoot();
	}
	/**
	 * Adds a new shape to the tree.
	 * @param newShape. Takes a new shape as parameter.
	 */
	public void addShapeNode(Shape newShape) {
		// newShape is transformed to a node in tree.
		NaryTreeNode<Shape> newShape2 = new NaryTreeNode<Shape>(newShape);
		// if tree is null, newShape is given to the linednarytree as the parameter to construct the root of the tree.
		if(tree==null) {
			tree = new LinkedNaryTree<Shape>(newShape2);
		// after we made sure that tree is not null, we add add new shape nodes to the tree.
		}else {
			// if there is a new slot in the tree for the next node, then we add the node to the tree.
			if (addShapeNodeHelper(newShape)!=null) {                                	
				// adding new node to the tree. We give two parameters. First one is the current node then the second is the shape that we have to add the tree if there is an available slot in the tree.
				tree.addNode(addShapeNodeHelper(newShape), newShape2);
			}
		}	
	}
	/**
	 * Helper method to add new shapes to the tree.
	 * @param dataShape. New shape to add to the list.
	 * @return the next available slot in the tree. If there is not any, it returns null.
	 */
	public NaryTreeNode<Shape> addShapeNodeHelper(Shape dataShape) {
		// Constructs a new stack array.
		ArrayStack<NaryTreeNode> newStack = new ArrayStack<NaryTreeNode>();
		// pushes the root of the tree to the stack.
		newStack.push(tree.getRoot());
		// loops until stack is empty.
		while(!newStack.isEmpty()) {
			// pops stack and takes the returned value as the current.
			NaryTreeNode<Shape> current = newStack.pop();
			// we check the availability using checknode method.
			if (checkNode(current, dataShape)) {
				return current;
			}
			// adds new nodes to the stack. We  push the children nodes from right to left.(Because we use stack)
			for(int i=current.getNumChildren()-1;i>=0;i--) {
				newStack.push(current.getChild(i));
			}
		}
		// returns null if there is no available slot.
		return null;
	}
	/**
	 * @param node. Current node for the tree.
	 * @param newS. New shape for the tree.
	 * @return true if the current method has a available slot to add the new node.
	 */
	public boolean checkNode(NaryTreeNode<Shape> node, Shape newS) {
		// creates a shape object and assigns it with the data in the node.
		Shape nod = node.getData();
		// circle
		if(nod.getNumSides()==1) {
			// checks the color and available slot for a new shape and returns True.
			if(nod.getColour()!=newS.getColour() && node.getNumChildren()==0) {
				return true;
			}
		}//triangle-- 3 lines because of triangle.
		else if(nod.getNumSides()==3) {
			for(int i=0;i<3;i++) {
				// we just check color of parent and child node.
				if(nod.getColour()!=newS.getColour()) {
					// if there is no children under the node.
					if(node.getNumChildren()==0) return true;
					// if first slot is full.
					else if (node.getNumChildren()==1 && node.getChild(0).getData().getColour()!=newS.getColour()) return true;
					//if second slot is full
					else if (node.getNumChildren()==2 && node.getChild(0).getData().getColour()!=newS.getColour() && node.getChild(1).getData().getColour()!=newS.getColour()) return true;
				}
			}
		}//square -- 4 lines because of square.
		else if(nod.getNumSides()==4) {
			for(int i=0;i<4;i++) {
				// we just check color of parent and child node.
				if(nod.getColour()!=newS.getColour()) {
					// if there is no children under the node.
					if(node.getNumChildren()==0) return true;
					// if first slot is full.
					else if (node.getNumChildren()==1 && node.getChild(0).getData().getColour()!=newS.getColour()) return true;
					//if second slot is full
					else if (node.getNumChildren()==2 && node.getChild(0).getData().getColour()!=newS.getColour() && node.getChild(1).getData().getColour()!=newS.getColour()) return true;
					// if third slot is full
					else if (node.getNumChildren()==3 && node.getChild(0).getData().getColour()!=newS.getColour() && node.getChild(1).getData().getColour()!=newS.getColour()
							&& node.getChild(2).getData().getColour()!=newS.getColour()) return true;
				}
			}
		}//star -- 5 lines because if star.
		else if(nod.getNumSides()==5) {
			for(int i=0;i<5;i++) {
				// we just check color of parent and child node.
				if(nod.getColour()!=newS.getColour()) {
					// if there is no children under the node.
					if(node.getNumChildren()==0) return true;
					// if first slot is full
					else if (node.getNumChildren()==1 && node.getChild(0).getData().getColour()!=newS.getColour()) return true;
					//if second slot is full
					else if (node.getNumChildren()==2  && node.getChild(0).getData().getColour()!=newS.getColour() && node.getChild(1).getData().getColour()!=newS.getColour()) return true;
					// if third slot is full
					else if (node.getNumChildren()==3 && node.getChild(0).getData().getColour()!=newS.getColour() && node.getChild(1).getData().getColour()!=newS.getColour()
							&& node.getChild(2).getData().getColour()!=newS.getColour()) return true;
					// if fourth slot is full
					else if (node.getNumChildren()==4 && node.getChild(0).getData().getColour()!=newS.getColour() && node.getChild(1).getData().getColour()!=newS.getColour() &&
							node.getChild(2).getData().getColour()!=newS.getColour() && node.getChild(4).getData().getColour()!=newS.getColour()) return true;
				}
			}
		}else {
			return false;
		}
		// if an available slot is not found, returns false.
		return false;
	}
	// returns toString method in the LinkedNaryTree class.
	public String toString() {
		return tree.toString();
	}
}