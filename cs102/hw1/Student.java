package hw1;

import java.util.ArrayList;

public class Student extends User implements StudentInterface{
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private ArrayList<Course> stuC = new ArrayList<Course>();
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public void getCourse() {
		for (Course course : this.stuC) {
			System.out.println(course);
		}
	}
	
	//View all courses that are not FULL
	@Override
	public void viewNotFullCourses(ArrayList<Course> c) {
		// TODO Auto-generated method stub
		System.out.println("Courses that are not full:");
		
		for (Course course : c) {
			if (course.getCurStudents() < course.getMaxStudents()) {
				System.out.println(course);
			}
		}
		
	}
	
	//Withdraw from a course
	@Override
	public void withdraw(ArrayList<Course> c, String firstName, String lastName, String courseName) {
		// TODO Auto-generated method stub
		for (Course course : c) {
			if (course.getCourseName().equals(courseName)) {
				for (Student student : course.getStudentNames()) {
					if (firstName.equals(student.getFirstName()) && lastName.equals(student.getLastName())) {
						course.removeStu(student);
						stuC.remove(course);
						System.out.println("You are removed from the course.");
					}
				}
			}
		}
		
	}
	
	//Register on a course
	@Override
	public void registerCourse(ArrayList<Course> c, String courseName, String section, Student student) {
		// TODO Auto-generated method stub
		for (Course course : c) {
			if (courseName.equals(course.getCourseName()) && section.equals(course.getSectionNum())) {
				if(!course.isFull()) {
					course.addStu(student);
				}
			}
		}
		
	}
	
	@Override
	public void options() {
		System.out.println("Options: ");
		System.out.println("1. View all courses");
		System.out.println("2. View all courses that are not FULL");
		System.out.println("3. Register on a course");
		System.out.println("4. Withdraw from a course");
		System.out.println("5. View all courses that the current student is being registered in");
		System.out.println("6. Exit");
		System.out.println("Please enter the number: ");
	}
}

	
