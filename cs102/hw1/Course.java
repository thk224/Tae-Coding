package hw1;
import java.util.ArrayList;

public class Course implements Comparable<Course>{
	
	private String courseName;
	private String courseId;
	private	int maxStudents;
	private int curStudents;
	private ArrayList <Student> studentNames = new ArrayList <Student> ();
	private String instructor;
	private String sectionNum;
	private String location;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	
	//constructors
	public Course(String courseName, String courseId, int maxStudents, int curStudents, String instructor, String sectionNum, String location) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.maxStudents = maxStudents;
		this.curStudents = curStudents;
		this.setInstructor(instructor);
		this.setSectionNum(sectionNum);
		this.setLocation(location);
	}
	
	public Course(String courseName, String courseId, String maxStudents, String curStudents, String instructor, String sectionNum, String location) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.maxStudents = Integer.parseInt(maxStudents.trim());
		this.curStudents = Integer.parseInt(curStudents.trim());
		this.setInstructor(instructor);
		this.setSectionNum(sectionNum);
		this.setLocation(location);
	}
	
	//getters and setters
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	
	public int getCurStudents() {
		return curStudents;
	}

	public void setCurStudents(int curStudents) {
		this.curStudents = curStudents;
	}

	public ArrayList <Student> getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(ArrayList <Student> studentNames) {
		this.studentNames = studentNames;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(String sectionNum) {
		this.sectionNum = sectionNum;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString() {
		String s = "Name: " + courseName + ", ID: " + courseId + ", Max Students: " + maxStudents + ", Current Students: " + curStudents;
		
		s += ", Student list: ";
		
		for(Student student : studentNames) {
			s += student + ", ";
		}
		
		s += "Instructor: " + instructor + ", Section: " + sectionNum + ", Location: " + location;
		
		return s;
		
	}
	
	public void removeStu(Student student) {
		for (Student s : studentNames) {
			if (student.getFirstName().equals(s.getFirstName()) && student.getLastName().equals(s.getLastName())) {
				studentNames.remove(student);
				curStudents -= 1;
			}
		}
	}
	
	public boolean isFull() {
		if (curStudents >= maxStudents) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addStu(Student student) {
		studentNames.add(student);
		curStudents += 1;
	}

	@Override
	public int compareTo(Course c) {
		// TODO Auto-generated method stub
		int compC = c.getCurStudents();
		
		return this.curStudents - compC;
	}

}
