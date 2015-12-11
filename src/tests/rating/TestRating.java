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
import timetableGen.rating.TimetableComparator;
import timetableGen.timetable.Timetable;

public class TestRating {


	@Test
	public void testRating() throws ParseException, WrongFormatException {
		class CompAlwaysEqual extends TimetableComparator{
			@Override
			public int compare(Timetable o1, Timetable o2) {
				return 0;
			}
		}
		class CompAlwaysLessThan extends TimetableComparator{
			public boolean methodIsCalled = false;
			@Override
			public int compare(Timetable o1, Timetable o2) {
				this.methodIsCalled = true;
				return -1;
			}
		}
		
		
		Meeting m ;
		Timetable t1 = new Timetable();
		Timetable t2 = new Timetable();
		
		
		// check compare function
		RateHandler c = new RateHandler();
		CompAlwaysEqual cae = new CompAlwaysEqual();
		c.addComparator(cae);
		CompAlwaysLessThan calt = new CompAlwaysLessThan();
		c.addComparator(calt);
		
		assertTrue(c.compare(t1, t2)<0);
		assertTrue(calt.methodIsCalled);
		
	}

}
