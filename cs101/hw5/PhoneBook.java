//Tae Kim (tk2686)
public class PhoneBook {
	
	private PhoneBookEntry[] entries;
	private int size;
	public static final int DEFAULT_CAPACITY = 4;
	
	//default constructor
	public PhoneBook() {
		this(DEFAULT_CAPACITY);
	}
	
	//constructors that takes capacity parameter
	public PhoneBook(int capacity) {
		this.entries = new PhoneBookEntry[capacity];
	}
	
	//getters for each attributes
	public int getCapacity() {
		return entries.length;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	//method calls the printEntry() method for each element in the array
	public void printPhoneBook() {
		for (int i = 0; i < size; i++) {
			entries[i].printEntry();
		}
	}
	
	//Add an entry to the PhoneBook
	public void addEntry(PhoneBookEntry entry) {
		if (size >= entries.length) {
		      PhoneBookEntry[] temp = new PhoneBookEntry[entries.length * 2];
		      System.arraycopy(entries, 0, temp, 0, entries.length);
		      entries = temp;
		    }

		entries[size++] = entry;
	}
	
	//sort the array in ascending order of the phone number
	public void sort() {	
		for (int i = 0; i < size - 1; i++) {
			int min = i;
			
			for (int j = i + 1; j < size; j++) {
				if (entries[j].getPhoneNum() < entries[min].getPhoneNum()) {
					min = j;
				}	
			}
			
			//switch min and i index
			PhoneBookEntry temp = entries[min];
			entries[min] = entries[i];
			entries[i] = temp;
		}
	}
	
	//linear search
	public PhoneBookEntry linearSearch(long number) {
		for (int i = 0; i < size; i++) {
			if (entries[i].getPhoneNum() == number) {
				return entries[i];
			}
		}
		return null;
	}
	
	//binary search
	public PhoneBookEntry binarySearch(long number) {
		int low = 0;
		int high = size - 1;
		
		while(low <= high) {
			int mid = (low + high) / 2;

			if (number == entries[mid].getPhoneNum()) {
				return entries[mid];
			} else if (number < entries[mid].getPhoneNum()) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		return null;
	}
	
}
