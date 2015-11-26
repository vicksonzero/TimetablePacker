package timetableGen.rating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import timetableGen.meeting.Day;
import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;

public class CompareTimeDiff implements Comparator<Timetable> {

	@Override
	public int compare(Timetable o1, Timetable o2) {
		int timeDiff1 = timeDiff(o1);
		int timeDiff2 = timeDiff(o2);
		
		return timeDiff1 - timeDiff2;
	}
	/**
	 * returns time to be spent on the same day
	 * @return
	 */
	public int timeDiff(Timetable t){
		
		ArrayList<Meeting> meetings = new ArrayList<Meeting>(t.getMeetings());
		int result = 0;
		
		// sort meetings according to startDateTime so that we are sure that 
		// we progress according to dayofweek
		Collections.sort(meetings);
		Day currentDay = null;
		int startHour = -1;
		int endHour = -1;
		
		// for all meetings
		for(Meeting e:meetings){
			// if new day
			if(e.getDay() != currentDay){
				currentDay = e.getDay();
				if(endHour - startHour> result) result = endHour - startHour; // endHour == startHour in the first round
				startHour = (int)((e.getStartDateTime().getTime()/1000/60+10)/60);
				endHour = (int)((e.getEndDateTime().getTime()/1000/60+10)/60);
			}else{
				endHour = (int)((e.getEndDateTime().getTime()/1000/60+10)/60);
			}
		}
		if(endHour - startHour> result) result = endHour - startHour; // endHour == startHour in the first round
		
		
		return result;
		
	}

}
