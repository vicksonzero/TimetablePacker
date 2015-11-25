package timetableGen.rating;

import java.util.Comparator;
import java.util.Date;

import timetableGen.meeting.Meeting;
import timetableGen.timetable.Timetable;

public class CompareTimeDiff implements Comparator<Timetable> {

	@Override
	public int compare(Timetable o1, Timetable o2) {
		return timeDiff(o2) - timeDiff(o1);
	}
	/**
	 * returns time to be spent on the same day
	 * @return
	 */
	public int timeDiff(Timetable t){
			
		Date firstClass[]=new Date[7];	//startTime of first class of the day
		Date lastClass[]=new Date[7];	//startTime of the last class of the day
		int firstLength[]=new int[7];   //length of the first class of the day
		int lastLength[]=new int[7];   //length of the last class of the day
		int duration[]=new int[7];   //total length of lesson between the first and last class
		
		
		for (Meeting e: t.getMeetings()){
			int dayInArray=e.getDay().getValue()-1;
			int diff=(int)(((e.getEndDateTime().getTime()-e.getStartDateTime().getTime())/1000/60+10)/60);

			
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
			}else{
				dura=(int) (((lastClass[i].getTime()-firstClass[i].getTime())/1000/60+10)/60-firstLength[i]-duration[i]);		
			}
			
			totalDiff+=dura;
		}
		
		
		return totalDiff;
		
	}

}
