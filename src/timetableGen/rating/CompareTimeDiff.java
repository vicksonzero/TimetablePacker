package timetableGen.rating;

import java.util.ArrayList;
import java.util.Collections;

import timetableGen.meeting.Day;
import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;

public class CompareTimeDiff extends TimetableComparator {
	/**
	 * Sub-comparator to allow sort according to total no. of hours of break between lessons (shorter is better)
	 */
	public CompareTimeDiff(){
		name = "Least gap hours";
	}
	
	@Override
	public int compare(Timetable o1, Timetable o2) {
		int timeDiff1 = TimeDiff(o1);
		int timeDiff2 = TimeDiff(o2);
		
		return timeDiff1 - timeDiff2;
	}
	
	/**
	 * returns number of gap hours
	 * @return number of gap hours
	 */
	public int TimeDiff(Timetable t){
		
		ArrayList<Meeting> meetings = new ArrayList<Meeting>(t.getMeetings());
		int result = 0;
		
		// sort meetings according to startDateTime so that we are sure that 
		// we progress according to dayofweek
		Collections.sort(meetings);
		Day currentDay = null;
		int startHour = -1;
		int endHour = -1;
		
		// for all sorted meetings
		for(Meeting e:meetings){
			// if new day
			if(e.getDay() != currentDay){
				// remember this day
				currentDay = e.getDay();
				// record end hour, not using it for this round
				endHour = (int)((e.getEndDateTime().getTime()/1000/60+10)/60);
			}else{
				// if have previous class
				
				// check this class's start time
				startHour = (int)((e.getStartDateTime().getTime()/1000/60+10)/60);
				
				// add gap to result
				result+= startHour - endHour;
				
				// record this class's end time for next round
				endHour = (int)((e.getEndDateTime().getTime()/1000/60+10)/60);
			}
		}
		
		
		return result;
		
	}

}
