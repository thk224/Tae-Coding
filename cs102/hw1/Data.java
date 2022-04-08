package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Data {
	
	private String courses;
	
	public Data() {
		// TODO Auto-generated constructor stub
	}
	
	//read file
	public String readString() throws FileNotFoundException{
		
		Scanner scanner = new Scanner (new File("/Users/kimtaehun/Desktop/MyUniversityCoursesFile.csv"));
		String line = "";
		
		while(scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			line += nextLine + ";";
		}
		
		scanner.close();
		System.out.println(line);
		
		return line;
		
	}
	
	//convert to 2d array
	public String[][] array(String s){
		
		String[] temp1 = s.split(";");
		
		String[][] arr = new String[temp1.length + 1][8];
		
		
		for (int i = 0; i < temp1.length; i++) {
			String[] temp2 = temp1[i].split(",");
			for (int j = 0; j < temp2.length; j++) {
				arr[i][j] = temp2[j];
			}
			
		}
		
		return arr;
		
	}
	
	//convert to Course array
	public ArrayList<Course> courseArray(String[][] arr){
		ArrayList<Course> course = new ArrayList<Course> ();
		
		//disregard the first line
		for(int i = 1; i < arr.length-1; i++) {
			String courseName = arr[i][0];
			String courseId = arr[i][1];
			String maxStudents = arr[i][2];
			String curStudents = "0";
			String instructor = arr[i][5];
			String sectionNum = arr[i][6];
			String location = arr[i][7];
			
			System.out.println(maxStudents);
			
			Course nCourse = new Course(courseName, courseId, maxStudents, curStudents, instructor, sectionNum, location);
			
			course.add(nCourse);
		}
		
		return course;
		
	}

}
