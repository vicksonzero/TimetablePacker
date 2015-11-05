package timetableGen.Rating;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import timetableGen.Days;
import timetableGen.Meeting;

public class rateHandler {
	private ArrayList<Meeting> meetingList;
	
	public rateHandler(ArrayList<Meeting> m){
		this.meetingList=m;
	}
	
	public int rate(){
		int score=calcDays()+timeDiff()+countMorningLesson()+countNightLesson();
		return score;
	}
	
	private int calcDays(){
	int[] week=new int[7];
		for (Meeting e:meetingList){
			Days day = e.getDay();
			switch (day) {
			  case SUNDAY:
				  week[0]=1;
				  break;
			  case MONDAY:
				  week[1]=1;
				  break;
	          case TUESDAY:
	        	  week[2]=1;
				  break;
	          case WEDNESDAY:
	        	  week[3]=1;
				  break;
	          case THURSDAY:
	        	  week[4]=1;
				  break;
	          case FRIDAY:
	        	  week[5]=1;
				  break;
	          case SATURDAY:
	        	  week[6]=1;
				  break;
			}
		}
		return week[0]+week[1]+week[2]+week[3]+week[4]+week[5]+week[6];
	}
	
	private int timeDiff(){
		String[] week={"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","FRIDAY","SATURDAY"};
		Date endTime=null;
		long diffMinutes=0;
			for (int i=0;i<7;i++){
				for (Meeting e: meetingList){
					if (week[i].equals(e.getDay().toString())){
						Date startTime=e.getStartTime();
						if(endTime!=null)
							  diffMinutes = diffMinutes+(startTime.getTime()-endTime.getTime()) / (60 * 1000) % 60; 
						endTime=e.getEndTime();
					}
				}
			}
		return (int) (diffMinutes/60);
	}
	
	private int countMorningLesson(){
		int counter=0;
		 Calendar cal = Calendar.getInstance();
		 
		for (Meeting e: meetingList){
			cal.setTime(e.getStartTime());
			if (cal.get(Calendar.HOUR_OF_DAY)<10)
				counter++;
		}
		
		return counter;
	}
	
	private int countNightLesson(){
		int counter=0;
		 Calendar cal = Calendar.getInstance();
		 
		for (Meeting e: meetingList){
			cal.setTime(e.getStartTime());
			if (cal.get(Calendar.HOUR_OF_DAY)>18)
				counter++;
		}
		return counter;
	}
	

}