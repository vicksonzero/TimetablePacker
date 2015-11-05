package testTimetableGen;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import timetableGen.Day;
import timetableGen.Meeting;
import timetableGen.Timetable;

public class TestTimetable {

	@Test
	public void testOneDay() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
		
		m = new Meeting(Day.MONDAY, sdf.parse("09:00"), sdf.parse("10:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("11:00"), sdf.parse("12:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("13:00"), sdf.parse("13:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("14:00"), sdf.parse("15:50"));
		tt.meetings.add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}
	
	@Test
	public void testOneDayWithConflicts() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
		
		m = new Meeting(Day.MONDAY, sdf.parse("09:00"), sdf.parse("10:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("10:00"), sdf.parse("12:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("13:00"), sdf.parse("13:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("14:00"), sdf.parse("15:50"));
		tt.meetings.add(m);
		
		assertTrue("timetable should have conflicts", tt.hasConflict());
	}

	@Test
	public void testDifferentDays() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
		
		m = new Meeting(Day.MONDAY, sdf.parse("09:00"), sdf.parse("10:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("11:00"), sdf.parse("12:00"));
		tt.meetings.add(m);

		m = new Meeting(Day.TUESDAY, sdf.parse("10:00"), sdf.parse("11:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.TUESDAY, sdf.parse("12:00"), sdf.parse("15:00"));
		tt.meetings.add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}

	@Test
	public void testDifferentDaysWithConflicts() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
		
		m = new Meeting(Day.MONDAY, sdf.parse("09:00"), sdf.parse("10:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, sdf.parse("11:00"), sdf.parse("12:00"));
		tt.meetings.add(m);

		m = new Meeting(Day.TUESDAY, sdf.parse("10:00"), sdf.parse("11:50"));
		tt.meetings.add(m);

		m = new Meeting(Day.TUESDAY, sdf.parse("11:00"), sdf.parse("15:00"));
		tt.meetings.add(m);
		
		assertTrue("timetable should have conflicts", tt.hasConflict());
	}

}
