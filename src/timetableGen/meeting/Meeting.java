package timetableGen.meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import timetableGen.Day;

public class Meeting implements Comparable<Meeting>{
	private int crn;
    private String sessionType;
    private String campus;
    private String room;
    private String instructor;
	private Day day;
	private Date startDateTime;
	private Date endDateTime;
	
	public Meeting(int crn, String sessionType,Day dayOfWeek, String startTimeString, String endTimeString, String campus, String room, String instructor) throws ParseException{
		this.crn = crn;
    	this.sessionType = sessionType;
        this.campus = campus;
        this.room = room;
        this.instructor = instructor; 
        
        String s = "2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println(s);
		
		this.startDateTime = sdf.parse(s+" "+startTimeString);
		this.endDateTime = sdf.parse(s+" "+endTimeString);
	
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
        return "CRN: "+ this.crn + " Sesion: "+ this.sessionType + " Time: "+ this.startDateTime + " - " + this.endDateTime + " Campus: "+ this.campus + " Room: "+ this.room + " Instructor: "+ this.instructor;
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


