package testTimetableGen;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;

import timetableGen.Course;
import timetableGen.Day;
import timetableGen.Lecture;
import timetableGen.Meeting;
import timetableGen.MeetingSet;
import timetableGen.Timetable;
import timetableGen.Tutorial;

public class TestTimetable {

	@Test
	public void testOneDay() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
		
		m = new Meeting(Day.MONDAY, "09:00", "10:50");
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, "11:00", "12:50");
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, "13:00", "13:50");
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, "14:00", "15:50");
		tt.meetings.add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}
	
	@Test
	public void testOneDayWithConflicts() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
		
		m = new Meeting(Day.MONDAY, "09:00", "10:50");
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, "10:00", "12:50");
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, "13:00", "13:50");
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, "14:00", "15:50");
		tt.meetings.add(m);
		
		assertTrue("timetable should have conflicts", tt.hasConflict());
	}

	@Test
	public void testDifferentDays() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
		
		m = new Meeting(Day.MONDAY, "09:00", "10:50");
		tt.meetings.add(m);

		m = new Meeting(Day.MONDAY, "11:00", "12:00");
		tt.meetings.add(m);

		m = new Meeting(Day.TUESDAY, "10:00", "11:50");
		tt.meetings.add(m);

		m = new Meeting(Day.TUESDAY, "12:00", "15:00");
		tt.meetings.add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}

	@Test
	public void testDifferentDaysWithConflicts() {
		Timetable tt = new Timetable();
		Meeting m;
		
		try {
			m = new Meeting(Day.MONDAY, "09:00","10:50");
			tt.meetings.add(m);

			m = new Meeting(Day.MONDAY, "11:00", "12:00");
			tt.meetings.add(m);

			m = new Meeting(Day.TUESDAY, "10:00", "11:50");
			tt.meetings.add(m);

			m = new Meeting(Day.TUESDAY, "11:00", "15:00");
			tt.meetings.add(m);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue("timetable should have conflicts", tt.hasConflict());
	}
	
	@Test 
	public void testGenMeetingSet(){
		
		Tutorial T1 = new Tutorial();
		Tutorial T2 = new Tutorial();
		Lecture L1 = new Lecture();
		Lecture L2 = new Lecture();
		Course c1 = new Course();
		ArrayList<Tutorial> m1 = new ArrayList<Tutorial>();
		m1.add(T1);
		m1.add(T2);
		ArrayList<Lecture> m2 = new ArrayList<Lecture>();
		m2.add(L1);
		m2.add(L2);
		ArrayList<MeetingSet> MeetingSetinACourse = new ArrayList<MeetingSet>();
		ArrayList<MeetingSet> ExpectedList = new ArrayList<MeetingSet>();
		MeetingSet meet1 = new MeetingSet(T1,L1);
		MeetingSet meet2 = new MeetingSet(T1,L2);
		MeetingSet meet3 = new MeetingSet(T2,L1);
		MeetingSet meet4 = new MeetingSet(T2,L2);
		ExpectedList.add(meet1);
		ExpectedList.add(meet2);
		ExpectedList.add(meet3);
		ExpectedList.add(meet4);
		
		MeetingSetinACourse = c1.generateMeetingSet(m1, m2);
		assertEquals(MeetingSetinACourse, ExpectedList );
			
		
	
		
		
	}

}
