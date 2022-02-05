/**
 * This class creates a queue by using a circular array. It implements QueueADT interface.
 * @author CS1027 Assignment 3 Yakup Tezcan
 */
public class CircularArrayQueue<T> implements QueueADT<T>{
	// Queue front index.
	private int front;
	// Queue rear index.
	private int rear;
	// Counts the number of elements in the queue.
	private int count;
	// Creates a queue array.
	private T[] queue;
	//Default capacity of queue array.
	private final int DEFAULT_CAPACITY=20;
	/**
	 * Constructor for the class. Does not take any parameter.
	 */
	public CircularArrayQueue() {
		//Assigns front with 1.
		front=1;
		//Assigns rear with default capacity.
		rear = DEFAULT_CAPACITY;
		//Assigns count with 0.
		count=0;
		//Determines the capacity of queue array.
		queue = (T[])(new Object[DEFAULT_CAPACITY]);
	}
	/**
	 * Second constructor. Takes an initial capacity parameter for the queue array.
	 * Assigns front and count with 1 and 0 respectively. 
	 */
	public CircularArrayQueue(int inputCapacity) {
		front=1;
		//Assigns rear with the initial capacity.
		rear=inputCapacity;
		count=0;
		//Determines the capacity of queue array with the initial capacity.
		queue = (T[]) (new Object[inputCapacity]);
	}
	/**
	 * Adds new element to the queue array.
	 */
	public void enqueue(T newItem) {
		//expands queue if capacity is over.
		if (size()==queue.length) {
			expandCapacity();
		}
		//finds the new index to add the new item to the queue.
		rear = (rear+1) % queue.length;
		//adds the new item.
		queue[rear]=newItem;
		//increments the number of elements in the queue.
		count++;
	}
	/**
	 * Expands the capacity of the queue.
	 */
	private void expandCapacity() {
		//Creates new queue array.
		T[] newArray = (T[])(new Object[queue.length+20]);
		//Adds the elements in the queue to the newArray.
		for(int i=front;i<=queue.length;i++) {
			newArray[i]=queue[front%queue.length];
			front++;	
		}
		//Initializes front and count with the proper variables.
		front=1;
		rear=count;
		//Assigns newArray to the queue again.
		queue=newArray;	
	}
	/**
	 * @return temp. Returns the removed element.
	 */
	public T dequeue() throws EmptyCollectionException {
		// Throws an exception if the queue is empty.
		if (isEmpty()) {
			throw new EmptyCollectionException("The queue is empty");
		}
		T temp = queue[front];
		//Decrements the number of elements in the list.
		count--;
		//Assigns the new front after removing the front element.
		front=(front+1)%queue.length;	
		return temp;
	}
	/**
	 * @return firstItem. Returns the first item in the queue.
	 */
	public T first() throws EmptyCollectionException {
		//Throws an exception if the queue is empty.
		if (count==0) {
			throw new EmptyCollectionException("The queue is empty");
		}
		T firstItem=queue[front];
		return firstItem;
	}
	/**
	 * @return true or false if the queue is empty.
	 */
	public boolean isEmpty() {
		// checks if the count is 0 or not.
		return count==0;
	}
	/**
	 * @return count. Size of the queue array.
	 */
	public int size() {
		return count;
	}
	/**
	 * @return front. Returns the index of front element.
	 */
	public int getFront() {
		return front;
	}
	/**
	 * 
	 * @return rear. Returns the index of rear element.
	 */
	public int getRear() {
		return rear;
	}
	/**
	 * @return length of the queue array.
	 */
	public int getLength() {
		return queue.length;
	}
	/**
	 * toString method for the circular array.
	 * @return text. Returns the formatted text to display all the elements in the queue array.
	 */
	public String toString() {
		String text="QUEUE: ";
		// returns the queue is empty if the array is empty.
		if(count==0) {
			return "The queue is empty";
		}
		for (int i=0;i<count-1;i++) {
			text+= queue[front+i%queue.length] + ", ";
		}
		// last element to add the dot at the end of the queue.
		text+=queue[rear%queue.length] + ".";
		return text;	
	}
}