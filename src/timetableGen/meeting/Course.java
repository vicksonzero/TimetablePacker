package timetableGen.meeting;

import java.util.ArrayList;

import timetableGen.meeting.Lecture;
import timetableGen.meeting.Tutorial;

public class Course {
	public ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	public ArrayList<Tutorial> tutorialList = new ArrayList<Tutorial>();
	public String courseCode = "";
}
