
public class test {
	public static void main(String args[]) {
		RoadMap streetMap = new RoadMap("map0");
		Node s = streetMap.getGraph().getNode(4);
		Node s1 = streetMap.getGraph().getNode(8);
		Edge edge = streetMap.getGraph().getEdge(s, s1);
		Node t = edge.firstEndpoint();
		System.out.print(edge.getType());
	}
}
