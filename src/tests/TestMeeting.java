package tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import timetableGen.Course;
import timetableGen.exception.WrongFormatException;
import timetableGen.meeting.Day;
import timetableGen.meeting.Lecture;
import timetableGen.meeting.Meeting;
import timetableGen.meeting.Tutorial;


public class TestMeeting {

	@Test
	public void testToString() throws ParseException, WrongFormatException {
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=Meeting.create(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor)
			.setCourse(new Course("GE2324"));
		
		//CRN: 45069 | Section: T02 | Day: Mon | Time: 09:00-09:50 | Campus: MMW | Room: 1411 | Instructor: LI Shuaicheng
		assertEquals("CRN: 45069 | Course: GE2324 | Session: T02 | Day: Mon | Time: 09:00-09:50 | Campus: MMW | Room: 1411 | Instructor: LI Shuaicheng",m.toString());
		
	}
	
	
	@Test
	public void testGetDay() throws ParseException, WrongFormatException {
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m = Meeting.create(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		assertEquals(Day.MONDAY,m.getDay());

	}
	
	@Test
	public void testGetSessionType() throws ParseException, WrongFormatException {
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=Meeting.create(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		assertEquals("T02",m.getSessionType());

	}
	
	@Test
	public void testGetStartTime() throws ParseException, WrongFormatException {
		int crn=45069;
		String sessionType="T02";
		Day dayOfWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=Meeting.create(crn,  sessionType, dayOfWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String s="2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();
		assertEquals(sdf.parse(s+" "+startTimeString),m.getStartDateTime());
	}
	
	
	@Test
	public void testGetEndTime() throws ParseException, WrongFormatException {
		int crn=45069;
		String sessionType="T02";
		Day dayOfWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=Meeting.create(crn,  sessionType, dayOfWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String s="2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();
		assertEquals(sdf.parse(s+" "+endTimeString),m.getEndDateTime());
	}
	
	@Test 
	public void testCompareTo() throws ParseException, WrongFormatException {
		Meeting m1 = Meeting.create(0, "T01",Day.TUESDAY, "09:00", "12:50",null, null, null);
		Meeting m2 = Meeting.create(0, "T01",Day.WEDNESDAY, "10:00", "11:50",null, null, null);
		assertEquals(-1,m1.compareTo(m2));
	}

	@Test 
	public void testEqual() throws ParseException, WrongFormatException {
		Meeting m1 = Meeting.create(0, "T01",Day.TUESDAY, "09:00", "12:50",null, null, null);
		Meeting m2 = Meeting.create(0, "T01",Day.TUESDAY, "09:00", "12:50",null, null, null);
		assertEquals(true,m1.equals(m2));
	}

	@Test 
	public void testCreate() throws ParseException, WrongFormatException {
		Meeting m1 = Meeting.create(0, "C01",Day.TUESDAY, "09:00", "12:50",null, null, null);
		Meeting m2 = Meeting.create(0, "T01",Day.TUESDAY, "09:00", "12:50",null, null, null);
		assertTrue(m1 instanceof Lecture);
		assertTrue(m2 instanceof Tutorial);
	}

	@Test (expected=WrongFormatException.class)
	public void testCreateWrongInput() throws ParseException, WrongFormatException {
		Meeting.create(0, "x01",Day.TUESDAY, "09:00", "12:50",null, null, null);
		fail();
	}
	
}
