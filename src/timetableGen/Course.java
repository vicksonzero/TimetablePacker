package timetableGen;

import java.util.ArrayList;

import timetableGen.meeting.Lecture;
import timetableGen.meeting.Tutorial;

/**
 * Course Class holds the lists of available lectures and tutorials under each course,
 * Including only getter and setter method in this class
 */

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
	
	public Course addLecture(Lecture lect){
		this.lectureList.add(lect);
		lect.setCourse(this);
		return this;
	}
	
	public Course addTutorial(Tutorial tut){
		this.tutorialList.add(tut);
		tut.setCourse(this);
		return this;
	}
	
	public String getCode(){
		return this.courseCode;
	}
}
