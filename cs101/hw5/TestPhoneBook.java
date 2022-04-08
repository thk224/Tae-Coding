//Tae Kim (tk2686)
public class TestPhoneBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Create a phone book
		PhoneBook contacts = new PhoneBook();
		
		//Add phone book entry — James Smith, 6549891223
		contacts.addEntry(new PhoneBookEntry("James", "Smith", 6549891223L));
		//Add phone book entry — Maria Garcia, 5512345765
		contacts.addEntry(new PhoneBookEntry("Maria", "Garcia", 5512345765L));
		
		//Print all entries
		System.out.println("Contacts:");
		contacts.printPhoneBook();
		
		//Sort all entries
		contacts.sort();
		
		//Print all entries
		System.out.println("\nContacts:");
		contacts.printPhoneBook();
		
		//Create a phone book entry using the default constructor
		PhoneBookEntry newContact = new PhoneBookEntry();
		
		//Set this entry to be Michael Johnson, 1909026532
		newContact.setFirstName("Michael");
		newContact.setLastName("Johnson");
		newContact.setPhoneNum(1909026532L);
		//Add it to the phone book
		contacts.addEntry(newContact);
		
		//Add phone book entry — Derek, 5516878429
		contacts.addEntry(new PhoneBookEntry("Derek", 5516878429L));
		
		//Linear Search for number — 1909026532 and print details
		System.out.println("\nContact #1909026532: " + contacts.linearSearch(1909026532L));
		
		//Sort all entries
		contacts.sort();
		
		//Binary Search for number — 6549891223 and print details
		System.out.println("\nContact #6549891223: " + contacts.binarySearch(6549891223L));
		//Binary Search for number — 9553692470 and print details
		System.out.println("\nContact #9553692470: " + contacts.binarySearch(9553692470L));
		
		//Add phone book entry — Emily Martinez, 9553692470
		contacts.addEntry(new PhoneBookEntry("Emily", "Martinez", 9553692470L));
		
		//Sort all entries
		contacts.sort();
		
		//Print all entries
		System.out.println("\nContacts:");
		contacts.printPhoneBook();
		
		//Linear Search for number — 5512345760 and print details
		System.out.println("\nContact #5512345760: " + contacts.linearSearch(5512345760L));
		
		//Additional testing
		System.out.println("-----------------------");
		
		//PhoneBook methods
		PhoneBook taePhoneBook = new PhoneBook(100);
		System.out.println("Tae's Phone Book: ");
		System.out.println("Capacity: " + taePhoneBook.getCapacity());
		System.out.println("Size: " + taePhoneBook.getSize());
		System.out.println("Empty? " + taePhoneBook.empty());
		System.out.println();
		
		//PhoneBookEntry methods
		PhoneBookEntry tae = new PhoneBookEntry("Tae", "Kim", 1234567890L);
		System.out.println("Contact 'Tae Kim': ");
		System.out.println("First Name: " + tae.getFirstName());
		System.out.println("Last Name: " + tae.getLastName());
		System.out.println("Phone#: " + tae.getPhoneNum());
		System.out.println();
		
		//PhoneBook methods after adding
		taePhoneBook.addEntry(tae);
		System.out.println("Tae's Phone Book: ");
		System.out.println("Capacity: " + taePhoneBook.getCapacity());
		System.out.println("Size: " + taePhoneBook.getSize());
		System.out.println("Empty? " + taePhoneBook.empty());
		System.out.println();
		
		
	}

}
