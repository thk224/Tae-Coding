//Tae Kim (tk2686)
package hw5;

public abstract class User {
	protected String username;
	private String password;
	private PhoneBook phoneBook;
	
	//default constructor
	protected User() {
		
	}
	
	//constructor
	protected User(String username, String password, PhoneBook phoneBook) {
		this.username = username;
		this.password = password;
		this.phoneBook = phoneBook;
	}
	
	//getter for username
	public String getUsername() {
		return this.username;
	}
	
	//getter and setter for password
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//getter and setter for phone book
	public PhoneBook getPhoneBook() {
		return this.phoneBook;
	}
	
	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}
	
	//an abstract method printUserInfo, which should be overridden in both PhoneBookAdmin and NormalUser
	public abstract void printUserInfo();
	
	//linear search by phone number.
	public PhoneBookEntry linearSearch(long number) {
		return phoneBook.linearSearch(number);
	}
	
}
