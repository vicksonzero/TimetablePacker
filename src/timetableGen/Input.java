package timetableGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import timetableGen.meeting.Course;
import timetableGen.meeting.Lecture;
import timetableGen.meeting.Tutorial;

public class Input {
	ArrayList<String> session = new ArrayList<String>();
	
	File file;
	public Input(File f){
		this.file = f;
	}
	public ArrayList<Course> parse(){
    	// holder for result
    	ArrayList<Course> result = new ArrayList<Course>();
		try {
	    	// scanner for file content
	    	Scanner scanner = new Scanner(this.file);
    	
	    	
	    	// holder for the current line
	    	String line;
	    	
	    	// flag for whether a new course is encountered
	    	boolean needNewClass = true;
	    	
	    	// holder for the current course being scanned
	    	Course currentCourse = new Course();
	    	
	    	// since needNewClass is true when the loop starts, 
	    	// it is guaranteed the first line, which is the course code , 
	    	// will be used to make the first course
	    	// the first case of the if-block will always be executed in the first loop
	    	while (scanner.hasNext()) 
	        {
	        	line= scanner.nextLine();
	        	System.out.printf("\"%s\"%n",line);
	        	
	        	if(needNewClass){ // last line is empty or it is first line
	        		// line contains course code
	        		currentCourse = new Course();
	        		currentCourse.courseCode = line;
	        		
	        		// include in result
	        		result.add(currentCourse);
	        		
	        		// set flag to get content
	        		needNewClass = false;
	        		
	        	}else if( !needNewClass && line.equals("")){ // this line is empty
	        		
	        		// set flag and ignore this line
	        		needNewClass = true;
	        		
	        	}else{
	        		// split line to get data
	        		String[] data = line.split("\t");
	        		try {
	        			// modifies currentCourse with data
						processMeetingData(currentCourse, data);
					} catch (Exception e) {
						System.out.printf("line \"%s\" is not in correct meeting format. must have format 'C**' or 'T**' in the second space, where ** is a number", line);
						System.out.printf("Stack trace:%n");
						e.printStackTrace();
					}
	                
	        	}
	        	
	        	
	            
	            
	        }
	        scanner.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
	
	private void processMeetingData(Course currentCourse, String[] data) throws Exception{

		String[] timeParts =  data[11].split(" - ");
		if(data[1].charAt(0) == 'C'){
			try {
				currentCourse.lectureList.add(new Lecture(Integer.parseInt(data[0]), data[1], Day.stringToDay(data[10]), timeParts[0], timeParts[1], data[12], data[13], data[14]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(data[1].charAt(0) == 'T'){
			try {
				currentCourse.tutorialList.add(new Tutorial(Integer.parseInt(data[0]), data[1], Day.stringToDay(data[10]), timeParts[0], timeParts[1], data[12], data[13], data[14]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			throw new Exception("line is not in correct meeting format");
		}
	}
	
}
