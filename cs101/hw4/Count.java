//Tae Kim (tk2686)
import java.util.Scanner;

public class Count {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		//creat a counter list length of 100
		int[] list = new int[100];
		
		//set count for each number to 0
		for (int i = 0; i < 100; i++) {
			list[i] = 0;
		}
		
		//receive input
		System.out.print("Enter integers between 1 and 100 (0 to terminate): ");
		
		//increase counter when number occurs
		while (true) {
			int num = input.nextInt();
			
			//for 0 and invalid inputs
			if (num == 0) {
				break;
			} else if (num < 0 || num > 100) {
				continue;
			}
			
			list[num]++;
			
		}
		
		//print out count results
		for (int i = 0; i < 100; i ++) {
			if (list[i] != 0) {
				if (list[i] == 1) {
					System.out.println(i + " occurs 1 time");
				} else {
					System.out.println(i + " occurs " + list[i] + " times");
				}
			}
			
		}
		
			
	}

}
