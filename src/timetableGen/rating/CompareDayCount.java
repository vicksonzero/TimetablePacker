package timetableGen.rating;

import timetableGen.meeting.Day;
import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;

public class CompareDayCount extends TimetableComparator {
	/**
	 * Sub-comparator to allow sort according to no. of days
	 */
	public CompareDayCount(){
		name = "Fewest days";
	}

	@Override
	public int compare(Timetable o1, Timetable o2) {
		int calcDays1 = calcDays(o1);
		int calcDays2 = calcDays(o2);
		return calcDays1 - calcDays2;
	}
	/**
	 * Calculate total no of lesson days in the timetable
	 * @param timetable t
	 * @return Integer noOfDays
	 */
	private int calcDays(Timetable t){
		int[] week=new int[7];
		for (Meeting e:t.getMeetings()){
			Day day = e.getDay();
			week[day.getValue()-1]=1;
		}
		return week[0]+week[1]+week[2]+week[3]+week[4]+week[5]+week[6];
	}
	

}
