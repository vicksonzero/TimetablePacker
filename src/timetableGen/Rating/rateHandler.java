package timetableGen.rating;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import timetableGen.Day;
import timetableGen.meeting.Meeting;

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
			Day day = e.getDay();
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
	
	/**
	 * returns time to be spent on the same day
	 * @return
	 */
	/*private int timeDiff(){
		String[] week={"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","FRIDAY","SATURDAY"};
		Date endTime=null;
		long diffMinutes=0;
			for (int i=0;i<7;i++){
				for (Meeting e: meetingList){
					if (week[i].equals(e.getDay().toString())){
						Date startTime=e.getStartDateTime();
						if(endTime!=null)
							  diffMinutes = diffMinutes+(startTime.getTime()-endTime.getTime()) / (60 * 1000) % 60; 
						endTime=e.getEndDateTime();
					}
				}
			}
		return (int) (diffMinutes/60);
	}*/
	


		private int timeDiff(){
		String[] week={"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","FRIDAY","SATURDAY"};
		Date endTime=null;
		long count = 0;
		Date LessonTime[][] = new Date [7][2];
		long LessonBetween[] = new long [7];
		long diffMinutes=0;
			for (int i=0;i<7;i++){
				for (Meeting e: meetingList){
					if (week[i].equals(e.getDay().toString())){
						Date startTime=e.getStartTime();
						if(endTime!=null)
							  diffMinutes = diffMinutes+(startTime.getTime()-endTime.getTime()) / (60 * 1000) % 60; 
						endTime=e.getEndTime();
					}
		for (int i=0;i<7;i++){
			for (Meeting e: meetingList){
				if (week[i].equals(e.getDay().toString())){
					if(e.getStartTime().before(LessonTime[i][0]))
						LessonTime[i][0] = e.getStartTime();
					else 
						if(e.getStartTime().after(LessonTime[i][1]))
							LessonTime[i][0] = e.getStartTime();
						else
							LessonBetween[i] += e.getEndTime().getTime() - e.getStartTime().getTime();
				}
			}
		}
		for(int i=0;i<7;i++){
			long FreeTime = LessonTime[i][1].getTime() - LessonTime[i][0].getTime();
			FreeTime -= LessonBetween[i];
			diffMinutes += FreeTime /(1000*60);
		}
		return (int) (diffMinutes/60);
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
