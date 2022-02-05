/**
 * This class creates an array stack and its methods.
 * @author CS1027B Assignment2 Yakup Tezan
 */
public class ArrayStack<T> implements ArrayStackADT<T> {
	// Creates an object of ArrayStackADT
	// top to determine the index of array stack
	// sequence to check the correct path on map. 
	private T[] stack;
	private int top;
	public static String sequence;
	/**
	 * Constructor without parameter.
	 */
	public ArrayStack() {
		sequence="";                                            // creates an empty String
		int DEFAULT_INITIAL_CAPACITY = 14;                      // default capacity for array stack
		top= -1;                                                // top is -1 because we want to use top as the index of elements that will be pushed into the stack.
		stack = (T[])(new Object[DEFAULT_INITIAL_CAPACITY]);    // creates a stack array with default capacity.
	}
	/**
	 * takes a capacity for the stack array
	 * @param initialCapacity
	 */
	public ArrayStack(int initialCapacity) {
		sequence="";                                            // creates an empty String
		top = -1;                                               // top is -1 because we want to use top as the index of elements that will be pushed into the stack.
		stack = (T[])(new Object[initialCapacity]);             // creates a stack array with the initial capacity.
	}
	/**
	 * Method to push new elements into the stack.
	 * @param dataItem
	 */
	public void push(T dataItem) {
		if (top+1 == stack.length && stack.length < 50) {                // expands the stack capacity by ten if the stack length is smaller than 50.
			expandCapacityTen();
		}else if (top+1 == stack.length && stack.length >= 50) {         // doubles the stack capacity if the stack length is larger than 50.
			expandCapacityDouble();
		}
		stack[top+1] = dataItem;                                         // adding the new item to the correct index.
		top++;                                                           //adding new index to the stack.
		if (dataItem instanceof MapCell) {
			sequence += "push" + ((MapCell)dataItem).getIdentifier();     // printing the sequence of cupid on the map.
		}else {
			sequence += "push" + dataItem.toString();
		}		
	}	
	// expand capacity method for array stack. Expanding 10.
	private void expandCapacityTen() {
		T[] larger = (T[])(new Object[stack.length+10]);	
		for (int i=0;i<top+1;i++) {
			larger[i] = stack[i];
		}
		stack = larger;
	}
	// expand capacity method for array stack. Doubling the capacity.
	private void expandCapacityDouble() {
        T[] larger = (T[])(new Object[stack.length*2]);
		for (int i=0;i<top+1;i++) {
			larger[i] = stack[i];
		}
		stack = larger;
	}
	/**
	 * Returns the popped item. 
	 * Also decreases the length of the stack array if the number of elements in the stack is 1/4 of the stack length. 
	 * Lowest capacity is set to be 14. ( Because of default capacity)
	 * @return dataItem 
	 */
	public  T pop() throws EmptyStackException{
		if (top == -1) {                                         // Throws empty stack exception if stack array is empty.
			throw new EmptyStackException("Empty Stack");
		}
		top--;
		T topItem = stack[top+1];                               // pops the last item in the stack, gives it a null.
		stack[top+1]=null;	                                   
		int sizeofNewArray;
		if (top+1 < stack.length/4) {
			if (stack.length >= 28) {
				sizeofNewArray = stack.length/2;
			}else {
				sizeofNewArray = 14;
			}		
			T[] newArray = (T[])(new Object[sizeofNewArray]);   // Creating a new array to decrease the stack array length when needed.
			for (int i=0;i<top+1;i++) {
				newArray[i] = stack[i];
			}
			stack = newArray;
		}
		if (topItem instanceof MapCell) {                       // to print out the sequence of stack array.
			sequence += "pop" + ((MapCell)topItem).getIdentifier();
		}else {
			sequence += "pop" + topItem.toString();
		} 
		return topItem;    // returns the removed item.
	}
	// Returns the top element in the stack array. It is a savior!
	public T peek() throws EmptyStackException{
		if (top == -1) {
			throw new EmptyStackException("Empty Stack");
		}
		return stack[top];
	}
	// returns true if stack is empty and false otherwise.
	public boolean isEmpty() {
		return (top == -1);
	}
	// returns the size of the array. Another savior!
	public int size() {
		return top+1;
	}
	// returns the length of the stack array.
	public int length() {
		return stack.length;
	}
	// returns text of sequence
	public String toString() {
	    String text = "Stack: ";
	    for (int i=0;i<top;i++) {
	    	text += stack[i] + ", ";
	    }
	    text += stack[top];
	    return text;
	}
}