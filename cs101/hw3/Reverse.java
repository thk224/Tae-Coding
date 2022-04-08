//Tae Kim (tk2686)
import java.util.Scanner;

public class Reverse {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		
		//get input from the user
		System.out.print("Enter a positive integer: ");
		int num = userInput.nextInt();
		
		//print the result
		System.out.println("The reversed number of " + num + " is: " + reverse(num) + ".");
	}
	
	public static int reverse(int num) {
		
		int revNum = 0;
		
		while (num > 0) {
			revNum *= 10;
			revNum += num % 10;
			
			num /= 10;
		}
		
		return revNum;
	}

}
