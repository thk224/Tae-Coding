//Tae Kim (tk2686)
package hw5;

public class NormalUser extends User {
	private int id;
	
	public NormalUser() {
		
	}

	public NormalUser(String username, String password, int id, PhoneBook phoneBook) {
		super(username, password, phoneBook);
		this.id = id;
	}
	
	//getter for id
	public int getId() {
		return this.id;
	}
	
	//Change password.
	public void changePassword(String password) {
		setPassword(password);
	}

	@Override
	public void printUserInfo() {
		System.out.println("User Info: " + getUsername() + ", " + getPassword() + ", " + getId());

	}

}
