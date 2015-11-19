

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import timetableGen.Day;
import timetableGen.Timetable;
import timetableGen.meeting.Meeting;

public class TestTimetable {

	@Test
	public void testOneDay() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		
		m = new Meeting(0, null, Day.MONDAY, "09:00", "10:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.MONDAY, "11:00", "12:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.MONDAY, "13:00", "13:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.MONDAY, "14:00", "15:50", null, null, null);
		tt.meetings.add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}
	
	@Test
	public void testOneDayWithConflicts() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		
		m = new Meeting(0, null, Day.MONDAY, "09:00", "10:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.MONDAY, "10:00", "12:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.MONDAY, "13:00", "13:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.MONDAY, "14:00", "15:50", null, null, null);
		tt.meetings.add(m);
		
		assertTrue("timetable should have conflicts", tt.hasConflict());
	}

	@Test
	public void testDifferentDays() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		
		m = new Meeting(0, null, Day.MONDAY, "09:00", "10:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.MONDAY, "11:00", "12:00", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.TUESDAY, "10:00", "11:50", null, null, null);
		tt.meetings.add(m);

		m = new Meeting(0, null, Day.TUESDAY, "12:00", "15:00", null, null, null);
		tt.meetings.add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}

	@Test
	public void testDifferentDaysWithConflicts() {
		Timetable tt = new Timetable();
		Meeting m;
		
		try {
			m = new Meeting(0, null, Day.MONDAY, "09:00","10:50", null, null, null);
			tt.meetings.add(m);

			m = new Meeting(0, null, Day.MONDAY, "11:00", "12:00", null, null, null);
			tt.meetings.add(m);

			m = new Meeting(0, null, Day.TUESDAY, "10:00", "11:50", null, null, null);
			tt.meetings.add(m);

			m = new Meeting(0, null, Day.TUESDAY, "11:00", "15:00", null, null, null);
			tt.meetings.add(m);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue("timetable should have conflicts", tt.hasConflict());
	}

	
	@Test
	public void testCompareTo(){
		Timetable tt1 = new Timetable(); //score=0
		Timetable tt2 = new Timetable();  //score=6
		Meeting m;
		
		try{
			m = new Meeting(0, null, Day.MONDAY, "10:00", "11:50", null, null, null);
			tt1.meetings.add(m);
			m = new Meeting(0, null,Day.TUESDAY, "10:00", "11:50", null, null, null);
			tt1.meetings.add(m);		
			m = new Meeting(0, null,Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			tt1.meetings.add(m);
			m = new Meeting(0, null,Day.THURSDAY, "10:00", "11:50",null, null, null);
			tt1.meetings.add(m);		
			m = new Meeting(0, null,Day.FRIDAY, "10:00", "11:50",null, null, null);
			tt1.meetings.add(m);	
			m = new Meeting(0, null,Day.SATURDAY, "10:00", "11:50",null, null, null);
			tt1.meetings.add(m);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int result=tt1.compareTo(tt2);
		assertEquals(result,1);
		
	}
}
