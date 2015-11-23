package timetableGen.meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Meeting implements Comparable<Meeting>{
	private String courseCode;
	private int crn;
    private String sessionType;
    private String campus;
    private String room;
    private String instructor;
	private Day day;
	private Date startDateTime;
	private Date endDateTime;
	
	public Meeting(int crn, String courseCode, String sessionType,Day dayOfWeek, String startTimeString, String endTimeString, String campus, String room, String instructor) throws ParseException{
		this.crn = crn;
		this.courseCode = courseCode;
    	this.sessionType = sessionType;
        this.campus = campus;
        this.room = room;
        this.instructor = instructor; 
        
        String s = "2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//System.out.println(s);
		
		this.startDateTime = sdf.parse(s+" "+startTimeString);
		this.endDateTime = sdf.parse(s+" "+endTimeString);
		this.day=dayOfWeek;
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
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		int crn = this.crn;
		String course = this.courseCode,
				session = this.sessionType,
				day = this.day.toString(),
				startTime = sdf.format(this.startDateTime),
				endTime = sdf.format(this.endDateTime),
				campus = this.campus,
				room = this.room,
				instructorName = this.instructor;
		return String.format("CRN: %d | Course: %s | Session: %s | Day: %s | Time: %s-%s | Campus: %s | Room: %s | Instructor: %s", 
				crn, course, session, day, startTime, endTime, campus, room, instructorName);
        //return "CRN: "+ this.crn + " Sesion: "+ this.sessionType + " Time: "+ this.startDateTime + " - " + this.endDateTime + " Campus: "+ this.campus + " Room: "+ this.room + " Instructor: "+ this.instructor;
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
	
	@Override
	public boolean equals(Object object){
		Meeting o2=(Meeting)object;
		if (this.crn != o2.crn) return false;
		return true;
	}

}


