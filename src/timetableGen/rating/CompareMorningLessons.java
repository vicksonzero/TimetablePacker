package timetableGen.rating;

import java.util.Calendar;
import java.util.Comparator;

import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;

public class CompareMorningLessons implements Comparator<Timetable> {

	@Override
	public int compare(Timetable o1, Timetable o2) {
		int countMorningLesson1 = countMorningLesson(o1);
		int countMorningLesson2 = countMorningLesson(o2);
		
		return countMorningLesson1 - countMorningLesson2;
	}
	
	private int countMorningLesson(Timetable t){
		int counter=0;
		 Calendar cal = Calendar.getInstance();
		 
		for (Meeting e: t.getMeetings()){
			cal.setTime(e.getStartDateTime());
			if (cal.get(Calendar.HOUR_OF_DAY)<10)
				counter++;
		}
		
		return counter;
	}

}
