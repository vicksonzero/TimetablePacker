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
	
	
	public int rating(){
		
		return 0;
		
		
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
			System.out.println("day: "+dayInArray);
			int diff=(int)(((e.getEndDateTime().getTime()-e.getStartDateTime().getTime())/1000/60+10)/60);
			System.out.println("diff: "+diff);
			
			///////////////////////////////////////////////////////////
			//consider case:
			//1. if first is null
			//       --last is not null(impossible)      >>add to first
			//       --last is null					>>add to first
			//2. if first is not null and e.StartTime before first
			//       --last also not null    >> add the old first to between
			//       --last is null          >> move old first to last
			//3. if first is not null and e.StartTime after first
			//       --last is not null
			//				-e.StartTime before last
			//				-e.StartTime after last
			//       --last is null
			///////////////////////////////////////////////////////////////////
			if(firstClass[dayInArray]==null){
				 firstLength[dayInArray]=diff;
				 firstClass[dayInArray]=e.getStartDateTime();
			}else if (e.getStartDateTime().before(firstClass[dayInArray])){
				 if (lastClass[dayInArray]!=null){
					 duration[dayInArray]+=firstLength[dayInArray];
				 }else{
					 lastLength[dayInArray]=firstLength[dayInArray];
					 lastClass[dayInArray]=firstClass[dayInArray];
				 }
				firstLength[dayInArray]=diff;
				firstClass[dayInArray]=e.getStartDateTime();
			}else if(e.getStartDateTime().after(firstClass[dayInArray])){
				if (lastClass[dayInArray]!=null){
					if (e.getStartDateTime().before(lastClass[dayInArray])){
						duration[dayInArray]+=diff;
					}else{
						duration[dayInArray]+=lastLength[dayInArray];
						lastLength[dayInArray]=diff;
						lastClass[dayInArray]=e.getStartDateTime();		
					}
				}else{
					 lastLength[dayInArray]=diff;
					 lastClass[dayInArray]=e.getStartDateTime();
				}
				
			}
		}
			
		
		int totalDiff=0;
		for (int i=0;i<7;i++){
			int dura;
			if (lastClass[i]==null){
				dura=0;
				System.out.println("dura:"+ dura);
			}else{
				dura=(int) (((lastClass[i].getTime()-firstClass[i].getTime())/1000/60+10)/60-firstLength[i]-duration[i]);
				System.out.println("duraLastFirst:"+ dura);
			}
			
			totalDiff+=dura;
		}
		
		System.out.println("result:"+ totalDiff);
		return totalDiff;
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
