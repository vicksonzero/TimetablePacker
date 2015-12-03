package timetableGen.rating;

import java.util.Calendar;
import java.util.Comparator;

import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;

public class CompareEveningLessons extends TimetableComparator {
	
	public CompareEveningLessons(){
		name = "Fewest Evening Lessons";
	}
	
	@Override
	public int compare(Timetable o1, Timetable o2) {
		int countEveningLesson1 = countEveningLesson(o1);
		int countEveningLesson2 = countEveningLesson(o2);
		
		return countEveningLesson1 - countEveningLesson2;
	}
	
	private int countEveningLesson(Timetable t){
		int counter=0;
		 Calendar cal = Calendar.getInstance();
		 
		for (Meeting e: t.getMeetings()){
			cal.setTime(e.getEndDateTime());
			if (cal.get(Calendar.HOUR_OF_DAY)+1>=18)
				counter++;
		}
		return counter;
	}

}
