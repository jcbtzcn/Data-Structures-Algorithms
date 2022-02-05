//This class constructs a graph.
import java.util.*;
public class Graph implements GraphADT{
	//node array to store the vertices of the graph.
	private Node graphNode[];
	//I used and adjacency matrix.
	private Edge edgeMatrix[][];
	//number of vertices
	private int n;
	public Graph(int n){
		//Initializes number of vertices, adjacency matrix.
		this.n = n;
		graphNode= new Node[n];
		for(int i=0; i< n;i++) {
			graphNode[i] = new Node(i);
		}
		edgeMatrix = new Edge[n][n];
	}
	//Returns node if the given name is in the graph. Otherwise returns exception.
	public Node getNode(int name) throws GraphException {
		if(name < 0 || name >= n)
			throw new GraphException("Node not found.");
		else
			return graphNode[name];
	}
	//Checks if the given vertices in the graph. Returns false if not.
	private boolean nodeCheck(Node u, Node v) {
		try{
			getNode(u.getName());
			getNode(v.getName());
			return true;
		}catch(GraphException e){
			return false;
		}
	}
	//Inserts a new edge to the graph.
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException{
		if(nodeCheck(u, v)) {
			Edge ed = edgeMatrix[u.getName()][v.getName()];
			//if edge is null then we insert.
			if(ed == null) {
				//inserts both locations because of the 2D array implementation.
				edgeMatrix[u.getName()][v.getName()] = new Edge(u, v, edgeType);
				edgeMatrix[v.getName()][u.getName()] = new Edge(v, u, edgeType);
			}else
				throw new GraphException("Existing Edge");
		}
		else {
			throw new GraphException("Nodes do not exist in the graph");
		}
	}
	//Checks if the given node is in the graph. Returns false if not.
	private boolean nodeCheckOne(Node u) {
		try{
			getNode(u.getName());
			return true;
		}catch(GraphException e){
			return false;
		}
	}
	//Creates a stack iterator for the incident edges on u.
	public Iterator incidentEdges(Node u) throws GraphException{
		if(nodeCheckOne(u)) {
			Stack newStack = new Stack();
			int count=0;
			while(count < n) {	
				Edge edgeCheck = edgeMatrix[u.getName()][count];
				if(edgeCheck != null)
					newStack.push(edgeCheck);
				count++;
			}
			return newStack.iterator();
		}else
			throw new GraphException("Node does not exist in the graph");
	}
	//gets edge of the given vertices if it is in the graph.
	public Edge getEdge(Node u, Node v) throws GraphException{
		if(nodeCheck(u, v)) {
			Edge edgeCheck = edgeMatrix[u.getName()][v.getName()];
			if(edgeCheck == null)
				throw new GraphException("Edge does not exist");
			else
				return edgeCheck;
		}
		else
			throw new GraphException("Nodes do not exist in the graph");
	}
	//Checks if two vertices are adjacent.
	public boolean areAdjacent(Node u, Node v) throws GraphException{
		if(nodeCheck(u, v)) {
			Edge edgeCheck = edgeMatrix[u.getName()][v.getName()];
			if(edgeCheck == null)
				return false;
			else
				return true;
		}else
			throw new GraphException("Nodes do not exist in the graph");
	}
}
