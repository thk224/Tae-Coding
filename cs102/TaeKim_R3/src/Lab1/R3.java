package Lab1;

public class R3 {

	public R3() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {7, -14, 14, 9};
		
		System.out.println(maxInt(arr, 4));
		
		System.out.println(sumOfeOrN(arr, 4));
		
		String temp = "AAAbbCCCC";
		
		System.out.println(removeString(temp));
		
		
	}
	
	public static int maxInt(int[] array, int n) {
		if (n == 1) {
			return array[0];
		} else {
			return Math.max(array[n-1], maxInt(array, n-1));
		}
	}
	
	public static int sumOfeOrN(int[] array, int n) {
		if (n <= 0) {
			return 0;
		} else {
			if (array[n - 1] < 0 || array[n - 1] % 2 == 0) {
				return array[n - 1] + sumOfeOrN(array, n - 1);
			} else{
				return sumOfeOrN(array, n - 1);
			}
		}
	}
	
	public static String removeString(String s) {
		if (s.length() <= 1) {
			return s;
		} else {
			if(s.charAt(0) == s.charAt(1)) {
				return removeString(s.substring(1));
			} else {
				return s.substring(0,1) + removeString(s.substring(1));
			}
		}
		
	}
	
}
