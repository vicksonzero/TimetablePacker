package timetableGen.timetable;


import java.util.ArrayList;
import java.util.Collections;

import timetableGen.Course;
import timetableGen.meeting.*;
import timetableGen.rating.*;


public class Timetable implements Comparable<Timetable>{
	private ArrayList<Meeting> meetings=new ArrayList<Meeting>();
	private Integer score=null;
	

	@Override
	public int compareTo(Timetable t2) {
		if (this.score==null){
			RateHandler rater=new RateHandler(this.meetings);
			this.score=rater.rate();
		}
		
		if(t2.score==null){
			RateHandler raterT2=new RateHandler(t2.meetings);
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

	public ArrayList<Meeting> getMeetings() {
		return this.meetings;
	}
	

	public static ArrayList<Timetable> genCom(int i,ArrayList<Course> courses){
		ArrayList<Timetable> ttList=new ArrayList<Timetable>();
	/*	if(courses.size()==0){
			return null;
		}*/
		if (i<courses.size()-1){
			///////////////////////////////////////////////////////////////
			//upper cases:
			//extract all meetings from the lower level return
			//create new timetable with 1 lecture and 1 tutorials from this course and all the meetings from lower level
			//returns grps of timetable
			/////////////////////////////////////////////////////////////
			for (Lecture lect:courses.get(i).getLecture()){
				if(courses.get(i).getTutorial().size()!=0){    //in case the course have no tutorial
					for (Tutorial tut:courses.get(i).getTutorial()){
						for (Timetable tt:genCom(i+1,courses)){
							Timetable t=new Timetable();
							t.meetings.add(tut);
							t.meetings.add(lect);
							for (Meeting m:tt.getMeetings())
								t.meetings.add(m);
							ttList.add(t);
						};
					}
				}else{
					for (Timetable tt:genCom(i+1,courses)){
						Timetable t=new Timetable();
						t.meetings.add(lect);
						for (Meeting m:tt.getMeetings())
							t.meetings.add(m);
						ttList.add(t);
					};
				}
			}		
		}else{
			/////////////////////////////////////////////////////////////
			//base case:
			//generate all combination of lecture and tutorial in this course
			//add to timetable as meetings (1 lecture and 1 tutorial in this timetable
			//return grps of timetable
			////////////////////////////////////////////////////////////
			Course c=courses.get(i);
			for (Lecture lect:c.getLecture()){
				if(c.getTutorial().size()!=0){  //in case the course have no tutorial
					for (Tutorial tut:c.getTutorial()){
						Timetable tt=new Timetable();
						tt.getMeetings().add(lect);
						tt.getMeetings().add(tut);	
						ttList.add(tt);
					}	
				}else{
					Timetable tt=new Timetable();
					tt.getMeetings().add(lect);
					ttList.add(tt);
				}
			}	
		}
		return ttList;
	}
	
	
	@Override
	public boolean equals(Object object){
		Timetable o2=(Timetable)object;
		if (this.meetings.containsAll(o2.meetings)&&o2.meetings.containsAll(this.meetings))
			return true;
		return false;
	}
	
	@Override
	public String toString(){
		String result = "";
		for (Meeting m:this.meetings)
			result+=m.toString()+"\n";
		return result;
	}
	
}
