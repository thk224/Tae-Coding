import java.util.Scanner;

public class NYUPower{
	public static void main(String[] args){
	
	//declare variables
	int result = 1; //since n^0 is 1
	String outPut = ""; //for printing results
	
	//get input from the user
	Scanner userInput = new Scanner(System.in);
	System.out.print("Enter the base: ");
	int base = userInput.nextInt();

	System.out.print("Enter the exponent: ");
	int exponent = userInput.nextInt();
	
	//for loop to compute the result
	for(int i = 1; i <= exponent; i++){

		//formatting the final print line
		if (i < exponent){
			outPut += base + " x ";
		} else if (i == exponent){
			//for the last element
		
			outPut += base;
		}
		result *= base;
	}
	
	
	System.out.println(outPut + " = " + result);
	}
}