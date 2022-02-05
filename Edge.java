//Edge class for graph.
public class Edge {
	//first endpoint of the edge
	private Node u;
	//second endpoint of the edge.
	private Node v;
	//type of the edge
	private int type;
	public Edge(Node u, Node v, int type) {
		//Initializes variables related to the edge.
		this.u = u;
		this.v = v;
		this.type = type;
	}
	//getter for the first end point
	public Node firstEndpoint() {
		return u;
	}
	//getter for the second end point
	public Node secondEndpoint() {
		return v;
	}
	//getter for the type of the edge.
	public int getType() {
		return type;
	}
}