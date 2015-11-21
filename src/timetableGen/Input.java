package timetableGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
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
	public ArrayList<Course> parse() throws FileNotFoundException, WrongFormatException{
    	// holder for result
    	ArrayList<Course> result = new ArrayList<Course>();
    	// scanner for file content
    	Scanner scanner = new Scanner(this.file);
	
    	
    	// holder for the current line
    	String line;
    	
    	// flag for whether a new course is encountered
    	boolean needNewClass = true;
    	
    	
    	Course currentCourse=null;
    	
    	// since needNewClass is true when the loop starts, 
    	// it is guaranteed the first line, which is the course code , 
    	// will be used to make the first course
    	// the first case of the if-block will always be executed in the first loop
    	while (scanner.hasNext()) 
        {
        	line= scanner.nextLine();
        	//System.out.printf("\"%s\"%n",line);
        	
        	if(needNewClass){ // last line is empty or it is first line
        		// holder for the current course being scanned
        		// line contains course code
        		currentCourse = new Course(line);
        		
        		
        		// include in result
        		result.add(currentCourse);
        		
        		// set flag to get content
        		needNewClass = false;
        		
        	}else if( !needNewClass && line.equals("")){ // this line is empty
        		
        		// set flag and ignore this line
        		needNewClass = true;
        		
        	}else{
        		// split line to get data
    			// modifies currentCourse with data
				processMeetingLine(currentCourse, line);
                
        	}
        	
        	
            
            
        }
        scanner.close();
		return result;
	}
	
	protected void processMeetingLine(Course currentCourse, String line) throws WrongFormatException{

		String[] data = line.split("\t");
		String[] timeParts =  data[11].split(" - ");
		if(data[1].charAt(0) == 'C'){
			try {
				currentCourse.getLecture().add(new Lecture(Integer.parseInt(data[0]), data[1], Day.stringToDay(data[10]), timeParts[0], timeParts[1], data[12], data[13], data[14]));
			} catch (NumberFormatException e) {
				throw new WrongFormatException("CRN should be a number");
			} catch (ParseException e) {
				throw new WrongFormatException("time format should be XX:XX - XX:XX");
			}
		}else if(data[1].charAt(0) == 'T'){
			try {
				currentCourse.getTutorial().add(new Tutorial(Integer.parseInt(data[0]), data[1], Day.stringToDay(data[10]), timeParts[0], timeParts[1], data[12], data[13], data[14]));
			} catch (NumberFormatException e) {
				throw new WrongFormatException("CRN should be a number");
			} catch (ParseException e) {
				throw new WrongFormatException("time format should be XX:XX - XX:XX");
			}
		}else{
			throw new WrongFormatException("");
		}
	}
	
}
