package timetableGen;

import java.util.ArrayList;

import timetableGen.meeting.Lecture;
import timetableGen.meeting.Tutorial;

public class Course {
	private ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	private ArrayList<Tutorial> tutorialList = new ArrayList<Tutorial>();
	private String courseCode = "";
	
	
	public Course(String code) {
		courseCode=code;
	}

	public ArrayList<Lecture> getLecture(){
		return this.lectureList;
	}
	
	public ArrayList<Tutorial> getTutorial(){
		return this.tutorialList;
	}
	
	public String getCode(){
		return this.courseCode;
	}
}
