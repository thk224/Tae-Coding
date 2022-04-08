//Tae Kim (tk2686)
import java.util.Arrays;
import java.util.Scanner;

public class Sorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		while (true) {
			//ask for list size
			System.out.print("How many elements (0 to exit)? ");
			int size = input.nextInt();
			
			//if size is less than or equal to zero, exit
			if (size <= 0) {
				break;
			}
			
			//create a list with the requested size
			int[] list = new int[size];
			
			//add elements to the list
			System.out.print("Enter " + size + " integers: ");
			for (int i = 0; i < size; i++) {
				list[i] = input.nextInt();
			}
			
			//test the "sortness"
			if (isSorted(list)) {
				System.out.println("The list is already sorted");
			} else {
				System.out.println("The list is not sorted");
			}
			
		}
		
		
	}
	
	public static boolean isSorted(int[] list) {
		//create a sorted version of list
		int[] sList = new int[list.length];
		
		for (int i = 0; i < list.length; i++) {
			sList[i] = list[i];
		}
		
		Arrays.sort(sList);
		
		//compare the sorted list and original list
		if (Arrays.equals(sList, list)) {
			return true;
		} else {
			return false;
		}
	}

}
