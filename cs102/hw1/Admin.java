package hw1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Admin extends User implements AdminInterface{

	private String username;
	private String password;
	private ArrayList <Student> students = new ArrayList <Student> ();

	public Admin() {
		// TODO Auto-generated constructor stub
		username = "Admin";
		password = "Admin001";
	}
	
	//Create a new course
	@Override
	public void newCourse(String courseName, String courseId, int maxStudents, String instructor,
			String sectionNum, String location, ArrayList<Course> c) {
		// TODO Auto-generated method stub
		Course course = new Course(courseName, courseId, maxStudents, 0, instructor, sectionNum, location);
		
		c.add(course);
	}
	
	//Delete a course
	@Override
	public void deleteCourse(ArrayList<Course> c, String courseName) {
		// TODO Auto-generated method stub
		for (Course course : c) {
			if (courseName.equals(course.getCourseName())) {
				c.remove(course);
			}
		}
	}
	
	//Edit a course
	@Override
	public void editCourse(ArrayList<Course> c, String courseName, int maxStudent, String instructor, String sectionNum, String location) {
		// TODO Auto-generated method stub
		for (Course course : c) {
			if (courseName.equals(course.getCourseName())) {
				course.setMaxStudents(maxStudent);
				course.setInstructor(instructor);
				course.setSectionNum(sectionNum);
				course.setLocation(location);
			}
		}
		
		
	}
	
	//Display information for a given course
	@Override
	public void displayCourse(ArrayList<Course> c, String courseId) {
		// TODO Auto-generated method stub
		
		for (Course course : c) {
			if (courseId.equals(course.getCourseId())) {
				System.out.println(course);
			}
		}
		
	}
	
	//View all courses
	@Override
	public void viewAllCourses(ArrayList<Course> c) {
		// TODO Auto-generated method stub
		
		for (Course course : c) {
			System.out.println(course);
		}
	}
	
	//View the names of the students being registered in a specific course
	@Override
	public void viewAllStudents(ArrayList<Course> c, String courseName) {
		// TODO Auto-generated method stub
		for (Course course : c) {
			if (courseName.equals(course.getCourseName())) {
				ArrayList <Student> s = course.getStudentNames();
				
				for (Student student : s) {
					System.out.println(student);
				}
			}
		}
	}
	
	//Register a student
	public void registerStudent(String username, String password, String firstName, String lastName) {
		// TODO Auto-generated method stub
		Student student = new Student(username, password, firstName, lastName);
		students.add(student);
	}
	
	//Write to a file the list of course that are Full
	@Override
	public void writeFile(ArrayList<Course> c) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		for (Course course : c) {
			
			if(course.getMaxStudents() == course.getCurStudents()) {
				writer.println(course);
			}
		}
		writer.close();
		
	}
	
	//View the list of courses that a given student is being registered on
	@Override
	public void viewStudentCourses(ArrayList<Course> c, String firstName, String lastName) {
		// TODO Auto-generated method stub
		for (Course course : c) {
				ArrayList <Student> s = course.getStudentNames();
				
				for (Student student : s) {
					if (firstName.equals(student.getFirstName()) && lastName.equals(student.getLastName()))
					System.out.println(course);
				}
		}
		
	}
	
	//Write to a file the list of course that are Full
	@Override
	public void viewFullCourses(ArrayList<Course> c) {
		// TODO Auto-generated method stub
		for (Course course : c) {
			System.out.println("Courses that are full: ");
			if(course.isFull()) {
				System.out.println(course);
			}
		}
		
	}
	
	//since we implemented comparable to Course object
	public void sort(ArrayList<Course> c) {
		ArrayList<Course> sortedC = new ArrayList<Course>();
		for (Course course : c) {
			sortedC.add(course);
		}
		
		Collections.sort(sortedC);
		System.out.println(sortedC);
	}
	
	@Override
	public void options() {
		System.out.println("Options: ");
		System.out.println("1. View all courses");
		System.out.println("2. View all courses that are FULL");
		System.out.println("3. Write to a file the list of course that are Full");
		System.out.println("4. View the names of the students being registered in a specific course");
		System.out.println("5. View the list of courses that a given student is being registered on");
		System.out.println("6. Sort");
		System.out.println("7. Create a new course");
		System.out.println("8. Delete a course");
		System.out.println("9.Edit a course");
		System.out.println("10.Display information for a given course");
		System.out.println("11.Register a student");
		System.out.println("12.Exit");
		System.out.println("Please enter the number: ");
		
	}
}
