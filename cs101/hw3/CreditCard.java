//Tae Kim (tk2686)
import java.util.Scanner;

public class CreditCard {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		
		//get input from the user
		System.out.print("Enter a credit card number as a long integer: ");
		long cardNum = userInput.nextLong();
		
		//check the validity of the car number, then print the result.
		if (isValid(cardNum)) {
			System.out.println(cardNum + " is valid.");
		} else {
			System.out.println(cardNum + " is invalid.");
		}
		
		//System.out.println();
		//System.out.println(getPrefix(4388576018410707L, 19));
		//System.out.println(getSize(4388576018410707L));
		//System.out.println(prefixMatched(4388576018410707L, 438857601));
		//System.out.println(sumOfOddPlace(4388576018402626L));
		//System.out.println(getDigit(12));
		//System.out.println(sumOfDoubleEvenPlace(4388576018402626L));
		//System.out.println(isValid(4388576018410707L));
		
		
	}
	
	/** Return true if the card number is valid */
	public static boolean isValid(long number) {
		
		//check the size
		if (13 <= getSize(number) && getSize(number) <= 16) {
			//check the prefix
			if (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37)
					|| prefixMatched(number, 6)) {
				//check if the sum is divisible by 10
				if ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0) {
					return true;
				}
			}
		}
		
		//if the number doesn't satisfy one of the if statement, return false
		return false;
	}
	
	/** Get the result from Step 2 */
	public static int sumOfDoubleEvenPlace(long number) {
		int sum = 0;
		int digit;
		
		number /= 10; //eliminate the first digit
		
		while(number != 0) {
			digit = (int)(number % 10); //get each digits
			sum += getDigit(digit * 2); //calculate the value by using getDigit method
			number /= 100; //eliminate odd digits
		}
		
		return sum;
	}
	
	/** Return this number if it is a single digit, otherwise,
	 * return the sum of the two digits */
	public static int getDigit(int number) {
		
		if (number < 10) { //if the number is a single digit
			return number;
		} else {
			return number / 10 + number % 10; //manipulating integer division
		}
	}
	
	/** Return sum of odd-place digits in number */
	public static int sumOfOddPlace(long number) {
		//declare sum variable
		int sum = 0;
		
		//divide by 100 to eliminate even digits
		while(number != 0) {
			sum += number % 10;
			number /= 100;
		}
		
		return sum;
	}
	
	/** Return true if the digit d is a prefix for number */
	public static boolean prefixMatched(long number, int d) {
		int size = getSize(d); // get size of d
		long prefix = getPrefix(number, size); //get prefix of the number
		
		//compare the prefix
		if (prefix == d) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Return the number of digits in d */
	public static int getSize(long d) {
		int size = 0; //counter variable
		
		//divide until the number is 0 (since it is an integer division)
		while (d != 0) {
			d /= 10;
			size += 1;
		}
		
		return size;
	}
	
	/** Return the first k number of digits from number. If the
	 * number of digits in number is less than k, return number. */
	public static long getPrefix(long number, int k) {
		if (number < k) {
			return number;
		}
		
		//set the limit
		long lim = (long)Math.pow(10, k);
		
		//divide until the number reaches the limit
		while (number >= lim) {
			number /= 10;
		}
		
		return number;
	}

}
