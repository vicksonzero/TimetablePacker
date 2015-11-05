package timetableGen;

import java.util.ArrayList;

import timetableGen.Rating.rateHandler;

public class Timetable implements Comparable<Timetable>{
	private ArrayList<Meeting> meetings=new ArrayList<Meeting>();
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
	
}
