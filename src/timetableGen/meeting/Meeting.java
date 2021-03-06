package timetableGen.meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import timetableGen.Course;
import timetableGen.EmptyCourse;
import timetableGen.exception.WrongFormatException;
/**
 * Meeting class is the class structure to hold information of a lesson no matter it is lecture or tutorials,
 * This class contains instance fields, constructors(for subclasses- lecture and tutorial),create method,  getter, setter and implemented compare method.
 *
 */
public abstract class Meeting implements Comparable<Meeting>{
	private Course parentCourse = EmptyCourse.getInstance();
	private int crn;
    private String sessionType;
    private String campus;
    private String room;
    private String instructor;
	private Day day;
	private Date startDateTime;
	private Date endDateTime;
	
	/**
	 * Subclass (Lecture and Tutorials) called this constructor.
	 * @param crn
	 * @param sessionType
	 * @param dayOfWeek
	 * @param startTimeString
	 * @param endTimeString
	 * @param campus
	 * @param room
	 * @param instructor
	 * @throws ParseException
	 */
	
	public Meeting(int crn, String sessionType,Day dayOfWeek, String startTimeString, String endTimeString, String campus, String room, String instructor) throws ParseException{
		this.crn = crn;
    	this.sessionType = sessionType;
        this.campus = campus;
        this.room = room;
        this.instructor = instructor; 
        
        String s = "2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		this.startDateTime = sdf.parse(s+" "+startTimeString);
		this.endDateTime = sdf.parse(s+" "+endTimeString);
		this.day=dayOfWeek;
	}
	/**
	 * This class read and define whether actual object lecture or tutorials should be created. Returns Lecture or Tutorial object.
	 * @param crn
	 * @param sessionType
	 * @param dayOfWeek
	 * @param startTimeString
	 * @param endTimeString
	 * @param campus
	 * @param room
	 * @param instructor
	 * @return Tutorial object or Lecture object
	 * @throws ParseException
	 * @throws WrongFormatException
	 */
	public static Meeting create(int crn, String sessionType,Day dayOfWeek, String startTimeString, String endTimeString, String campus, String room, String instructor) throws ParseException, WrongFormatException{
		if(sessionType.charAt(0) == 'C'){
			return new Lecture(crn, sessionType, dayOfWeek, startTimeString, endTimeString,
					campus, room, instructor);
		}else if(sessionType.charAt(0) == 'T'){
			return new Tutorial(crn, sessionType, dayOfWeek, startTimeString, endTimeString,
					campus, room, instructor);
		}
		throw new WrongFormatException("session type must start with C or T");
	}

	public Day getDay() {
		return this.day;
	}
	
	public String getSessionType()
    {
        return sessionType;
    }
	
	/**
	 * Return in this format :CRN: 45069 | Course: [CourseCode] | Section: T02 | Day: Mon | Time: 09:00-09:50 | Campus: MMW | Room: 1411 | Instructor: LI Shuaicheng
	 */
	@Override
    public String toString() 
    {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		int crn = this.crn;
		String course = this.parentCourse.getCode(),
				session = this.sessionType,
				day = this.day.toString(),
				startTime = sdf.format(this.startDateTime),
				endTime = sdf.format(this.endDateTime),
				campus = this.campus,
				room = this.room,
				instructorName = this.instructor;
		return String.format("CRN: %d | Course: %s | Session: %s | Day: %s | Time: %s-%s | Campus: %s | Room: %s | Instructor: %s", 
				crn, course, session, day, startTime, endTime, campus, room, instructorName);
    }
	
	public Date getStartDateTime() {
		return this.startDateTime;
	}

	public Date getEndDateTime() {
		return this.endDateTime;
	}
	
	public Meeting setCourse(Course course){
		
		this.parentCourse = course;
		return this;
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


