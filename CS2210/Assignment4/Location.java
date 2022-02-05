//This class builds a Location object.
public class Location {
	// x coordinate
	private int x;
	// y coordinate
	private int y;
	//Constructor for the class. Takes two parameter x and y to initialize the coordinates.
	public Location(int x, int y) {
		this.x =x;
		this.y =y;
	}
	//returns the x coordinate of the location.
	public int xCoord() {
		return x;
	}
	//returns the y coordinate of the location.
	public int yCoord() {
		return y;
	}
	//Compares the current location with the given location p.
	//Returns -1 if the given location is greater, 1 if smaller and 0 if they are equal.
	public int compareTo(Location p) {
		if (x < p.x || (x == p.x && y < p.y)){
			return -1;
		}else if(x > p.x || (x == p.x && y > p.y)) {
			return 1;
		}else {
			return 0;
		}
	}
}