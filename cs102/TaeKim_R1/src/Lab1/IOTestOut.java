package Lab1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTestOut {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//Request the length and width from user (use System.out)
		//Save these values in local variables l and w.
		System.out.print("Enter the length: ");
		int l = Integer.parseInt(in.readLine());
		
		System.out.print("Enter the width: ");
		int w = Integer.parseInt(in.readLine());
		
		//create a Rectangle object with these values.
		Rectangle r = new Rectangle(l,w);
		
		//Output the rectangle area.
		System.out.println(r.getArea());
	}

}
