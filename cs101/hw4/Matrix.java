//Tae Kim (tk2686)
import java.util.Scanner;

public class Matrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		//take size of matrix
		System.out.print("Enter n: ");
		int n = input.nextInt();
		
		//create a matrix
		double[][] matrix = new double[n][n];
		
		System.out.println("Enter a " + n + " x " + n + " matrix row by row:");
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = input.nextDouble();
			}
		}
		
		//print result
		System.out.println("Sum of the elements in the antidiagonal is " + sumAntidiagonal(matrix));
		
	}

	public static double sumAntidiagonal(double[][] matrix) {
		double sum = 0;
		
		//calculate anti-diagonal sum
		int j = matrix.length - 1;
		for (int i = 0; i < matrix.length; i++) {
			sum += matrix[i][j];
			j--;
		}
		
		return sum;
	}
	
}
