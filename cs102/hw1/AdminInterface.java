package hw1;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface AdminInterface {

	public abstract void newCourse(String courseName, String courseId, int maxStudents, 
			String instructor, String sectionNum, String location, ArrayList<Course> c);
	
	public abstract void deleteCourse(ArrayList<Course> c, String courseName);
	
	public abstract void editCourse(ArrayList <Course> c, String courseName, int maxStudent, String instructor, 
			String sectionNum, String location);
	
	public abstract void displayCourse(ArrayList <Course> c, String courseId);
	
	public abstract void viewAllCourses(ArrayList <Course> c);
	
	public static void register(ArrayList<Student> s, String firstName, String lastName) {
		
	}
	
	public abstract void viewAllStudents(ArrayList <Course> c, String courseName);
	
	public abstract void viewFullCourses(ArrayList <Course> c);
	
	public abstract void writeFile(ArrayList <Course> c) throws FileNotFoundException, UnsupportedEncodingException;
	
	public abstract void viewStudentCourses(ArrayList <Course> c, String firstName, String lastName);
	
}
