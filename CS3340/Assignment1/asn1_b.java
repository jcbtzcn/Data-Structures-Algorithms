/**
 * @author Yakup Tezcan
 *Student ID: 251190973
 */
public class asn1_b {
	//We determine a size number to hold the 500th Lucas number which have more than 100 digits. 
	private  static int SIZE = 120;
	//Main method to get the number by helper methods.
	public static int[] lucas(int n) {
		//L(n) = L(n-1) + L(n-2)
		//L(n)
		int[] lucn = new int[SIZE];
		//L(n-1)
		int[] lucn1 = new int[SIZE];
		//L1 is 1.
		lucn1[SIZE-1] = 1;
     	//L(n-2)
		int[] lucn2 = new int[SIZE];
		//L0 is 2.
		lucn2[SIZE-1] = 2;
		//After creating new arrays for each number that we will use to calculate the Lucas number,
		//we proceed to base cases.
		//base cases only change the result. In this case, it is lucn.
		if(n==0){
			lucn[SIZE-1] = 2;
		}
		else if(n==1) {
			lucn[SIZE-1] = 1;
		}//After base cases, we proceed to calculate the recursion relation.(not exactly a recursion though.)
		else {
			// From 2 to n, we add all the numbers to calculate the result.
			for (int i=2;i<=n;i++) {
				//largeAddition function helps us to calculate two numbers that have more than 20 digits.
				//With long type, we cannot store more than 20 digits.
				lucn = largeAddition(lucn1, lucn2);
				//Then, we assign the numbers by incrementing their lucas numbers. (First lucas number becomes the second, then third,i.e.)
				lucn2 = lucn1;
				lucn1 = lucn;
			}
		}
		return lucn2;
	}
	public static int[] largeAddition(int[] num1, int[] num2) {
		//a new array sum to store the result of addition.
		int[] totalSum = new int[SIZE];
		//to check the carry from adding to digits in the same index of arrays.
		boolean carryOfTwoDigits=true;
		//we decrement i since, we stored the first digit in the last index of the arrays.
		int indexOfSum;
		for(int i=SIZE;i>0;i--) {
			// if there is no carry, then we don't need to add it.
			indexOfSum=0;
			if(!carryOfTwoDigits){
				//When carry is false, which we assigned below if the sum of digits are larger than 9, 
				//we need index 1 because we need to add it to the next sum of the next digits in the next index position.
				indexOfSum = 1;
				carryOfTwoDigits = true;
            }
			//adding two digits in the same index.
			indexOfSum += num1[i-1] + num2[i-1];
			//if the addition of digits are more than 9, then we have a carry to add to the next addition.
			//we control the carry here, so we can use it in the next run.
			if(indexOfSum > 9)
            {
				carryOfTwoDigits = false;
            }
			// When the addition of two digits at index i is larger than 9, we put the the carry (1) to i, and store the other part in i-1,
			totalSum[i-1] = indexOfSum % 10;
		}
		return totalSum;
	}
	// We have many zeros at the indexes that we did not use.
	public static void printLucas(int[] num) {
		boolean zeroes = true;
		//We check the first index numbers, until we find a digit at index i
		for(int i=0; i<SIZE;i++) {
			while(zeroes) {
				i++;
				//we stop at index i, when we find a digit.
				if(num[i] != 0) {
					zeroes=false;
				}
			}//the program will not run the if condition, if there are still zeroes before the first digit in the array. 
			if(i<SIZE){
				 System.out.print(num[i]);
			 }
		}
	}
    public static void main(String[] args){
        // Calculate and print L(0) to L(500)
        for(int i = 0; i <= 25; i++){
        	System.out.print("L(" + i*20 + ") = " );
        	printLucas(lucas(i*20));
        	System.out.println(); 	
        }
    }
}