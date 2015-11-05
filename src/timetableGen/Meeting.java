package timetableGen;

import java.util.Date;


public class Meeting implements Comparable<Meeting>{
	private Day day;
	private Date startDateTime;
	private Date endDateTime;
	
	public Meeting(Day dayOfWeek, Date startDateTime, Date endDateTime){
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public Day getDay() {
		return this.day;
	}

	public Date getStartDateTime() {
		return this.startDateTime;
	}

	public Date getEndDateTime() {
		return this.endDateTime;
	}
	

	@Override
	public int compareTo(Meeting other) {
		
		return this.getStartDateTime().compareTo(other.getStartDateTime());
	}

}
