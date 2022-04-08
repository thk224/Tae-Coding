package hw2;

public class Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		//System.out.println(reversed("google", "elgoog"));
		//System.out.println(reversed("data", "ata"));
		//System.out.println(reversed("asdfghjkl", "lkjhgf    DSa"));
		
		System.out.println(shortestStrings("Other entries include a historic district in Charlottesville Virginia cut-flower greenhouse complex"));
		*/
	}
	
	//**Exercise One**
	public static int reversed(String str1, String str2) {
		//remove white spaces
		String s1 = str1.replaceAll(" ", "");
		String s2 = str2.replaceAll(" ", "");
		
		//ignore upper case letters
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		
		//compare two strings
		if (s1.length() != s2.length()) { //if length is not equivalent
			return 0; //false
			
		} else if (s1.isEmpty() && s2.isEmpty()) { //if both strings are empty
			return 1; //true
			
		} else {
			if (s1.charAt(0) == s2.charAt(s2.length() - 1)) { //compare the first and last character of the strings
				return reversed(s1.substring(1), s2.substring(0, s2.length() - 1)); //call the method for the remaining characters
				
			} else { //if s1's first char != s2's last char
				return 0;
				
			}
		}
	}
	
	//**Exercise Two**
	public static String shortestStrings(String str) {
		//create a string array
		String s[] = str.split(" ");
		String shortest = "";
		
		//if the string contains less than or equal to three words or is not multiple of 3.
		if (s.length <= 3 || s.length % 3 != 0) {
			return str;
		}
		
		//for every three consecutive strings
		for (int i = 0; i < s.length; i += 3) {
			String threeStr[] = new String[3];
			threeStr[0] = s[i];
			threeStr[1] = s[i + 1];
			threeStr[2] = s[i + 2];
			
			//sort the strings
			for (int j = 0; j < 3; j++) {
				for (int k = j + 1; k < 3; k++) {
					String temp = "";
					if (threeStr[j].length() > threeStr[k].length()) {
						//let three[0] be the shortest string
						temp = threeStr[j];
						threeStr[j] = threeStr[k];
						threeStr[k] = temp;
					}
				}
			}
			shortest += threeStr[0] + " ";
			
		}
		
		return shortest;
	}

}
