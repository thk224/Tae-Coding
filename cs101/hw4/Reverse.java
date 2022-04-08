//Tae Kim (tk2686)
import java.util.Scanner;

public class Reverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size;
		Scanner input = new Scanner(System.in);
		
		while (true) {
			
			//ask for list size
			System.out.print("How many elements (0 to exit)? ");
			size = input.nextInt();
			
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
			
			//print out the elements in reversed order
			String result = "The reversed list is ";
			for (int j = size - 1; j >= 0; j--) {
				if (j > 0) {
					result += list[j] + " ";
				} else {
					result += list[j];
				}
			}
			
			System.out.println(result);
			
			
		}
	}

}
