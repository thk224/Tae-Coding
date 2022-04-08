//Tae Kim (tk2686)
import java.util.Scanner;

public class LastDigitOfSum {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		
		//get input from the user
		System.out.print("Enter the first positive integer: ");
		int n1 = userInput.nextInt();
		
		System.out.print("Enter the second positive integer: ");
		int n2 = userInput.nextInt();
		
		//print the result using the lastDigitSum method
		System.out.println("The last digit of the sum of the two numbers is " + lastDigitSum(n1, n2) + ".");

	}

	public static int lastDigitSum(int n1, int n2) {
		
		int sum = n1 + n2; //add two positive integers
		
		while (sum >= 10) { //divide until the number is a single digit
			sum %= 10; //get remainder
		}
		
		return sum;
	
	}
}
