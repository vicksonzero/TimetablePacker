import java.util.ArrayList;
import java.util.Collections;

import timetableGen.Timetable;
import timetableGen.meeting.Course;
import timetableGen.meeting.Lecture;
import timetableGen.meeting.Meeting;
import timetableGen.meeting.Tutorial;


public class Controller {

	private ArrayList<Timetable> timetables=new ArrayList<Timetable>();
	private static final Course course = new Course();
	private static final String FILENAME = "input-file.txt";
	
	public void main(String args[]){
		//sort according to level of recommendation
		Collections.sort(timetables,Collections.reverseOrder());
		
		for (int i=0;i<3;i++)
			System.out.println(timetables.get(i));
		
	}
	
}
