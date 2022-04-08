import java.util.Scanner;

public class NYUATM{
	public static void main(String[] args){
		
		//declare the variables
		int selection = 0; //null selection
		double balance = 0; //initial balance is 0
		double deposit = 0;
		double withdraw = 0;		

		System.out.println("Welcome to Tae's ATM!\n");

		while (selection != 4){
			System.out.println("\n1. View your balance");
			System.out.println("2. Deposit cash");
			System.out.println("3. Withdraw cash");
			System.out.println("4. Leave the ATM\n");
			
			//User inputs selection
			Scanner userInput = new Scanner(System.in);
			System.out.print("Enter your selection: ");
			selection = userInput.nextInt();
			
			//View you balance
			if (selection == 1){
				System.out.println("\nYour balance is " + balance);
			} else if (selection == 2){
				//deposit to the balance

				System.out.print("\nEnter the amount you want to deposit: ");
				deposit = userInput.nextDouble();
				
				balance += deposit;
			} else if (selection == 3){
				//withdraw from the balance

				System.out.print("\nEnter the amount you want to withdraw: ");
				withdraw = userInput.nextDouble();
				
				//to prevent withdraw that is more than your current account balance
				if (withdraw > balance){
					System.out.println("\nSorry, your balance is insufficient.");
				} else{
					balance -= withdraw;
				}
			} else if (selection == 4){
				//exit when selection is 4
				
				System.out.println("\nGoodbye!");
			} else{
				//when user inputs other random numbers

				System.out.println("\nError");
				break;
			}

		}
		
		
	}
}