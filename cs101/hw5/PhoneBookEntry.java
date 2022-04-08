//Tae Kim (tk2686)
public class PhoneBookEntry {
	
	private String firstName;
	private String lastName;
	private long phoneNum;
	
	//A default (no-arg) constructor
	public PhoneBookEntry() {
		
	}
	
	//A constructor that takes all attributes as arguments
	public PhoneBookEntry(String firstName, String lastName, long phoneNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
	}
	
	//A constructor that takes only the first name and the phone number
	public PhoneBookEntry(String firstName, long phoneNum) {
		this.firstName = firstName;
		this.phoneNum = phoneNum;
	}
	
	//getters and setters for first name
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	//getters and setters for last name
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//getters and setters for phone number
	public long getPhoneNum() {
		return this.phoneNum;
	}
	
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	//method that returns a string representation of all the data fields
	public String toString() {
		if (this.lastName == null) {
			return this.firstName + ", " + this.phoneNum;
		}
		return this.firstName + " " + this.lastName + ", " + this.phoneNum;
	}
	
	//method that prints the book entry in a human-friendly manner
	public void printEntry() {
		System.out.println(toString());
	}
}
