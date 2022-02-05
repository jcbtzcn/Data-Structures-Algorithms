/**
 * This class represents a compressed array from a 2d array.  
 * @author CS1027B Assignment-1 Yakup Tezcan
 */
public class CompressedArray {
	/**
	 * origArraySize, array, newtwoArray private methods.
	 */
	private int origArraySize;
	private double[] array;
	private double[][] newtwoArray;
	/**
	 * Constructor takes a 2D array and converts it to linear array.
	 * @param origArray 2D array
	 */
	public CompressedArray (double[][] origArray) {	
		origArraySize = origArray.length;         // Length of original size.
		// Gets the size of linear array that we will construct.
		int arraySize=0;
		for (int i=origArraySize-1;i>0;i--) {
			arraySize += i;
		}
		array = new double[arraySize];  // Array size of the linear array.
		// Converts linear array from 2D array.
		int k =0;
		for (int i = 1; i < origArraySize; i++) {
			for (int j=0; j < i; j++) {
				array[k] = origArray[i][j];
				k = k+1;								
			}
		}
		// We are copying two dimensional array to newtwoArray array to use in toString method.
		newtwoArray = new double[origArraySize][origArraySize];
		for (int i=0;i<origArraySize;i++) {
			for(int j=0;j<origArraySize;j++) {
				newtwoArray[i][j] = origArray[i][j];
			}
		}		
	}
	/**
	 * Length of the linear array.
	 * @return length of the constructed array.
	 */
	public int getLength() {
		return array.length;
	}
	/**
	 * An element from the array.
	 * @param element the given index.
	 * @return an element from the array.
	 */
	public double getElement(int element) {
		return array[element];
	}
	// check if the given array and the compressed array are equal or not.
	public boolean equals(CompressedArray arrayList) {			
		if ((getLength() == ( arrayList).getLength())){   // Checks the length of the arrays.
			for (int i = 0; i < getLength(); i++) {       
				if (array[i] == arrayList.getElement(i)) { // Checks the every element by iterating each index.
					return true;                           // returns true when both arrays are the same in terms of length and elements.
				} else {
					return false;                         // returns false if length is the same but elements are not.
				}
			}			
		}
		return false;	                                 // returns false if they are not equal.
	}
	/**
	 * toString method prints out the left lower side of the diagonal in the pyramid format.
	 * We are using two dimensional array that we copied in the constructor.
	 */
	public String  toString() {
		String text = "";                          // a null text to store the String.
		for (int i=1; i< origArraySize;i++) {      // iterates every element and gets the left lower side of the matrix.
			text = text + "\n";
			for (int j=0;j<i;j++) {
				text = text + String.format("%8.2f", newtwoArray[i][j]);	// stores String in the pyramid shape.		
			}			
		}  
		text = text + "\n"; // to add newline at the end of the text.
		return text;        // returns text.
   }	
}