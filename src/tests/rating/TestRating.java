package tests.rating;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import timetableGen.exception.WrongFormatException;
import timetableGen.meeting.Day;
import timetableGen.meeting.Meeting;
import timetableGen.rating.RateHandler;
import timetableGen.timetable.Timetable;

public class TestRating {


	@Test
	public void testRating() throws ParseException, WrongFormatException {
		Meeting m ;
		Timetable t1 = new Timetable();
		Timetable t2 = new Timetable();
		
		
		m = Meeting.create(0, "T01", Day.MONDAY, "10:00", "11:50", null, null, null);
		t1.getMeetings().add(m);
		// check compare function
		RateHandler c = new RateHandler();
		assertTrue(c.compare(t1, t2)>0);
		
		// check sorting
		ArrayList<Timetable> timetables = new ArrayList<Timetable>();
		timetables.add(t1);
		timetables.add(t2);
		Collections.sort(timetables,c);
		assertTrue(timetables.get(0).equals(t2));
		assertTrue(timetables.get(1).equals(t1));
	}

}
