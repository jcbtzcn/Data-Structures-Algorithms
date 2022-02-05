/**
 * This class represents a City and is consisted of getters, setters and toString method.
 * @author CS1027B Assignment-1 Yakup Tezcan
 */
public class City {
    /**
     * name, x, y, marker for City
     */
	private String name;
	private int x;
	private int y;
	private CityMarker marker;
	/**
	 * 
	 * @param name of the City
	 * @param x coordinate of the City
	 * @param y coordinate of the City
	 */
	public City(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		// Constructs a CityMarker object
		marker = new CityMarker();
	}
	/**
	 * Accessor method to get the name of the City
	 * @return name of the City
	 */
	public String getName() {
		return name;
	}
	/**
	 * Accessor method to get the x coordinate of the City
	 * @return x coordinate of the City
	 */
	public int getX() {
		return x;
	}
	/**
	 * Accessor method to get the y coordinate of the City
	 * @return y coordinate of the City
	 */
	public int getY() {
		return y;
	}
	/**
	 * Accessor method to get the CityMarker object
	 * @return marker object of CityMarker
	 */
	public CityMarker getMarker() {
		return marker;
	}
	// sets name of the City
	public void setName (String name) {
		this.name = name;
	}
	// sets x coordinate of the City
	public void setX (int x) {
		this.x = x;
	}
	// sets y coordinate of the city
	public void setY(int y) {
		this.y = y;
	}
	// sets a new object for CityMarker
	public void setMarker(CityMarker marker) {
		this.marker = marker;
	}
	// toString methods prints out the name of the city in the following format
	public String toString () {
		return "Name of the city: " + name;
	}
}