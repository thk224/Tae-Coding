package hw2;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		int arr[] = {4, 77, 98, 30, 20, 50, 77, 22, 49, 2};
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		
		System.out.println();
		
		//nonRecBubbleSort(arr);
		//recBubbleSort(arr, arr.length);
		//selectionSort(arr);
		//insertionSort(arr);
		//mergeSort(arr);
		//quickSort(arr, 0, arr.length - 1);
		
		for(int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + " ");
		}
		*/
	}
	
	//Bubble Sort Non-Recursive
	public static void nonRecBubbleSort(int arr[]) {
		
		for (int i = 0; i < arr.length; i++) {
			
			for (int j = 1; j < (arr.length - i); j++){
				
				//swap if arr[j] is less
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
					
				}
			}
		}
	}
	
	//Bubble Sort Recursive
	public static void recBubbleSort(int arr[], int n) {
		//base case
		if (n == 1) {
			//do nothing
		} else { // if n > 1
			
			//move max int to the end
			for (int i = 0; i < n - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
			//sort rest of the array
			recBubbleSort(arr, n - 1);
		}
	}
	
	//Selection Sort
	public static void selectionSort(int arr[]) {
		
		for (int i = 0; i < arr.length - 1; i++) {
			
			int min = i; //arbitrary min index
			
			for(int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j; //change min index
					
				}
			}
			
			//swap
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
			
			
		}
	}
	
	//Insertion Sort
	public static void insertionSort(int arr[]) {
		
		for (int i = 1; i < arr.length; i++) {
			
			int piv = arr[i]; //pivot element
			int j = i - 1; //element right before the pivot element
			
			//compare the pivot element and the elements before it
			while (j >= 0 && piv < arr[j]) {
				arr[j + 1] = arr[j]; //move the element
				j -= 1; //the element before the previous element
			}
			
			arr[j + 1] = piv;
		}
	}
	
	//Merge
	public static void merge(int oArr[], int arr1[], int arr2[]) {
		//starting index of the divided arrays
		int i = 0;
		int j = 0;
		
		while (i < arr1.length && j < arr2.length) {
			//compare and merge
			if (arr1[i] < arr2[j]) {
				oArr[i + j] = arr1[i];
				i++;
			} else {
				oArr[i + j] = arr2[j];
				j++;
			}
		}
		
		//if there is remaining element in arr1
		while (i < arr1.length) {
			oArr[i + j] = arr1[i];
			i++;
		}
		
		//if there is remaining element in arr2
		while (j < arr2.length) {
			oArr[i + j] = arr2[j];
			j++;
		}
	}
	
	//Merge Sort
	public static void mergeSort(int arr[]) {
		int n = arr.length;
		
		//base case
		if (n == 1) {
			//do nothing
		} else {
			//divide array
			int mid = n / 2;
			int arr1[] = Arrays.copyOfRange(arr, 0, mid);
			int arr2[] = Arrays.copyOfRange(arr, mid, n);
			
			//sort array
			mergeSort(arr1);
			mergeSort(arr2);
			
			//merge array
			merge(arr, arr1, arr2);
		}
	}
	
	//Partition
	public static int partition(int arr[], int l, int h) {
		int i = l - 1; //beginning index
		
		for (int j = l; j < h; j++) {
			if (arr[j] < arr[h]) { //if element is less than pivot arr[h]
				i++; //swap with the next element
				
				//swap
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		//swap the pivot with the greater element
		int temp = arr[i + 1];
		arr[i + 1] = arr[h];
		arr[h] = temp;
		
		return i + 1;
	}
	
	//Quick Sort
	public static void quickSort(int arr[], int l, int h) {
			
		if (l < h) {
				int p = partition(arr, l, h); //choose pivot
				
				quickSort(arr, l, p - 1); //left side of pivot
				quickSort(arr, p + 1, h); //right side of pivot
		}
	}
}
