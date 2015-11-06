package timetableGen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Meeting implements Comparable<Meeting>{
	private Day day;
	private Date startDateTime;
	private Date endDateTime;
	
	public Meeting(Day dayOfWeek, String startDateTimeString, String endDateTimeString) throws ParseException{
		String s = "2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println(s);
		
		this.startDateTime = sdf.parse(s+" "+startDateTimeString);
		this.endDateTime = sdf.parse(s+" "+endDateTimeString);
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
