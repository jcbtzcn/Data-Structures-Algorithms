/*
 * This class implements DictionaryADT and builds a dictionary  using hash tables with separate chaining.
 */
public class Dictionary implements DictionaryADT{
	//default size of the dictionary.
	int size=7753;
	//Initializes a table with Record variables.
	Record[] table = new Record[size];
	//Constructor for the class. Takes one parameter.
	public Dictionary(int size) {
		//Assigns this.size with size and assings the table with the size.
		this.size = size;
		table = new Record[size];
	}
	//Insert the given Record pair in the dictionary.
	public int insert(Record pair) throws DictionaryException {
		//Gets the index of config by using h function.
		int index = h(pair.getConfig());
		//result to check collisions.
		int result=1;
		//if the index is null then it means there will not be any collision.
		if(table[index] == null) {
			//if it was null, we will return result with 0.
			result=0;
		}
		//if the variables at index of table is null and we are trying to add the same value to the dictionary, it throws an error.
		if(table[index]!= null && table[index].getConfig().equals(pair.getConfig())) {
			throw new DictionaryException();
		}
		//inserts the pair to the index of the table.
	    table[index] = pair;
	    //returns result.
		return result;
	}
	//Hash fuction to determine the index of the key.
	private int h(String key) {
		int x=33;  //small integer prime number.
		//First character of the String key.
		int val = (int)key.charAt(0);
		//For loop to get the integer of all characters in key.
		for(int i=1;i<key.length();i++) {
			val = (val*x + (int)key.charAt(i)) % size;
		}
		//returns the index number.
		return val;
	}
	//remove method.
	public void remove(String config) throws DictionaryException{
		//takes the index for the config using hash function.
		int index=h(config);
		//if config is not in the dictionray, throws an error.
		if(table[index] == null) {
			throw new DictionaryException();
		}
		//if config is in the dictionary, we remove it by assigning table[index] to null.
		if(table[index].getConfig().equals(config)) {
			table[index] = null;
		}
	}
	//This method gets the score of parameter config if it is in the dictionary.
	public int get(String config) {
		int index = h(config);
		//if config is in the array, then it returns the score of it.
		if(table[index]!=null) {
			return table[index].getScore();
		}
		//otherwise returns -1.
		return -1;
	}
	//Method to access the length of table, number of elements in the dictionary.
	public int numElements() {
		return table.length;
	}
}
