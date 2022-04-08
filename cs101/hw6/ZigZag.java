//Tae Kim (tk2686)
import java.util.Arrays;

public class ZigZag {

	public static void main(String[] args) {
		//test cases
		int[][] array1 = new int[3][3];
		fillZigZag(array1);
		for (int[] row: array1)
			System.out.println(Arrays.toString(row));
		
		int[][] array2 = new int[5][5];
		fillZigZag(array2);
		for (int[] row: array2)
			System.out.println(Arrays.toString(row));
		
	}
	
	public static void fillZigZag(int[][] arr) {
		int count = 0; //counter variable which is the number that goes into array
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) { 
				for (int j = 0; j < arr.length; j++) {
					arr[j][i] = ++count; //for 'i'the element of each columns
				}
			} else { //backward
				for (int j = arr.length - 1; j >= 0; j--) {
					arr[j][i] = ++count; //for 'i'the element of each columns
				}
			}
		}
		
	}
	
}
