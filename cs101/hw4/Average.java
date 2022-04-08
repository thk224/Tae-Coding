//Tae Kim (tk2686)
import java.util.Scanner;

public class Average {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		//create int-list and double-list with length of 10
		int[] iList = new int[10];
		double[] dList = new double[10];
		
		//take 10 integer inputs
		System.out.print("Enter 10 integers: ");
		for (int i = 0; i < 10; i++) {
			iList[i] = input.nextInt();
		}
		
		//print average
		System.out.println("The average is " + average(iList));
		
		//take 10 double inputs
		System.out.print("Enter 10 double values: ");
		for (int i = 0; i < 10; i++) {
			dList[i] = input.nextDouble();
		}
		
		//print average
		System.out.println("The average is " + average(dList));
		
	}
	
	public static double average(int[] array) {
		double sum = 0;
		
		//get sum of the integers in array
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		
		//calculate average
		double avg = sum / array.length;
		
		return avg;
	}
	
	public static double average(double[] array) {
		double sum = 0;
		
		//get sum of the doubles in array
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		
		//calculate average
		double avg = sum / array.length;
		
		return avg;
	}

}
