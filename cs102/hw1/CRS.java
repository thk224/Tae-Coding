package hw1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class CRS {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		ArrayList <Course> course = new ArrayList<Course>();
		ArrayList<Student> students = new ArrayList<Student>();
		
		Scanner scanner = new Scanner(System.in);
		
		//ask if student or admin
		System.out.print("Are you a Student or Admin? (S/A): ");
		String input = scanner.nextLine();
		
		//Admin
		if (input.equals("A")) {
			System.out.print("Username: ");
			String uName = scanner.nextLine();
			
			if (uName.equals("Admin")) {
				System.out.print("Password: ");
				String pswd = scanner.nextLine();
				
				if (pswd.equals("Admin001")) {
					System.out.println("Welcome!");
					
					Admin admin = new Admin();
					Data data = new Data();
					
					String temp = data.readString();
					
					String[][] temp2 = data.array(temp);
					
					course = data.courseArray(temp2);
					
					while(true) {
						//suggest options
						admin.options();
						int option = Integer.parseInt(scanner.nextLine());
						if (option == 1) {
							admin.viewAllCourses(course);
							
						} else if (option == 2) {
							admin.viewFullCourses(course);
							
						} else if (option == 3) {
							admin.writeFile(course);
							
						} else if (option == 4) {
							System.out.print("What is the course name?: ");
							String courseName = scanner.nextLine();
							
							admin.viewAllStudents(course, courseName);
							
						} else if (option == 5) {
							System.out.print("What is student's first name?: ");
							String fName = scanner.nextLine();
							System.out.print("What is student's last name?: ");
							String lName = scanner.nextLine();
							
							admin.viewStudentCourses(course, fName, lName);
						} else if (option == 6) {
							admin.sort(course);
							
						} else if (option == 7) {
							System.out.print("Course name: ");
							String courseName = scanner.nextLine();
							
							System.out.print("Course ID: ");
							String courseId = scanner.nextLine();
							
							System.out.print("Max Students: ");
							int maxStudents = Integer.parseInt(scanner.nextLine());
							
							System.out.print("Instructor: ");
							String instructor = scanner.nextLine();
							
							System.out.print("Section #: ");
							String sectionNum = scanner.nextLine();
							
							System.out.print("Location: ");
							String location = scanner.nextLine();
							
							admin.newCourse(courseName, courseId, maxStudents, instructor, sectionNum, location, course);
							
						} else if (option == 8) {
							System.out.print("Course name: ");
							String courseName = scanner.nextLine();
							
							admin.deleteCourse(course, courseName);
							
						} else if (option == 9) {
							System.out.print("Course name: ");
							String courseName = scanner.nextLine();
							
							System.out.print("New max students: ");
							int maxStudent = Integer.parseInt(scanner.nextLine());
							
							System.out.print("New instructor: ");
							String instructor = scanner.nextLine();
							
							System.out.print("New sectionNum: ");
							String sectionNum = scanner.nextLine();
							
							System.out.print("New location: ");
							String location = scanner.nextLine();
							
							admin.editCourse(course, courseName, maxStudent, instructor, sectionNum, location);
							
							
						} else if (option == 10) {
							System.out.print("Course ID: ");
							String courseId = scanner.nextLine();
							
							admin.displayCourse(course, courseId);
							
						} else if (option == 11) {
							System.out.print("Username: ");
							String username = scanner.nextLine();
							
							System.out.print("Password: ");
							String password = scanner.nextLine();
							
							System.out.print("First name: ");
							String firstName = scanner.nextLine();
							
							System.out.print("Last name: ");
							String lastName = scanner.nextLine();
							
							admin.registerStudent(username, password, firstName, lastName);
							
						} else if (option == 12) {
							break;
							
						} else{
							break;
						}
						
					}
					
					
					
					
				} else {
					System.out.println("Wrong Password");
				}
			} else {
				System.out.println("Wrong Username");
			}
		}
		
		//Student
		if (input.equals("S")) {
			System.out.println("Are you a new student? (Y/N)");
			String newStu = scanner.nextLine();
			
			if (newStu.equals("Y")) {
				//register new student
				
				Data data = new Data();
				
				String temp = data.readString();
				
				String[][] temp2 = data.array(temp);
				
				course = data.courseArray(temp2);
				
				System.out.print("Username: ");
				String username = scanner.nextLine();
				
				System.out.print("Password: ");
				String password = scanner.nextLine();
				
				System.out.print("First name: ");
				String firstName = scanner.nextLine();
				
				System.out.print("Last name: ");
				String lastName = scanner.nextLine();
				
				Student student = new Student(username, password, firstName, lastName);
				
				while(true) {
					student.options();
					int option = Integer.parseInt(scanner.nextLine());
					
					if (option == 1) {
						student.viewCourses(course);
						
					} else if (option == 2) {
						student.viewNotFullCourses(course);
						
					} else if (option == 3) {
						System.out.print("Course name: ");
						String courseName = scanner.nextLine();
						
						System.out.print("Section number: ");
						String sectionNum = scanner.nextLine();
						
						student.registerCourse(course, courseName, sectionNum, student);
						
					} else if (option == 4) {
						System.out.print("Course name: ");
						String courseName = scanner.nextLine();
						
						System.out.print("First name: ");
						String fName = scanner.nextLine();
						
						System.out.print("Last name: ");
						String lName = scanner.nextLine();
						
						student.withdraw(course, fName, lName, courseName);
						
					} else if (option == 5) {
						student.viewStudentCourse(students, firstName, lastName);
						
					} else if (option == 6) {
						break;
					}
				}
				
			} else { //deserialization
				
				try{
				      FileInputStream file1 = new FileInputStream("course.ser");
				      FileInputStream file2 = new FileInputStream("student.ser");
				      
				      ObjectInputStream obj1 = new ObjectInputStream(file1);
				      ObjectInputStream obj2 = new ObjectInputStream(file2);

				      course = (ArrayList<Course>)obj1.readObject();
				      students = (ArrayList<Student>)obj2.readObject();
				      file1.close();
				      obj1.close();
				      
				      file2.close();
				      obj2.close();
				    }
				    catch(IOException ioe) {
				       ioe.printStackTrace();
				       return;
				    }
				 catch(ClassNotFoundException cnfe) {
				       cnfe.printStackTrace();
				       return;
				     }
				//verify username and password
				System.out.print("Username: ");
				String username = scanner.nextLine();
				
				System.out.print("Password: ");
				String password = scanner.nextLine();
				
				for (Student s : students) {
					if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
						Student student = s;
						
						while(true) {
							student.options();
							int option = Integer.parseInt(scanner.nextLine());
							
							if (option == 1) {
								student.viewCourses(course);
								
							} else if (option == 2) {
								student.viewNotFullCourses(course);
								
							} else if (option == 3) {
								System.out.print("Course name: ");
								String courseName = scanner.nextLine();
								
								System.out.print("Section number: ");
								String sectionNum = scanner.nextLine();
								
								student.registerCourse(course, courseName, sectionNum, student);
								
							} else if (option == 4) {
								System.out.print("Course name: ");
								String courseName = scanner.nextLine();
								
								System.out.print("First name: ");
								String fName = scanner.nextLine();
								
								System.out.print("Last name: ");
								String lName = scanner.nextLine();
								
								student.withdraw(course, fName, lName, courseName);
								
							} else if (option == 5) {
								student.viewStudentCourse(students, student.getFirstName(), student.getLastName());
								
							} else if (option == 6) {
								break;
							}
						}
						
					} else {
						System.exit(0);
					}
				}
			}
			//Serialization
			try {
				FileOutputStream courseData = new FileOutputStream("course.ser");
				FileOutputStream studentsData = new FileOutputStream("students.ser");
				
				ObjectOutputStream oosC = new ObjectOutputStream(courseData);
				ObjectOutputStream oosS = new ObjectOutputStream(studentsData);
				
				oosC.writeObject(course);
				oosS.writeObject(students);
				
				oosC.close();
				oosS.close();

				courseData.close();
				studentsData.close();

				System.out.println("Thank you!");
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		}

		
	}

}
