import java.util.Scanner;

public class NYUPrefix{
	public static void main(String[] args){

	Scanner userInput = new Scanner(System.in);
	
	//get the first string
	System.out.print("Enter the 1st string: ");
	String str1 = userInput.nextLine();
	
	//get the second string
	System.out.print("Enter the 2nd string: ");
	String str2 = userInput.nextLine();

	String prefix = ""; //create and empty string

	for(int i = 0; i < str1.length(); i++){
		
		//for each index compare the characters
		if (str1.charAt(i) == str2.charAt(i)){
			prefix += str1.charAt(i);
		} else{
			break; //break the loop when the character is different
		}
	}
	
	//check if the prefix is still an empty string
	if (prefix.length() > 0){
		System.out.println("The longest common prefix is \"" + prefix + "\"");
	} else{
		System.out.println("These two strings have no common prefix");
	}
	
	
	}
}