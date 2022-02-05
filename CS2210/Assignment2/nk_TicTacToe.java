/*
 * This class implements all the methods needed by algorithm computerPlay in Play_nk_TTT class.
 */
public class nk_TicTacToe {
	//2D char array to create an empty gameBoard.
	char[][] gameBoard;
	//board size nxn
	int board_size;
	//number of lines needed to win the game.
	int inline;
	//Constructor for the class.
	//first parameter is the size of the board.
	//second parameter is the number of symbols in-line needed to win the game.
	//third is the maximum level of the game tree.
	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		//initializes the game board size with board_size.
		gameBoard = new char[board_size][board_size];
		//initializes the board size and inline with inputs.
		this.board_size=board_size;
		this.inline=inline;
	}
	//Create a dictionary of selected size and returns it.
	public Dictionary createDictionary() {
		Dictionary dict = new Dictionary(7753);
		return dict;
	}
	//This method checks whether the string(converted from gameBoard array) is in the dictionary or not.
    public int repeatedConfig(Dictionary configurations) {
    	//Stores the characters from the gameBoard in a string.
    	String str="";
    	for(int i=0;i<board_size;i++) {
   		 for(int j=0;j<board_size;j++) {
   			 str+=gameBoard[i][j];
   		     }
   	    }
    	//checks the string with the help of get method inside the Dictionary class.
    	return configurations.get(str);
    }
    //This method inserts the string and score inside the dictionary.
    public void insertConfig(Dictionary configurations, int score) {
    	//Stores the characters from the gameBoard in a string.
    	String str="";
    	for(int i=0;i<board_size;i++) {
   		 for(int j=0;j<board_size;j++) {
   			 str+=gameBoard[i][j];
   		     }
   	    }
    	//Creates a new record to insert it to the dictionary.
    	//After it is created then we insert the new record to the dictionary.
    	Record newRec = new Record(str, score);
    	configurations.insert(newRec);
    }
    //This method stores the play(symbol) in the given positions.
    public void storePlay(int row, int col, char symbol) {
    	gameBoard[row][col]= symbol;
    }
    //This method checks whether the given positions of gameBoard is empty or not. Returns true if it is empty.
    public boolean squareIsEmpty(int row, int col) {
    	if(gameBoard[row][col] != 'X' && gameBoard[row][col] != 'O') {
    		return true;
    	}else {
    		return false;
    	}
    }
    //This method checks if the given symbol wins the game or not.
    public boolean wins(char symbol) {
    	//k is the inline which is the needed number of symbols to win the game in the same row, column or diagonal.
    	int k=inline;
    	//Until horizontal check, I handled diagonal matches here.
    	//Since the inline can change(when inline is not equal to board_size), I had to check diagonal more carefully for all gameBoards.
    	//I drew some shapes to show what kind of checks loops do.
    	int ct=0;
    	/*
    	 * When k=3 and board_size is 4.
    	 * Positions below are only for examples.
    	 * Check starts at left upper side(1,0) and ends at right down side(board_size, board_size-1)
    	 *_X_X
    	 *X_O_
    	 *_X_O
    	 *__X_ 
    	 */
    	for(int first=1;first<board_size;first++) {
    		ct=0;
    		for(int sec=first, thi=0;sec<board_size;thi++, sec++) {
    			if(gameBoard[sec][thi] == symbol) {
    				ct++;
    			}
    			if(gameBoard[sec][thi] != symbol)
    				ct=0;
    			if(ct==k)
    				return true;
    		}
    	}
    	/*
    	 * When k=3 and board)size is 4.
    	 * Positions below are only for examples.
    	 * Check starts at right upper side(1,board_size) and ends at left lower side(board_size,1).
    	 *_X_X
    	 *___X
    	 *__X_
    	 *_X_O 
    	 */
    	for(int first=1;first<board_size;first++) {
    		ct=0;
    		for(int sec=first, thi=board_size-1;sec<board_size;thi--, sec++) {
    			if(gameBoard[sec][thi] == symbol) {
    				ct++;
    			}
    			if(gameBoard[sec][thi] != symbol)
    				ct=0;
    			if(ct==k)
    				return true;
    		}
    	}
    	/*
    	 * When k=3 and board)size is 4.
    	 * Positions below are only for examples.
    	 * Check starts at left upper side(0,1) and ends at right lower side(board_size-1, board_size).
    	 *_X_X
    	 *__XO
    	 *___X
    	 *_X_O 
    	 */
    	for(int first=1;first<board_size;first++) {
    		ct=0;
    		for(int sec=0, thi=first;thi<board_size;thi++, sec++) {
    			if(gameBoard[sec][thi] == symbol) {
    				ct++;
    			}
    			if(gameBoard[sec][thi] != symbol)
    				ct=0;
    			if(ct==k)
    				return true;
    		}
    	}
    	/*
    	 * When k=3 and board)size is 4.
    	 * Positions below are only for examples.
    	 * Check starts at right upper side(0,board_size-1) and ends at left lower side(board_size-1, 0).
    	 *__X_
    	 *_X_O
    	 *X___
    	 *_X_O 
    	 */
    	for(int first=board_size-2;first>0;first--) {
    		ct=0;
    		for(int sec=0, thi=first;thi >=0;thi--, sec++) {
    			if(gameBoard[sec][thi] == symbol) {
    				ct++;
    			}
    			if(gameBoard[sec][thi] != symbol)
    				ct=0;
    			if(ct==k)
    				return true;
    		}
    	}
    	//horizontal check for the win.
    	int count=0;
    	for(int i=0;i<board_size;i++) {
    		count=0;
    		for(int j=0;j<board_size;j++) {
    			if(gameBoard[i][j] == symbol) {
    				count++;
    			}
    			if(gameBoard[i][j] != symbol) {
    				count=0;
    			}
    			if(count==k)
    				return true;
    		}
    	}
    	//vertical check for the win.
    	int c=0;
    	for(int s=0;s<board_size;s++) {
    		c=0;
    		for(int t=0;t<board_size;t++) {
    			if(gameBoard[t][s] == symbol) {
    				c++;
    			}
    			if(gameBoard[t][s]!=symbol)
    				c=0;
    			if(c==k)
    				return true;
    		}
    	}
    	// basic diagonal check
    	//from left upper side to right lower side when inline is equal to board_size.
    	int countd=0;
    	for(int x=0;x<board_size;x++) {
    		if(gameBoard[x][x] == symbol) {
    			countd++;
    		}
    		if(gameBoard[x][x] != symbol) {
    			countd=0;
    		}
    		if(countd==k)
        		return true;
    	}
    	int countn=0;
    	//from right upper side to left lower side when inline is equal to board_size.
    	for(int y=0;y<board_size;y++){
    		if(gameBoard[y][board_size-1-y] == symbol) {
    			countn++;
    		}
    		if(gameBoard[y][board_size-1-y] != symbol) {
    			countn=0;
    		}
    		if(countn==k)
        		return true;
    	}
    	
    	return false;
    }
    //This method returns true if there is a draw.
    public boolean isDraw() {
    	int count=0;
    	int size=board_size*board_size;
    	//Loop checks if there is more room in the gameBoard.
    	for(int i=0;i<board_size;i++){
    		for(int j=0;j<board_size;j++){
    			if(gameBoard[i][j] == 'X' || gameBoard[i][j] == 'O') {
    				count++;
    		   }
    		}
    	}
    	//If condition to check the size and win situations.
    	if(size==count && !wins('X') && !wins('O'))
    		return true;
    	//returns false otherwise.
    	return false; 	
    }
    //Check win or draw situations.
    public int evalBoard() {
    	//returns 3 if computer wins.
    	if(wins('O')) {
    		return 3;
    	}//returns 0 if human wins.
    	else if(wins('X')) {
    		return 0;
    	//returns 2 if it is a draw.
    	}else if(isDraw()) {
    		return 2;
    	//returns 1 if there is an undecided situation.
    	}else {
    		return 1;
    	}
    }
}