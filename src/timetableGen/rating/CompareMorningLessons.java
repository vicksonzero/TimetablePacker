package timetableGen.rating;

import java.util.Calendar;

import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;

public class CompareMorningLessons extends TimetableComparator {
	/**
	 * Sub-comparator to allow sort according to no. of morning lessons
	 */
	public CompareMorningLessons(){
		name = "Fewest Morning Lessons";
	}
	
	@Override
	public int compare(Timetable o1, Timetable o2) {
		int countMorningLesson1 = countMorningLesson(o1);
		int countMorningLesson2 = countMorningLesson(o2);
		
		return countMorningLesson1 - countMorningLesson2;
	}
	/**
	 * Calculate total no of lesson days in the timetable
	 * @param timetable t
	 * @return Integer noOfMorningLessons
	 */
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
