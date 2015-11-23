package timetableGen.meeting;

import java.text.ParseException;

public class Tutorial extends Meeting
{

	public Tutorial(int crn, String courseCode, String sessionType, Day dayOfWeek,
			String startDateTimeString, String endDateTimeString,
			String campus, String room, String instructor)
			throws ParseException {
		super(crn, courseCode, sessionType, dayOfWeek, startDateTimeString, endDateTimeString,
				campus, room, instructor);
		// TODO Auto-generated constructor stub
	}
}