/**
 * This class is moving figures by using a binary search tree.
 * @author Yakup Tezcan CS2210B Assignment 4
 */
public class GraphicalFigure implements GraphicalFigureADT {
	//identifier of this figure.
	private int id;
	//Width of the enclosing rectangle of this figure.
	private int width;
	//Height of the enclosing rectangle of this figure.
	private int height;
	//Type of the figure.
	private String type;
	//Location-offset of the figure.
	private Location pos;
	//A new binary search tree.
	BinarySearchTree tree;
	//This constructors takes id, width, height, type and pos for the figure as input and initializes them.
	public GraphicalFigure(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		//Creates a new binary search tree with a root that is a leaf for now.
		tree = new BinarySearchTree();
	}
	//Sets the type of the figure with the given type.
	public void setType(String type) {
		this.type = type;
	}
	//Access method for the width of the enclosing rectangle.
	public int getWidth() {
		return width;
	}
	//Access method for the height of the enclosing rectangle.
	public int getHeight() {
		return height;
	}
	//Access method for the type of the figure.
	public String getType() {
		return type;
	}
	//Access method for the identifier of the figure.
	public int getId() {
		return id;
	}
	//Access method for the offset-location of figure.
	public Location getOffset() {
		return pos;
	}
	//Sets the offset of the figure with the given value.
	public void setOffset(Location value) {
		pos = value;
	}
	//Adds pix as a new pixel. Throws an exception if the pixel is already in the tree.
	public void addPixel(Pixel pix) throws DuplicatedKeyException{
		//Try to insert the pix, if throws an exception in the put method, this method catches it and throws an exception too.
		try {
			tree.put(tree.getRoot(), pix);
		}catch(Exception e) {
			throw new DuplicatedKeyException();
		}
	}
	//This method returns true if this figure and the given figure(gobj) intersects, returns false otherwise.
	public boolean intersects(GraphicalFigure gobj) {
		//first we check the y coordinate.
		if(pos.yCoord() > gobj.getOffset().yCoord() + gobj.getHeight())
			return false;
		else if(pos.yCoord() + getHeight() < gobj.getOffset().yCoord())
			return false;
		//after we check y, we need to check x.
		else {
			//Here we use width of the enclosing rectangle because of x coordinate(instead of height).
			if(pos.xCoord() > gobj.getOffset().xCoord() + gobj.getWidth())
				return false;
			else if(pos.xCoord() + getWidth() < gobj.getOffset().xCoord())
				return false;
			//We made sure that the figure is in the enclosing rectangle.
			//So now, we need to find the current location of the figure to make sure it does not overlap with the target or the figure controlled by the computer or wall(fixed figures).
			else {
				//We get the larger and smallest location in the tree.
				Location larger = tree.largest(tree.getRoot()).getLocation();
				Pixel smallest = tree.smallest(tree.getRoot());
				while(smallest.getLocation().compareTo(larger) < 0) {
					//Now we need to find the location of the figure and confirm the intersection or overlap.
					//Below, we are finding x and y by using the current location, offset and the given figure.
					int xSmallest = smallest.getLocation().xCoord();
					int xThis = getOffset().xCoord();
					int xGobj = gobj.getOffset().xCoord();
					int x = xSmallest + xThis - xGobj;
					int ySmallest = smallest.getLocation().yCoord();
					int yThis = smallest.getLocation().yCoord();
					int yGobj = gobj.getOffset().yCoord();
					int y = ySmallest + yThis - yGobj;
					//Then we create a new location to check of this new(current location)overlaps with any figures(fixed, wall, computer) in the enclosing rectangle.
					Location checkLoc = new Location(x,y);
					//This condition checks if this pixel is already in the tree.
					if(gobj.findPixel(checkLoc))
						return true;
					//When it returns false, we need to check other pixel in the tree so we get the next larger key than the given one(smallest in the first case, then it goes up).
					smallest = tree.successor(tree.getRoot(), smallest.getLocation());
				}
			}
		}
		return false;
	}
	//This method checks if given location of the pixel is already in the tree. Returns false otherwise.
	private boolean findPixel(Location p) {
		//We use get method from binary search tree to confirm the Pixel.
		Pixel newPixel = tree.get(tree.getRoot(), p);
		if (newPixel==null)
			return true;
		else
			return false;
	}
}