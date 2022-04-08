package hw1;

import java.util.ArrayList;

public interface StudentInterface {

	public abstract void viewNotFullCourses(ArrayList<Course> c);
	
	public abstract void withdraw(ArrayList <Course> c, String firstName, String lastName, String courseName);
	
	public abstract void registerCourse(ArrayList <Course> c, String courseName, String section, Student student);
}
