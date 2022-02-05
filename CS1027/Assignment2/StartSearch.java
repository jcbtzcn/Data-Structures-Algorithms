// imports IOException and FileNotFoundException
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * This class considers all the possibilities for our hero Cupid and takes him to the Target.
 * We use provided limitations to move Cupid and other instructions to determine the best available paths.
 * @author CS1027B Assignment2 Yakup Tezcan
 */
public class StartSearch {
	private Map targetMap; //Where Cupid and the targets are located.
	private int numArrows; // Cupid arrows. Compare this to the quiverSize from the Map.
	private int inertia=0; // This variable shows how many times an arrow has travelled in the same direction. Starts at 0.
	private int direction; //Tracking the direction of the arrow.
	/**
	 * Constructor method for the class. Takes a parameter and reads the file on Map class then returns a Map.
	 * @param filename
	 */
	public StartSearch(String filename){
		try {
			targetMap = new Map(filename);       // Building a map.
		} 
		catch (FileNotFoundException e) {    // catches the first exception
		      System.out.print("File Not Found");
		} catch (IOException e) {                  // invalid file
			  System.out.print("Invalid File");  
		}catch (InvalidMapException e) {         // invalid map
			System.out.println("Invalid Map");
		}
	}
	/**
	 * Main method for the class. It provides the algorithm to move Cupid forward on the correct paths.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StartSearch objectSearch = new StartSearch(args[0]);       // args0 is parameter for the map name
			// targets that Cupid found <3.
			int foundTargets = 0;
			// max path length
			int maxPathLength;
			// stack array with MapCell class variables
			ArrayStack<MapCell> stack;
			if (args.length > 1) {  // Checks if an argument given or not.
				stack = new ArrayStack<MapCell>(Integer.parseInt(args[1]));
				maxPathLength = Integer.parseInt(args[1]);
			}else {                     // if no argument given then it uses the first constructor method of StackArray class.
				stack = new ArrayStack<MapCell>(); 
				maxPathLength = -1;   // max length is not important
			}
			// counts the steps that Cupid took so far.
			int stepCounter=0;
			// counts backtrack
			int backtrack = 0;
			objectSearch.numArrows = objectSearch.targetMap.quiverSize();  // gets quiverSize from map class and assigns it with numArrows.		
			while(objectSearch.numArrows > 0 && stepCounter != maxPathLength) { // first loop to start the search for a new path.
				stack.push(objectSearch.targetMap.getStart());                  // gets the start cell from map class.
				objectSearch.targetMap.getStart().markInStack();                // marks the start cell
				while(!stack.isEmpty()) {                                       // if stack is not empty he keeps looking for another path. If it is, then he fires another arrow.
					MapCell tempCell = objectSearch.nextCell(stack.peek());     // gives the start cell as parameter to find the next cell. After first one, it keeps doing the same for the each elements in the stack.
					if (tempCell!=null) {                                       // checks if there is an available cell to move.
						stack.push(tempCell);                                   // pushes new cell to the stack.
						tempCell.markInStack();                                 // marks the new found cell.
						stepCounter++;                                          // increments the step
						if (tempCell.isTarget()) {                              // if Cupid finds a target
							foundTargets++;                                     // increments the target count
							while(!stack.isEmpty()) {                           // clears the stack after finding a target.
								stack.pop();                                    // popping all the elements.
								if (stack.size() == 2) {                        // when it comes to the adjacent cell of cupid, it marks it out so Cupid does not use it again and can look for new paths.
									 stack.peek().markOutStack();               // That target already got all the love he needs.
								}
							}
						}
					}else { 
						if(backtrack < 3) {                                      // checking if backtracks is less than 3.
						    stack.pop();                                         // pops one item and backtracks.
						    stepCounter++;                                       
						    backtrack++;                                         // increments backtrack
						    while(!stack.isEmpty() && foundTargets < 1) {        // checks if target is not found and stack is not empty
							    stack.pop();                                      
							    if (stack.size() == 2) {                         // when it comes to the adjacent cell of cupid, it marks it out so Cupid does not use it again and can look for new paths.
								   stack.peek().markOutStack();
							   }
						  }					
					    }
					}
				}
				backtrack=0;                                                    // Reseting all the numbers here and decrements arrows because it there was no more available path and he needs other paths.                      
				stepCounter=0;
				objectSearch.numArrows--;
				objectSearch.inertia=0;
			}		
			if (objectSearch.numArrows == 0) {                                  // After arrow is zero, it prints out the found target number.
				System.out.println(foundTargets + " target(s) found.");
			}
		}catch(EmptyStackException e1) {                                        // Catches empty stacks and prints a message.
			System.out.print("The stack is empty!");
		}catch(InvalidNeighbourIndexException e2) {                             // Catches invalid neighbours and prints a message.
			System.out.print("Invalid Neighbour"); 
		}catch(IllegalArgumentException e3) {                                   // Catches illegal arguments(inputs) and prints a message.
			System.out.println("Illegal Argument");
		}catch(InvalidMapException e4) {                                        // Catches illegal arguments(inputs) and prints a message.
			System.out.println("Invalid Map");
		}
	}  
	/**
	 * This long method is assigned to find the correct path for Cupid.
	 * @param cell
	 * @return the next available cell or null
	 */
	public MapCell nextCell(MapCell cell) {
		// Start Cell  to determine a direction and start to move Cupid forward. It goes to Target if there is any around when it starts.
		if (cell.isStart() && cell.getNeighbour(0)!=null && !cell.getNeighbour(0).isBlackHole() && !cell.getNeighbour(0).isMarked() && cell.getNeighbour(0).isTarget()) {
			return cell.getNeighbour(0);
		}else if (cell.isStart() && cell.getNeighbour(1)!=null && !cell.getNeighbour(1).isBlackHole() && !cell.getNeighbour(1).isMarked() && cell.getNeighbour(1).isTarget()) {
			return cell.getNeighbour(1);
		}else if (cell.isStart() && cell.getNeighbour(2)!=null && !cell.getNeighbour(2).isBlackHole() && !cell.getNeighbour(2).isMarked() && cell.getNeighbour(2).isTarget()) {
			return cell.getNeighbour(2);
		}else if (cell.isStart() && cell.getNeighbour(3)!=null && !cell.getNeighbour(3).isBlackHole() && !cell.getNeighbour(3).isMarked() && cell.getNeighbour(3).isTarget()) {
			return cell.getNeighbour(3);
		// Start cell- if there is no target around the start cell of cupid then it chooses to go to Crosspath. There 
		}else if (cell.isStart() && cell.getNeighbour(0) != null && !cell.getNeighbour(0).isBlackHole() && !cell.getNeighbour(0).isMarked() && cell.getNeighbour(0).isCrossPath()) {
			direction = 0;
			return cell.getNeighbour(0);
		}else if (cell.isStart() && cell.getNeighbour(1) != null && !cell.getNeighbour(1).isBlackHole() && !cell.getNeighbour(1).isMarked() && cell.getNeighbour(1).isCrossPath()) {
			direction = 1;
			return cell.getNeighbour(1);
		}else if (cell.isStart() && cell.getNeighbour(2) != null && !cell.getNeighbour(2).isBlackHole() && !cell.getNeighbour(2).isMarked() && cell.getNeighbour(2).isCrossPath()) {
			direction = 2;
			return cell.getNeighbour(2);
		}else if (cell.isStart() && cell.getNeighbour(3) != null && !cell.getNeighbour(3).isBlackHole() && !cell.getNeighbour(3).isMarked() && cell.getNeighbour(3).isCrossPath()) {
			direction = 3;
			return cell.getNeighbour(3);
		} // If there is no target or cross path then it chooses the path with the lower index.
		else if (cell.isStart() && cell.getNeighbour(0)!=null && !cell.getNeighbour(0).isBlackHole() && !cell.getNeighbour(0).isMarked() && cell.getNeighbour(0).isVerticalPath()) {
			direction = 0;
			return cell.getNeighbour(0);
		}else if (cell.isStart() && cell.getNeighbour(1)!=null && !cell.getNeighbour(1).isBlackHole() && !cell.getNeighbour(1).isMarked() && cell.getNeighbour(1).isHorizontalPath()) {
			direction = 1;
			return cell.getNeighbour(1);
		}else if (cell.isStart() && cell.getNeighbour(2)!=null && !cell.getNeighbour(2).isBlackHole() && !cell.getNeighbour(2).isMarked() && cell.getNeighbour(2).isVerticalPath()) {
			direction = 2;
			return cell.getNeighbour(2);
		}else if (cell.isStart() && cell.getNeighbour(3)!=null && !cell.getNeighbour(3).isBlackHole() && !cell.getNeighbour(3).isMarked() && cell.getNeighbour(3).isHorizontalPath()) {
			direction = 3;
			return cell.getNeighbour(3);
		} // After cell, we consider that we move forward unless there is a wall or marked or null path and increment the inertia.
		else if (direction == 0 && cell.getNeighbour(0)!=null && !cell.getNeighbour(0).isMarked() && !cell.getNeighbour(0).isBlackHole() && !cell.getNeighbour(0).isHorizontalPath()) {
			direction = 0;
			inertia++;
			return cell.getNeighbour(0);
		}
		else if (direction == 1 && cell.getNeighbour(1)!=null && !cell.getNeighbour(1).isMarked() && !cell.getNeighbour(1).isBlackHole() && !cell.getNeighbour(1).isVerticalPath()) {
			direction = 1;
			inertia++;
			return cell.getNeighbour(1);
		}else if (direction == 2 && cell.getNeighbour(2)!=null && !cell.getNeighbour(2).isMarked() && !cell.getNeighbour(2).isBlackHole() && !cell.getNeighbour(2).isHorizontalPath()) {
			direction = 2;
			inertia++;
			return cell.getNeighbour(2);
		}else if (direction == 3 && cell.getNeighbour(3)!=null && !cell.getNeighbour(3).isMarked() && !cell.getNeighbour(3).isBlackHole() && !cell.getNeighbour(3).isVerticalPath()) {
			direction = 3;
			inertia++;
			return cell.getNeighbour(3);
			// When Cupid faces a blockage while moving forward to East then it attemps to turn. 
		}else if (cell.isCrossPath() && direction == 1 && inertia < 3 && (cell.getNeighbour(1) == null || cell.getNeighbour(1).isBlackHole() || cell.getNeighbour(1).isMarked()||cell.getNeighbour(1).isVerticalPath())) {
			for (int i=0;i<4;i++) {
				if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isTarget()){
					return cell.getNeighbour(i);
				}else if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isCrossPath()){
					direction = i;
					return cell.getNeighbour(i);
				}else if (i!=1 && i!= 3 && cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isVerticalPath()){
					direction = i;
					return cell.getNeighbour(i);
				}
			}			
			// When Cupid faces a blockage while moving forward to North then it attemps to turn. 
		}else if (cell.isCrossPath() && direction == 0 && inertia < 3 &&(cell.getNeighbour(0) == null || cell.getNeighbour(0).isBlackHole() || cell.getNeighbour(0).isMarked() || cell.getNeighbour(0).isHorizontalPath())) {
			for (int i=0;i<4;i++) {
				if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isTarget()){
					return cell.getNeighbour(i);
				}else if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isCrossPath()){
					direction = i;
					return cell.getNeighbour(i);
				}else if (i!=0 && i!=2 && cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isHorizontalPath()){
					direction = i;
					return cell.getNeighbour(i);
				}
			}			
			// When Cupid faces a blockage while moving forward to South then it attemps to turn. 
		}else if (cell.isCrossPath() && direction == 2 && inertia < 3 &&  (cell.getNeighbour(2) == null || cell.getNeighbour(2).isBlackHole() || cell.getNeighbour(2).isMarked() || cell.getNeighbour(2).isHorizontalPath())) {
			for (int i=0;i<4;i++) {
				if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isTarget()){
					return cell.getNeighbour(i);
				}else if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isCrossPath()){
					direction = i;
					return cell.getNeighbour(i);
				}else if (i!=0 && i!=2 && cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isHorizontalPath()){
					direction = i;
					return cell.getNeighbour(i);
				}
			}			
			// When Cupid faces a blockage while moving forward to West then it attemps to turn. 
		}else if (cell.isCrossPath() && direction == 3 && inertia < 3 && (cell.getNeighbour(3) == null || cell.getNeighbour(3).isBlackHole() || cell.getNeighbour(3).isMarked() || cell.getNeighbour(3).isVerticalPath())) {
			for (int i=0;i<4;i++) {
				if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isTarget()){
					return cell.getNeighbour(i);
				}else if (cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isCrossPath()){
					direction = i;
					return cell.getNeighbour(i);
				}else if (i!=1 && i!=3 && cell.getNeighbour(i)!=null && !cell.getNeighbour(i).isBlackHole() && !cell.getNeighbour(i).isMarked() && cell.getNeighbour(i).isVerticalPath()){
					direction = i;
					return cell.getNeighbour(i);
				}
			}		
		}	
		return null;   // returns null if there is no available path.
	}
}