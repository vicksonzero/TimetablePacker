package tests.rating;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import timetableGen.exception.WrongFormatException;
import timetableGen.meeting.Day;
import timetableGen.meeting.Meeting;
import timetableGen.rating.CompareTimeDiff;
import timetableGen.timetable.Timetable;

public class TestCompareTimeDiff {

	@Test
	public void testCompareFunction() throws ParseException, WrongFormatException {
		Meeting m ;
		Timetable t1 = new Timetable();
		Timetable t2 = new Timetable();
		Timetable t3 = new Timetable();
		
		
		m = Meeting.create(0, "T01", Day.MONDAY, "10:00", "10:50", null, null, null);
		t1.getMeetings().add(m);

		m = Meeting.create(0, "T02", Day.MONDAY, "13:00", "14:50", null, null, null);
		t1.getMeetings().add(m);
		
		m = Meeting.create(0, "T01",Day.MONDAY, "10:00", "10:50", null, null, null);
		t2.getMeetings().add(m);

		m = Meeting.create(0, "T02", Day.MONDAY, "12:00", "12:50", null, null, null);
		t2.getMeetings().add(m);
		
		// check compare function
		CompareTimeDiff c = new CompareTimeDiff();
		assertTrue(c.compare(t1, t2)>0);
		
		
		
		// check sorting
		
		// add a t3
		m = Meeting.create(0, "T01",Day.MONDAY, "10:00", "10:50", null, null, null);
		t3.getMeetings().add(m);

		m = Meeting.create(0, "T02", Day.MONDAY, "11:00", "12:50", null, null, null);
		t3.getMeetings().add(m);
		
		
		ArrayList<Timetable> timetables = new ArrayList<Timetable>();
		timetables.add(t1);
		timetables.add(t2);
		timetables.add(t3);
		Collections.sort(timetables,c);
		assertTrue(timetables.get(0).equals(t3));
		assertTrue(timetables.get(1).equals(t2));
		assertTrue(timetables.get(2).equals(t1));
	}

}
