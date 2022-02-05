//This class represents the data items to be stored in the BST.
public class Pixel {
	//color of the data.
	private int color;
	//location of the data or node.
	private Location p;
	//Location p is the key for the Pixel.
	//Constructor for this class to initialize color and location with the given inputs.
	public Pixel(Location p, int color) {
		this.color = color;
		this.p =p;
	}
	//get method to access location of the data.
	public Location getLocation() {
		return p;
	}
	//get method to access the color of the data.
	public int getColor() {
		return color;
	}
}