package hw1;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, String firstName, String lastName) {
		this.setUsername(username);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		
	}
	
	public void viewCourses(ArrayList <Course> c) {
		for (Course course : c) {
			System.out.println(course);
		}
	}
	
	public void viewStudentCourse(ArrayList <Student> s, String firstName, String lastName) {
		for (Student student : s) {
			
			String firstN = student.getFirstName();
			String lastN = student.getLastName();
			
			if(firstName.equals(firstN) && lastName.equals(lastN)) {
				student.getCourse();
			}
			
		}
	}
	
	public abstract void options();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
