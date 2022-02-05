/**
 * @author Yakup Tezcan
 *Student ID: 251190973
 */
/*
 * L(0) = 2
 * L(5) = 11
 * L(10) = 123 
 * L(15) = 1364 
 * L(20) = 15127 
 * L(25) = 167761 
 * L(30) = 1860498 
 * L(35) = 20633239 
 * L(40) = 228826127 
 * L(45) = 2537720636 
 * L(50) = 28143753123 
 */
public class asn1_a {
    // recursive lucas function
    public static long lucas(long n){
        // Base cases
        if (n == 0)
            return 2;
        if (n == 1)
            return 1;
        // recurrence
        return lucas(n - 1) +
               lucas(n - 2);
    }
    // Main method
    public static void main(String args[]){
    	//for loop to get the required Lucas numbers.
        for(int i=0;i<=10;i++) {
        	System.out.println("L(" + i*5 + ") =" + lucas(i*5));
        }
    }
}