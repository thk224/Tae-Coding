public class NYUASCII{
	public static void main(String[] args){
	
	//declare variables
	final int end = 126; //end index
	int count = 0; //counter
	String result = "";
	
	//start adding the characters from 32
	for(int code = 32; code <= end; code++){
		
		//when counter is not 0, when counter is divisible by 5 move to the next line
		if(count != 0 && count%5 == 0){
			result += "\n" + (char)code;
		} else{
			result += (char)code;
		}
		
		count++; //increase the counter
	}
	

	System.out.println(result);
	}
}