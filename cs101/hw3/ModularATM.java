//Tae Kim (tk2686)
import java.util.Scanner;

public class ModularATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//initialize variables
		int selection = 0;
		double balance = 0;
		
		while (selection != 4) {
			selection = options(); //get selection input
			
			if (selection == 1) { //view balance
				
				balance = viewBalance(balance);
				
			} else if (selection == 2) { //deposit
				
				balance = deposit(balance);
				
			} else if (selection == 3) { //withdraw
				
				balance = withdraw(balance);
				
			} else if (selection == 4) { //leave
				
				System.out.println("\nGoodbye!");
				
			} else { //any invalid selection
				
				System.out.println("\nError");
				break;
			}
		}
	
	}

	public static int options() {
		System.out.println("\n1. View your balance");
		System.out.println("2. Deposit cash");
		System.out.println("3. Withdraw cash");
		System.out.println("4. Leave the ATM\n");
		
		//User inputs selection
		Scanner userInput = new Scanner(System.in);
		System.out.print("Enter your selection: ");
		int selection = userInput.nextInt();
		
		return selection;
	}
	
	public static double viewBalance(double balance) {
		
		System.out.println("\nYour balance is " + balance);
		
		return balance;
	}
	
	public static double deposit(double balance) {
		
		Scanner userInput = new Scanner(System.in);
		System.out.print("\nEnter the amount you want to deposit: ");
		double depositAmount = userInput.nextDouble();
		
		if (depositAmount < 0) { //to prevent negative input
			System.out.println("\nSorry, invalid input");
			
			return balance;
		} else {
			//deposit to the balance
			return balance + depositAmount;
		}
		
	}
	
	public static double withdraw(double balance) {
		
		Scanner userInput = new Scanner(System.in);
		System.out.print("\nEnter the amount you want to withdraw: ");
		double withdrawAmount = userInput.nextDouble();
		
		//to prevent withdraw that is more than your current account balance
		if (withdrawAmount > balance){
			System.out.println("\nSorry, your balance is insufficient.");
			
			return balance;
		} else if (withdrawAmount < 0) {
			System.out.println("\nSorry, invalid input");
			
			return balance;
		} else {
			
			//deducted balance
			return balance - withdrawAmount;
		}
		
	}
	
}
