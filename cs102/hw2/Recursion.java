package hw2;

import java.util.Arrays;

public class Recursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		int arr[] = {1, 23435, 339, 6, 4, 5, 678, 399, 12, -10};
		System.out.println(binaryMax(arr, 0, arr.length));
		
		System.out.println(numOfZeros(100));
		
		System.out.println(palindrome("kayak"));
		System.out.println(palindrome("radar"));
		System.out.println(palindrome("dad"));
		System.out.println(palindrome("qwer"));
		*/
	}
	
	//Recursion #1
	public static int binaryMax(int arr[], int i, int n) {
		
		//when array length is 1
		if (n == 1) {
			return arr[0];
			
		}
		
		//pivot
		int mid = n / 2;
		
		//divide array in half
		int left[] = Arrays.copyOfRange(arr, 0, mid);
		int right[] = Arrays.copyOfRange(arr, mid, n);
		
		//find max for each array
		int leftMax = binaryMax(left, 0, mid);
		int rightMax = binaryMax(right, mid, n - mid);
		
		//return the max value comparing the left and right max
		if (leftMax > rightMax) {
			return leftMax;
			
		} else {
			return rightMax;
			
		}

	}
	
	//Recursion #2
	public static int numOfZeros(int n) {
		
		//base case
		if (n == 0) {
			return 0;
			
		} else {
			//if n is divisible by 2
			if (n % 2 == 0) {
				return 1 + numOfZeros(n / 2); //increase counter and count the rest
				
			} else {
				return numOfZeros(n / 2); //count the rest
				
			}
		}
	}
	
	//Recursion #3
	public static boolean palindrome(String str) {
		//ignore punctuation
		String s = str.replaceAll(" ", "");
		s = s.toLowerCase();
		
		//base case
		if(s.length() == 1) {
			return true;
			
		} else if (s.charAt(0) == s.charAt(s.length() - 1)) { // if first and last characters are the same
			return palindrome(s.substring(1, s.length() - 1)); //check for the string between the first and last characters
			
		} else { //if not the same
			return false;
			
		}
	}
}
