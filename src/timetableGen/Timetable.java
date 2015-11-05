package timetableGen;

import java.util.ArrayList;
import java.util.Collections;

import timetableGen.Rating.rateHandler;

public class Timetable implements Comparable<Timetable>{
	public ArrayList<Meeting> meetings=new ArrayList<Meeting>();
	private Integer score=null;
	

	@Override
	public int compareTo(Timetable t2) {
		if (this.score==null){
			rateHandler rater=new rateHandler(this.meetings);
			this.score=rater.rate();
		}
		
		if(t2.score==null){
			rateHandler raterT2=new rateHandler(t2.meetings);
			t2.score=raterT2.rate();
		}
		
		return score.compareTo(t2.score);
	}
	
	public boolean hasConflict(){
		
		// sort all meetings here according to startDateTime
		Collections.sort(this.meetings);
		
		// length of the collection, to prevent checking the length over and over 
		int len = this.meetings.size();
		
		// holder variables for comparison
		Meeting lastMeeting = this.meetings.get(0);
		Meeting targetMeeting = null;
		
		// for every meeting, check if it ends after the next meeting starts 
		for(int i=1; i < len; i ++){
			targetMeeting = this.meetings.get(i);
			if(lastMeeting.getEndDateTime().after(targetMeeting.getStartDateTime())){
				return true;
			}
			lastMeeting = this.meetings.get(i);
		}
		
		// given the start time is sorted, it is sufficient that 
		// if the next class starts after the current class, 
		// all remaining classes start after the current class
		
		return false;
	}
	
}
