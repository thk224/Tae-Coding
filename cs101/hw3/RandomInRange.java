//Tae Kim (tk2686)
import java.util.Scanner;

public class RandomInRange {

	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		
		//get input from the user
		System.out.print("Enter the first integer: ");
		int n1 = userInput.nextInt();
		
		System.out.print("Enter the second integer: ");
		int n2 = userInput.nextInt();
		
		//print out result
		randomInRange(n1, n2);
		
	}

	public static void randomInRange(int a, int b) {
		//declare max and min variables
		int max;
		int min;
		
		//compare a and b to decide which is max or min
		if (a > b) {
			max = a;
			min = b;
		} else { //if a == b or a < b
			max = b;
			min = a;
		}
		
		//create random number including max and min
		int randNum = (int)(Math.random() * (max - min + 1) + min);
		
		System.out.println("The random number from range of " + min + " to " + max + " is: " + randNum + ".");
	}
}
