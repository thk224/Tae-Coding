//Tae Kim (tk2686)
package hw5;

public class TestPhoneBookUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhoneBook phoneBook = new PhoneBook();
		PhoneBookAdmin admin = new PhoneBookAdmin("admin", "abc123", "admin@example.com", phoneBook);
		NormalUser user = new NormalUser("user", "abcde", 42, phoneBook);
		
		admin.changeUsername("boss"); //Change the username of the admin to boss.
		admin.changePassword("xyz456"); //Change the password of the admin to xyz456.
		admin.setEmail("boss@example.com"); //Change the email of the admin to boss@example.com.
		admin.printUserInfo(); //Print the admin’s info.
		
		user.changePassword("xyz"); //Change the password of the normal user to xyz.
		user.printUserInfo(); //Print the user’s info.
		
		System.out.println("----------------------------------------");
		//Using the admin, perform all the test cases in Part 3 of Homework 5.
		//Add phone book entry — James Smith, 6549891223
		admin.addEntry(new PhoneBookEntry("James", "Smith", 6549891223L));
		//Add phone book entry — Maria Garcia, 5512345765
		admin.addEntry(new PhoneBookEntry("Maria", "Garcia", 5512345765L));
		
		//Print all entries
		System.out.println("Contacts:");
		admin.printPhoneBook();
		
		//Sort all entries
		admin.sort();
		
		//Print all entries
		System.out.println("\nContacts:");
		admin.printPhoneBook();
		
		//Create a phone book entry using the default constructor
		PhoneBookEntry newContact = new PhoneBookEntry();
		
		//Set this entry to be Michael Johnson, 1909026532
		newContact.setFirstName("Michael");
		newContact.setLastName("Johnson");
		newContact.setPhoneNum(1909026532L);
		//Add it to the phone book
		admin.addEntry(newContact);
		
		//Add phone book entry — Derek, 5516878429
		admin.addEntry(new PhoneBookEntry("Derek", 5516878429L));
		
		//Linear Search for number — 1909026532 and print details
		System.out.println("\nContact #1909026532: " + admin.linearSearch(1909026532L));
		
		//Sort all entries
		admin.sort();
		
		//Binary Search for number — 6549891223 and print details
		System.out.println("\nContact #6549891223: " + admin.binarySearch(6549891223L));
		//Binary Search for number — 9553692470 and print details
		System.out.println("\nContact #9553692470: " + admin.binarySearch(9553692470L));
		
		//Add phone book entry — Emily Martinez, 9553692470
		admin.addEntry(new PhoneBookEntry("Emily", "Martinez", 9553692470L));
		
		//Sort all entries
		admin.sort();
		
		//Print all entries
		System.out.println("\nContacts:");
		admin.printPhoneBook();
		
		//Linear Search for number — 5512345760 and print details
		System.out.println("\nContact #5512345760: " + admin.linearSearch(5512345760L));
		
		//Using the normal user, linear search for phone number 5512345760 and print details.
		System.out.println("\nContact #5512345760: " + user.linearSearch(5512345760L));
		
	}

}
