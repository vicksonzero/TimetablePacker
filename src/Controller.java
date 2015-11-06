import java.util.ArrayList;
import java.util.Collections;

import meeting.Course;
import meeting.Lecture;
import meeting.Tutorial;
import timetableGen.Timetable;


public class Controller {

	private ArrayList<Timetable> timetables=new ArrayList<Timetable>();
	private static final Course course = new Course();
	private static final Lecture lecture = new Lecture();
	private static final Tutorial tutorial = new Tutorial();
	private static final String FILENAME = "input-file.txt";
	
	public void main(String args[]){
		//sort according to level of recommendation
		Collections.sort(timetables,Collections.reverseOrder());
		
		for (int i=0;i<3;i++)
			System.out.println(timetables.get(i));
	}
	
}
