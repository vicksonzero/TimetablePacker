package timetableGen.Rating;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import timetableGen.Day;
import timetableGen.meeting.Meeting;

public class RateHandler {
	private ArrayList<Meeting> meetingList;
	
	public RateHandler(ArrayList<Meeting> m){
		this.meetingList=m;
	}
	
	public int rate(){
		int score=0;
		if(this.meetingList!=null)
			score=calcDays()+timeDiff()+countMorningLesson()+countNightLesson();
		else
			score=0;
		
		return score;
	}
	
	private int calcDays(){
		int[] week=new int[7];
		for (Meeting e:this.meetingList){
			Day day = e.getDay();
			week[day.getValue()-1]=1;
		}
		return week[0]+week[1]+week[2]+week[3]+week[4]+week[5]+week[6];
	}
	
	/**
	 * returns time to be spent on the same day
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private int timeDiff(){
			
		Date firstClass[]=new Date[7];	//startTime of first class of the day
		Date lastClass[]=new Date[7];	//startTime of the last class of the day
		int firstLength[]=new int[7];   //length of the first class of the day
		int lastLength[]=new int[7];   //length of the last class of the day
		int duration[]=new int[7];   //total length of lesson between the first and last class
		
		for (Meeting e: meetingList){
			int dayInArray=e.getDay().getValue()-1;
			int diff=(int)(e.getEndDateTime().getTime()-e.getStartDateTime().getTime())/60;
			///////////////////////////////////////////////////////////
			//consider case:
			//1. if first is null: update firstLength to e.length, firstClss to e.startTime
			//2. if end is null:
			//3. if before first: update duration(+old firstLength), firstLength to e.length, firstClss to e.startTime
			//4. if after last
			//else, in between
			///////////////////////////////////////////////////////////////////
			if(firstClass[dayInArray]==null){
				 firstLength[dayInArray]=diff;
				 firstClass[dayInArray]=e.getStartDateTime();
			 }else if(lastClass[dayInArray]==null){
				 lastLength[dayInArray]=diff;
				 lastClass[dayInArray]=e.getStartDateTime();
			 }else if (e.getStartDateTime().before(firstClass[dayInArray])){
				duration[dayInArray]=+firstLength[dayInArray];
				firstLength[dayInArray]=diff;
				firstClass[dayInArray]=e.getStartDateTime();
			 }else if(e.getStartDateTime().after(lastClass[dayInArray])){
				duration[dayInArray]=+lastLength[dayInArray];
				lastLength[dayInArray]=diff;
				lastClass[dayInArray]=e.getStartDateTime();		
			 }else {
				 duration[dayInArray]=+diff;
			 }
		}
		
		return duration[0]+duration[1]+duration[2]+duration[3]+duration[4]+duration[5]+duration[6];
	}
	
	
	/**
	 * counts morning lessons of a week
	 * @return the number of lessons during the week that starts at 9am
	 */
	private int countMorningLesson(){
		int counter=0;
		 Calendar cal = Calendar.getInstance();
		 
		for (Meeting e: meetingList){
			cal.setTime(e.getStartDateTime());
			if (cal.get(Calendar.HOUR_OF_DAY)<10)
				counter++;
		}
		
		return counter;
	}

	/**
	 * counts morning lessons of a week
	 * @return the number of lessons during the week that ends at 6pm
	 */
	private int countNightLesson(){
		int counter=0;
		 Calendar cal = Calendar.getInstance();
		 
		for (Meeting e: meetingList){
			cal.setTime(e.getStartDateTime());
			if (cal.get(Calendar.HOUR_OF_DAY)>18)
				counter++;
		}
		return counter;
	}
	

}
