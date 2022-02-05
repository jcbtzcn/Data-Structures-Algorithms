/**
 * This class represents the main program to read the files, to calculate and store the distances between Cities.
 * @author CS1027B Assignment-1 Yakup Tezcan
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
public class Program {
	/**
	 * cityCount, cityArray, array private methods.
	 */
	private int cityCount=0;
	private City[] cityArray;
	private CompressedArray array;
	/**
	 * Constructor to build an array by using fileName.
	 * @param fileName filename that cities may be found.
	 * @param showMap  boolean value, true or false.
	 */
	public Program(String fileName, boolean showMap) {
		cityArray = new City[3];                            // Initializes cityArray with 3 cells.
		MyFileReader fileR = new MyFileReader(fileName);    // Creates an object of MyFileReader class.
		/**
		 * This try and catch is to get the size of text files.
		 * We write the text again to a new text file. 
		 */
		int size=0;	
		String line;
		line = fileR.readString();	                         // Invokes readString method of MyFileReader class.
		try {
		    FileWriter file = new FileWriter("text.txt");
	        BufferedWriter bw = new BufferedWriter(file);	
		    while (line != null) {
			    size++;                                      // Gets size of the text file.
			    bw.write(line + "\n");
			    line = fileR.readString();
		    }
		    bw.close();                                      // Closes file after writing to it.
		  }catch (Exception e) {                             // Throws an exception when the text ends.
			   System.out.println("");
		      }	  
		String[] myArray = new String[size];                // Finally we are initializing myArray with the size we get above.
		String line2;
		MyFileReader fileR2 = new MyFileReader("text.txt");
		line2 = fileR2.readString();
		/**
		 * We are iterating the new text file we get above to store the lines into myArray.
		 */
		for (int i=0; i < myArray.length; i++) {
			if (line2 != null) {
				myArray[i] = line2;
			}	
			line2 = fileR2.readString();	
		}
		/**
		 * We are finding the name of the city, x and y coordinates in myArray.
		 */
        for (int i=1; i < myArray.length;i = i+3) {
        	int num1 = Integer.parseInt(myArray[i+1]);       	       // x coordinate of the cities.
        	int num2 = Integer.parseInt(myArray[i+2]);	               // y coordinate of the cities.
        	City cityOfArray = new City(myArray[i], num1, num2);       // Creating new city object and using name, x and y coordinates to initialize it.
        	cityArray[(i-1)/3] = cityOfArray; 	                       // Placing the object in the CityArray.
        	if (cityArray.length-1 == (i-1)/3) {                       // Extending capacity when needed.
            	expandCapacity();
            }
        } 
       if (showMap == true) {	 
        	Map mapObj = new Map();                                    // Creates a new Map object.
        	for (int i=0;i < cityArray.length;i++) {
        		if (cityArray[i] != null) {
        		    mapObj.addCity(cityArray[i]);                      // Add new city for each cityArray.
        		}
        	}
        }
	}
	/**
	 * @return cityArray
	 */
	public City[] getCityList() {	                   
		return cityArray;
	}
	/**
	 * expands the capacity of CityArray by adding 3.
	 */
	private void expandCapacity() {				
		City[] largeArray = new City[getCityList().length+3];	// Expanding by adding 3.	
		for(int i = 0; i < getCityList().length; i++)
		     largeArray[i] = cityArray[i];		
		cityArray = largeArray;	                                // largeArray is equal to cityArray again.
	}
	/**
	 * @param city1 first city 
	 * @param city2 second city
	 * @return distance calculates the distance between two cities and return it.
	 */
	public double disBetweenCities (City city1, City city2) {
		double distance;
		// Euclidean distance between the cities.
		distance = Math.sqrt( Math.pow((city1.getX() - city2.getX()), 2) + Math.pow((city1.getY() - city2.getY()), 2) );
		return distance; 
	}
	public void compareDistances () {	                                    // This method compares the distances for each possible pairs.
		for (int i=0;i<cityArray.length;i++) {                              // We check how many cities we have after we constructed the arrays.
			if (cityArray[i] != null) {
				cityCount += 1;
			}
		}	
		double[][] twoArray = new double[cityCount][cityCount];             // Creates a 2D array with number of cities.
		double result;
		for (int i=1;i < cityCount;i++ ) {
			for (int j=0;j < i;j++) {
				if (cityArray[j] != null && cityArray[i] != null) {
				    result = disBetweenCities(cityArray[i], cityArray[j]);   // Finding result by using the disBetweenCities method.
				twoArray[i][j] = result;                                     //  Adding the result into a 2D array.
				}
			}
		}	
	}
	/**
	 * Creating a new array object by using CompressedArray class and giving the twoArray array.
	 * @return array
	 */
	public CompressedArray getArray () {                                  
		double[][] twoArray = new double[cityCount][cityCount];            // We construct a 2D array.
		double result;
		for (int i=1;i < cityArray.length;i++ ) {
			for (int j=0;j < i;j++) {
				if (cityArray[j] != null && cityArray[i] != null) {
				    result = disBetweenCities(cityArray[i], cityArray[j]);
				twoArray[i][j] = result;                                     // we put store the results here in the 2D array.
				}
			}
		}
		array = new CompressedArray(twoArray);                              // Creating array object.
		return array;                                                      // Returns array.
	}
}