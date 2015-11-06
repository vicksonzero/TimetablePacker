package timetableGen;

import java.util.Date;


public class Meeting {
	private int crn;
    private String sessionType;
    private String startTime;
    private String endTime;
    private String campus;
    private String room;
    private String instructor;
	Days day;

	public Meeting(int crn, String sessionType, String starttime, String endtime, String campus, String room, String instructor)
    {
    	this.crn = crn;
    	this.sessionType = sessionType;
        this.startTime = starttime;
        this.endTime = endtime;
        this.campus = campus;
        this.room = room;
        this.instructor = instructor;
    }

    public String getSessionType()
    {
        return sessionType;
    }

    @Override
    public String toString() 
    {
        return crn + " "+ sessionType + " "+ startTime + " " + endTime + " "+ campus + " "+ room + " "+ instructor;
    }
    
	public Days getDay() {
		return null;
	}

	public Date getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getEndTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
