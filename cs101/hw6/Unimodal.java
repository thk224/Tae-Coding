//Tae Kim (tk2686)
public class Unimodal {

	public static void main(String[] args) {
		//test cases
		System.out.println(isUnimodal(new int[]{2, 3, 5, 7, 8, 6, 4, 2}));
		System.out.println(isUnimodal(new int[]{1, 3, 2, 4}));
		System.out.println(isUnimodal(new int[]{1, 3, 3, 2}));
		System.out.println(isUnimodal(new int[]{1, 3, 2}));
		System.out.println(isUnimodal(new int[]{1, 3}));
		System.out.println(isUnimodal(new int[]{1}));
		System.out.println(isUnimodal(new int[]{}));
	}
	
	public static boolean isUnimodal(int[] array) {
		//declare increase and decrease int variables
		int increase = 0;
		int decrease = array.length - 1;
		
		//determine weather the number increases and count the increment
		while (increase < array.length - 1 && array[increase] < array[increase + 1]) {
			increase++;
		}
		
		//determine weather the number decreases and count the decrement
		while (0 < decrease && array[decrease - 1] > array[decrease]) {
			decrease--;
		}
		
		//determine weather the increment equals to decrement and if those number are valid
		return increase == decrease && increase > 0 && decrease < array.length - 1;
	}

}
