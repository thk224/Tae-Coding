import java.util.Scanner;

public class hw1_p4{
	public static void main(String[] args) {
		System.out.println("Welcome to BMI Calculator!");

		//Get input from the user - both height and weight
		Scanner userInput = new Scanner(System.in);
		System.out.print("Enter your weight in pounds: ");
		double weightLbs = userInput.nextDouble();
		
		System.out.print("Enter your height in inches: ");
		double heightIn = userInput.nextDouble();
		
		//declare final double variables for unit conversion
		final double lbsToKg = 0.45359237;
		final double inToM = 0.0254;
		
		//convert unit for each variable
		double weightKg = weightLbs * lbsToKg;
		double heightM  = heightIn * inToM;
		
		//calculate bmi
		double bmi = weightKg/(heightM * heightM);
		
		//display bmi
		System.out.println("Your BMI is " + bmi);

	}
}