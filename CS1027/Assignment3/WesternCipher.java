/**
 * This class encodes and decodes strings.
 * @author CS1027 Assignment 3 Yakup Tezcan
 */
// import scanner.
import java.util.Scanner;
public class WesternCipher {
	// creates a circular array queue objects for encoding and decoding takes character interface.
	CircularArrayQueue<Character> encodingQueue;
	CircularArrayQueue<Character> decodingQueue;
	/**
	 * First constructor for the class.
	 */
	public WesternCipher() {
		// Assigns the capacity of both queue with 10.
		encodingQueue = new CircularArrayQueue<Character>(10);
		decodingQueue = new CircularArrayQueue<Character>(10);
	}
	/**
	 * Second parameter for the class.
	 * @param newCapacity takes parameter for the capacity of the queue arrays.
	 */
	public WesternCipher(int newCapacity) {
		encodingQueue = new CircularArrayQueue<Character>(newCapacity);
		decodingQueue = new CircularArrayQueue<Character>(newCapacity);
	}
	/**
	 * This class takes a parameter as input and encodes it with the given rules.
	 * @param input. Takes the string and encodes it.
	 * @return output. Returns the encoded string.
	 */
	public String encode(String input) {
		// English alphabet letters.
		char[] charArray= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		// Creates the output String.
		String output="";
		//Splits the string into characters.
		char[] cArray=input.toCharArray();
		//enqueues all the characters to the queue array.
		for(int i=0;i<cArray.length;i++) {
			encodingQueue.enqueue(cArray[i]);
		}
		//tracks the previous element.
		char prev=0;
		for(int i=0;i<cArray.length;i++) {
			// first element in the queue array to remove.
			char cur=encodingQueue.first();
			//Checks for AEIOUY 
			if(cur=='A'||cur=='E'||cur=='I'||cur=='O'||cur=='U'||cur=='Y') { 
				//cases for if previous was converted to a number.
				//dequeues the elements after converting them.
				if((prev=='A' || prev=='E' || prev=='I' || prev=='O' || prev=='U' || prev=='Y') && cur=='A') {    
					output+='3';
					prev=encodingQueue.dequeue();
				}else if((prev=='A' || prev=='E' || prev=='I' || prev=='O' || prev=='U' || prev=='Y') && (cur=='E')) {
					output+='4';
					prev=encodingQueue.dequeue();
				}else if((prev=='A' || prev=='E' || prev=='I' || prev=='O' || prev=='U' || prev=='Y') && (cur=='I')) {
					output+='5';
					prev=encodingQueue.dequeue();
				}else if((prev=='A' || prev=='E' || prev=='I' || prev=='O' || prev=='U' || prev=='Y') && (cur=='O')) {
					output+='6';
					prev=encodingQueue.dequeue();
				}else if((prev=='A' || prev=='E' || prev=='I' || prev=='O' || prev=='U' || prev=='Y') && (cur=='U')) {
					output+='1';
					prev=encodingQueue.dequeue();
				}else if((prev=='A' || prev=='E' || prev=='I' || prev=='O' || prev=='U' || prev=='Y') && (cur=='Y')) {
					output+='2';
					prev=encodingQueue.dequeue();
				// cases if previous is not converted to a number.
				//dequeues the elements after converting them.
				}else if(prev!='A' && prev!='E' && prev!='I' && prev!='O' && prev!='U' && prev!='Y' && cur=='A') {    
					output+='1';
					prev=encodingQueue.dequeue();
				}else if(prev!='A' && prev!='E' && prev!='I' && prev!='O' && prev!='U' && prev!='Y' && cur=='E') {
					output+='2';
					prev=encodingQueue.dequeue();
				}else if(prev!='A' && prev!='E' && prev!='I' && prev!='O' && prev!='U' && prev!='Y' && cur=='I') {
					output+='3';
					prev=encodingQueue.dequeue();
				}else if(prev!='A' && prev!='E' && prev!='I' && prev!='O' && prev!='U' && prev!='Y' && cur=='O') {
					output+='4';
					prev=encodingQueue.dequeue();
				}else if(prev!='A' && prev!='E' && prev!='I' && prev!='O' && prev!='U' && prev!='Y' && cur=='U') {
					output+='5';
					prev=encodingQueue.dequeue();
				}else if(prev!='A' && prev!='E' && prev!='I' && prev!='O' && prev!='U' && prev!='Y' && cur=='Y') {
					output+='6';
					prev=encodingQueue.dequeue();
				}
				//converts spaces to spaces again then removing the space queue array element from the queue.
			}else if(cur== ' ') {
				output+= ' ';
				prev=encodingQueue.dequeue();
				//if the letters are not AEIOUY and space then following options are to implemented.
			}else {
				int index=0;
				//finds the index number of current element in the queue.
				for (int j=0;j<charArray.length;j++) {
					if(cur==charArray[j]) {
						index=j;
					}
				}
				//cases if previous element is not converted to a number.
				if(prev!='A' && prev!='E' && prev!='I' && prev!='O' && prev!='U' && prev!='Y') {
					//if the new index number is out of range then following if condition is applied.
					if((index+5)+i*2>=26) {
						output+=charArray[(index+5)+i*2-26];
						prev=encodingQueue.dequeue();
					// if the new index number is in the range.
					}else {
						output+=charArray[(index+5)+i*2];
						prev=encodingQueue.dequeue();
					}
				//cases if the previous element is converted to a number.
				}else if(prev=='A' || prev=='E' || prev=='I' || prev=='O' || prev=='U' || prev=='Y') {
					int num=0;
					//splits the output until the current element to its characters.
					char[] newOne=output.toCharArray();
					//checks the number that previous element is converted to backtrack.
					for (int s=0;s<newOne.length;s++) {
						if (s+1==newOne.length && newOne[s]=='1') {
							num=1;
						}else if (s+1==newOne.length && newOne[s]=='2') {
							num=2;
						}else if (s+1==newOne.length && newOne[s]=='3') {
							num=3;
						}else if (s+1==newOne.length && newOne[s]=='4') {
							num=4;
						}else if (s+1==newOne.length && newOne[s]=='5') {
							num=5;
						}else if (s+1==newOne.length && newOne[s]=='6') {
							num=6;
						}
					}
					//if the new index number is out of range then following if condition is applied.
					if((index+5)+(i*2)-(2*num)>=26) {
						output+=charArray[((index+5)+(i*2)-2*num)%charArray.length];
						prev=encodingQueue.dequeue();
					// if the new index number is in the range.
					}else {
						output+=charArray[(index+5)+(i*2)-2*num];
						prev=encodingQueue.dequeue();
					}
				}
			}
		}	 
		return output;
	}
	/**
	 * This class takes a parameter as input and decodes it with the given rules.
	 * @param input. Takes the string that will be decoded.
	 * @return output of the decoded string.
	 */
	public String decode(String input) {
		// English alphabet letters.
		char[] charArray= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		// Creates the output String.
		String output="";
		//Splits the string into characters.
		char[] cArray=input.toCharArray();
		for(int i=0;i<cArray.length;i++) {
			decodingQueue.enqueue(cArray[i]);
		}
		//tracks the previous element.
		char prev=0;
		for(int i=0;i<cArray.length;i++) {
			// first element in the queue array to remove.
			char cur=decodingQueue.first();
			//Checks for AEIOUY 
			if(cur=='1'||cur=='2'||cur=='3'||cur=='4'||cur=='5'||cur=='6') {
				//cases for if previous was converted to a number.
				//dequeues the elements after converting them.
				if((prev=='1' || prev=='2' || prev=='3' || prev=='4' || prev=='5' || prev=='6') && (cur=='1')) {  
					output+='A';
					prev=decodingQueue.dequeue();
				}else if((prev=='1' || prev=='2' || prev=='3' || prev=='4' || prev=='5' || prev=='6') && (cur=='2')) {
					output+='Y';
					prev=decodingQueue.dequeue();
				}else if((prev=='1' || prev=='2' || prev=='3' || prev=='4' || prev=='5' || prev=='6') && (cur=='3')) {
					output+='A';
					prev=decodingQueue.dequeue();
				}else if((prev=='1' || prev=='2' || prev=='3' || prev=='4' || prev=='5' || prev=='6') && (cur=='4')) {
					output+='E';
					prev=decodingQueue.dequeue();
				}else if((prev=='1' || prev=='2' || prev=='3' || prev=='4' || prev=='5' || prev=='6') && (cur=='5')) {
					output+='O';
					prev=decodingQueue.dequeue();
				}else if((prev=='1' || prev=='2' || prev=='3' || prev=='4' || prev=='5' || prev=='6') && (cur=='6')) {
					output+='O';
					prev=decodingQueue.dequeue();
				// cases if previous is not converted to a number.
				//dequeues the elements after converting them.
				}else if(prev!='1' && prev!='2' && prev!='3' && prev!='4' && prev!='5' && prev!='6' && cur=='1') {
					output+='A';
					prev=decodingQueue.dequeue();
				}else if(prev!='1' && prev!='2' && prev!='3' && prev!='4' && prev!='5' && prev!='6' && cur=='2') {
					output+='E';
					prev=decodingQueue.dequeue();
				}else if(prev!='1' && prev!='2' && prev!='3' && prev!='4' && prev!='5' && prev!='6' && cur=='3') {
					output+='I';
					prev=decodingQueue.dequeue();
				}else if(prev!='1' && prev!='2' && prev!='3' && prev!='4' && prev!='5' && prev!='6' && cur=='4') {
					output+='O';
					prev=decodingQueue.dequeue();
				}else if(prev!='1' && prev!='2' && prev!='3' && prev!='4' && prev!='5' && prev!='6' && cur=='5') {
					output+='U';
					prev=decodingQueue.dequeue();
				}else if(prev!='1' && prev!='2' && prev!='3' && prev!='4' && prev!='5' && prev!='6' && cur=='6') {
					output+='Y';
					prev=decodingQueue.dequeue();
				}
			//converts spaces to spaces again then removing the space queue array element from the queue.
			}else if(cur== ' ') {
				output+= ' ';
				prev=decodingQueue.dequeue();
			//if the letters are not AEIOUY and space then following options are to implemented.
			}else {
				int index=0;
				//finds the index number of current element in the queue.
				for (int j=0;j<charArray.length;j++) {
					if(cur==charArray[j]) {
						index=j;
					}
				}
				//cases if previous element is not converted to a number
				if(prev!='1' && prev!='2' && prev!='3' && prev!='4' && prev!='5' && prev!='6') {
					//if the new index number is out of range then following if condition is applied.
					if((index-5)-i*2<0) {
						output+=charArray[((index-5)-i*2)+charArray.length];
						prev=decodingQueue.dequeue();
					// if the new index number is in the range.
					}else {
						output+=charArray[(index-5)-i*2];
						prev=decodingQueue.dequeue();
					}
				}else if(prev=='1' || prev=='2' || prev=='3' || prev=='4' || prev=='5' || prev=='6') {
					//checks the number that previous element is converted to go forward.
					int num=0;
					if (prev=='1') {
						num=1;
					}else if (prev=='2') {
						num=2;
					}else if (prev=='3') {
						num=3;
					}else if (prev=='4') {
						num=4;
					}else if (prev=='5') {
						num=5;
					}else if (prev=='6') {
						num=6;
					}
					//if the new index number is out of range then following if condition is applied.
					if((index-5)-(i*2)+(2*num)<0) {
						output+=charArray[Math.abs((index-5)-(i*2)+2*num+2*charArray.length)%charArray.length];
						prev=decodingQueue.dequeue();
					// if the new index number is in the range.
					}else {
						output+=charArray[(index-5)-(i*2)+2*num];
						prev=decodingQueue.dequeue();
					}
				}
			}
		}	
		return output;
	}
	/**
	 * Main method. Takes user inputs to encode or decode strings.
	 * @param args
	 */
	public static void main(String[] args) {
		// message to user for the options.
		String msg = "Do you want to encode(e) or decode(d) a String?(Quit/q to quit)";
		//boolean value isTrue.
		boolean isTrue=true;
		//westernChiper object to call class methods.
		WesternCipher newInput = new WesternCipher();
		//while loop when isTrue is true.
		while(isTrue) {
			//scanner takes user inputs.
			Scanner inputString = new Scanner(System.in);
			//prints message.
			System.out.println(msg);
			String s=inputString.nextLine();
			//if input is encode or e.
			if(s.equals("encode") || s.equals("e")) {
				//asks for the string to encode.
				System.out.println("Enter the string you want to encode.");
				String s4=inputString.nextLine();
				//encodes the string.
				newInput.encode(s4);
				//prints the encoded string.
				System.out.println("Encoded String: " + newInput.encode(s4));
				//asks for another string to encode.
				System.out.println("Do you want to enter another string?(Quit/q to quit)");
				String s2=inputString.nextLine();
				// if input is not quit or q then it encodes the new string.
				if(!s2.equals("Quit") && !s2.equals("q")) {
					System.out.println("Enter the new string you want to encode.");
					String s6=inputString.nextLine();
					newInput.encode(s6);
					//prints the new encoded string.
					System.out.println("Encoded String: " + newInput.encode(s6));
					//after encoding the program quits.
					isTrue=false;
				// if answer is not for an another string, the program quits.
				}else {
					System.out.print("Quiting...");
					isTrue=false;
				}
				// if the input is decode or d, then the prompted string is decoded.
			}else if(s.equals("decode") || s.equals("d")) {
				System.out.println("Enter the string you want to decode.");
				//gets input.
				String s5=inputString.nextLine();
				//decodes the string.
				newInput.decode(s5);
				//prints the decoded string.
				System.out.println("Decoded String: " + newInput.decode(s5));
				//asks for another string to decode.
				System.out.println("Do you want to enter another string?(Quit/q to quit)");
				String s3=inputString.nextLine();
				//if input is not quit or q, then the new string is decoded.
				if(!s3.equals("Quit") && !s3.equals("q")) {
					System.out.println("Enter the new string you want to decode.");
					String s6=inputString.nextLine();
					System.out.println("Decoded String: " + newInput.decode(s6));
					newInput.encode(s6);
					//quit after the new string decoded.
					isTrue=false;
				}else {
					System.out.print("Quiting...");
					isTrue=false;
				}
			// if user inputs quit or q before doing any decoding or encoding, the program quits.
			}else if(s.equals("q") || s.equals("Quit")) {
				System.out.print("Quiting...");
				isTrue=false;
			// if input is invalid then loop continues 
			}else {
				System.out.println("Invalid Input");
				isTrue=true;
			}
		}
	}
}