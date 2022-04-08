import java.util.Scanner; //import scanner

public class hw1_p3{
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in); //create scanner object
		System.out.print("Enter the first integer: ");
		int num1 = userInput.nextInt(); //receive the first integer
    
		System.out.print("Enter the second integer: ");
		int num2 = userInput.nextInt(); //receive the second integer
		
		//print out the results
		System.out.println("---------------------------");
		System.out.println("Result");
		System.out.println("Addition: " + (num1 + num2));
		System.out.println("Subtraction: " + (num1 - num2));
		System.out.println("Multiplication: " + (num1 * num2));
		System.out.println("Division: " + ((double)num1 / num2)); //convert num1 to double
		System.out.println("Quotient: " + (num1 / num2));
		System.out.println("Remainder: " + (num1 % num2));
	}
}