package timetableGen.rating;

import java.util.Comparator;

import timetableGen.meeting.Day;
import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;
public class CompareDayCount implements Comparator<Timetable> {

	@Override
	public int compare(Timetable o1, Timetable o2) {
		return calcDays(o2) - calcDays(o1);
	}
	
	private int calcDays(Timetable t){
		int[] week=new int[7];
		for (Meeting e:t.getMeetings()){
			Day day = e.getDay();
			week[day.getValue()-1]=1;
		}
		return week[0]+week[1]+week[2]+week[3]+week[4]+week[5]+week[6];
	}
	

}
