//Node class for vertices of the graph.
public class Node {
	//name of the vertex
	private int name;
	private boolean mark;
	public Node(int name) {
		//Initializes the name of the vertex.
		this.name = name;
		mark = false;
	}
	public void setMark(boolean mark) {
		//Sets the mark.
		this.mark = mark;
	}
	//get method for mark
	public boolean getMark() {
		return mark;
	}
	//getter for name of the vertex.
	public int getName() {
		return name;
	}
}