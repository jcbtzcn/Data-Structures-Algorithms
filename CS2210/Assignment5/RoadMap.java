import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;
//I could not fix  a bug in the findPath method but still adding for compiling.
public class RoadMap {
	private Graph graph;
	private int startingNode, destinationNode, initialMoneyy;
	private int width, length;
	private int toll, gain;
	public RoadMap(String inputFile) throws MapException{
		try{
			FileReader file = new FileReader(inputFile);
			BufferedReader inputText = new BufferedReader(file);
			// We do not the first line input.
			Integer.parseInt(inputText.readLine());
			//starting node
			startingNode = Integer.parseInt(inputText.readLine());
			//destination
			destinationNode = Integer.parseInt(inputText.readLine());
			//width of the map
			width = Integer.parseInt(inputText.readLine());
			//length of the map
			length = Integer.parseInt(inputText.readLine());
			//initial money
			initialMoneyy = Integer.parseInt(inputText.readLine());
			//money to pay for private roads
			toll = Integer.parseInt(inputText.readLine());
			//money to gain by using reward roads
			gain = Integer.parseInt(inputText.readLine());
			//creating a new graph by multiplying width and length.
			graph = new Graph(width*length);
			//Starts to read after line 8.
			String line;
			//to check row numbers
			int row=0;
			//when the program can read a new line
			while((line = inputText.readLine()) != null) {
				//Now, it reads the characters of the line from input file.
				for(int i=0;i<line.length();i++) {
					//checking if lines have the same length.
				    if(row % 2 != 0 || i % 2 != 0) {
				    	if(line.charAt(i) == 'T') {
				    		if(row%2 == 0)
				    			graph.insertEdge(graph.getNode(((i-1)/2) + (row/2)*width ), graph.getNode(((i-1)/2) + (row/2)*width + 1 ), 1);
				    		else
				    			graph.insertEdge(graph.getNode(i/2 + ((row-1)/2)*width ), graph.getNode(i/2 + ((row+1)/2)*width), 1);
				    	}else if(line.charAt(i) == 'F') {
				    		if(row%2 == 0)
				    			graph.insertEdge(graph.getNode(((i-1)/2) + (row/2)*width ), graph.getNode(((i-1)/2) + (row/2)*width + 1 ), 0);
				    		else
				    			graph.insertEdge(graph.getNode(i/2 + ((row-1)/2)*width ), graph.getNode(i/2 + ((row+1)/2)*width), 0);
				    	}else if(line.charAt(i) == 'C') {
				    		if(row%2 == 0)
				    			graph.insertEdge(graph.getNode(((i-1)/2) + (row/2)*width ), graph.getNode(((i-1)/2)+ (row/2)*width + 1 ), -1);
				    		else
				    			graph.insertEdge(graph.getNode(i/2 + ((row-1)/2)*width ), graph.getNode(i/2 + ((row+1)/2)*width), -1);
				    	}
				    }
				}
				row++;
			}
			
		    inputText.close();
		
		}catch(Exception e) {
			throw new MapException();
		}
	}
	public Graph getGraph() {
		if (graph != null)
			return graph;
		else
			return null;
	}
	public int getStartingNode() {
		return startingNode;
	}
	public int getDestinationNode() {
		return destinationNode;
	}
	public int getInitialMoney() {
		return initialMoneyy;
	}
	public Iterator findPath(int start, int destination, int initialMoney) {
		Node s = graph.getNode(start);
		Node t =graph.getNode(destination);
		int money = initialMoney;
		Stack stack = new Stack();
		s.setMark(true);
		stack.push(s);
		
		while(!stack.empty()) {
			Node current = (Node) stack.peek();
			if(current == t)
				return stack.iterator();
			else {
				Iterator check = graph.incidentEdges(current);
				while(check.hasNext()) {
					Edge tmp = (Edge) check.next();
					Node tmpNode = tmp.secondEndpoint();
					if(!tmpNode.getMark()) {
						if(tmp.getType() == 0) {
							tmpNode.setMark(true);
							stack.push(tmpNode);
						}else if(tmp.getType() == 1 && money >= toll) {
							tmpNode.setMark(true);
							stack.push(tmpNode);
							money = money - toll;
						}else if(tmp.getType() == -1) {
							tmpNode.setMark(true);
							stack.push(tmpNode);
							money = money + gain;
						}
					}
					
				}
			}
		}
		return stack.iterator();
	}
}