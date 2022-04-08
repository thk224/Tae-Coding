//Tae Kim (tk2686)
package hw5;

public class PhoneBookAdmin extends User {
	private String email;
	
	//default constructor
	public PhoneBookAdmin() {
		
	}
	
	//constructor
	public PhoneBookAdmin(String username, String password, String email, PhoneBook phoneBook) {
		super(username, password, phoneBook);
		this.email = email;
	}
	
	//getter and setter for email
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Change username
	public void changeUsername(String username) {
		this.username = username;
	}
	
	//Change password.
	public void changePassword(String password) {
		setPassword(password);
	}
	
	@Override
	public void printUserInfo() {
		System.out.println("Admin Info: " + getUsername() + ", " + getPassword() + ", " + getEmail());
	}
	
	//Print all entries in the phone book.
	public void printPhoneBook() {
		getPhoneBook().printPhoneBook();
	}
	
	//Add a phone book entry.
	public void addEntry(PhoneBookEntry entry) {
		getPhoneBook().addEntry(entry);
	}
	
	//Sort the phone book entries by phone number.
	public void sort() {
		getPhoneBook().sort();
	}
	
	//Binary search by phone number.
	public PhoneBookEntry binarySearch(long number) {
		return getPhoneBook().binarySearch(number);
	}

}
