package timetableGen.meeting;

import java.text.ParseException;


public class Lecture extends Meeting
{

	public Lecture(int crn, String sessionType, Day dayOfWeek,
			String startDateTimeString, String endDateTimeString,
			String campus, String room, String instructor)
			throws ParseException {
		super(crn, sessionType, dayOfWeek, startDateTimeString, endDateTimeString,
				campus, room, instructor);
	}
}

