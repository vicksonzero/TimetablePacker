package timetableGen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Meeting implements Comparable<Meeting>{
	private int crn;
    private String sessionType;
    private String campus;
    private String room;
    private String instructor;
	Days day;
	private Day day;
	private Calendar startDateTime;
	private Calendar endDateTime;
	
	public Meeting(int crn, String sessionType,Day dayOfWeek, int startHour, int endHour, String campus, String room, String instructor){
		this.crn = crn;
    	this.sessionType = sessionType;
        this.campus = campus;
        this.room = room;
        this.instructor = instructor; 
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = new GregorianCalendar();
		c.set(2014, 9, 1);
		
		c.set(Calendar.DAY_OF_MONTH, dayOfWeek.getValue());
		System.out.println(sdf.format(c.getTime()));

		c.set(Calendar.HOUR, startHour);
		this.startDateTime = c;
		
		c = (Calendar) c.clone();
		c.set(Calendar.HOUR, endHour);
		this.endDateTime = c;
	}

	public Day getDay() {
		return this.day;
	}
	
	public String getSessionType()
    {
        return sessionType;
    }
	
	@Override
    public String toString() 
    {
        return "CRN: "+ crn + " Sesion: "+ sessionType + " Time: "+ startTime + " - " + endTime + " Campus: "+ campus + " Room: "+ room + " Instructor: "+ instructor;
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


