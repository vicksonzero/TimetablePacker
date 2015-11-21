import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import timetableGen.Day;
import timetableGen.meeting.Meeting;


public class TestMeeting {

	@Test
	public void testToString() throws ParseException {
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=new Meeting(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		//CRN: 45069 | Section: T02 | Day: Mon | Time: 09:00-09:50 | Campus: MMW | Room: 1411 | Instructor: LI Shuaicheng
		assertEquals("CRN: 45069 | Course: GE2324 | Section: T02 | Day: Mon | Time: 09:00-09:50 | Campus: MMW | Room: 1411 | Instructor: LI Shuaicheng",m.toString());
		
	}
	
	
	@Test
	public void testGetDay() throws ParseException {
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=new Meeting(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		assertEquals(Day.MONDAY,m.getDay());

	}
	
	@Test
	public void testGetSessionType() throws ParseException {
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=new Meeting(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		assertEquals("T02",m.getSessionType());

	}
	
	@Test
	public void testGetStartTime() throws ParseException {
		int crn=45069;
		String sessionType="T02";
		Day dayOfWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=new Meeting(crn,  sessionType, dayOfWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String s="2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();
		assertEquals(sdf.parse(s+" "+startTimeString),m.getStartDateTime());
	}
	
	
	@Test
	public void testGetEndTime() throws ParseException {
		int crn=45069;
		String sessionType="T02";
		Day dayOfWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=new Meeting(crn,  sessionType, dayOfWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String s="2014-09-0";
		s+=new Integer(dayOfWeek.getValue()).toString();
		assertEquals(sdf.parse(s+" "+endTimeString),m.getEndDateTime());
	}
	
	@Test 
	public void testCompareTo() throws ParseException {
		Meeting m1 = new Meeting(0, null,Day.TUESDAY, "09:00", "12:50",null, null, null);
		Meeting m2 = new Meeting(0, null,Day.WEDNESDAY, "10:00", "11:50",null, null, null);
		assertEquals(-1,m1.compareTo(m2));
	}

	@Test 
	public void testEqual() throws ParseException {
		Meeting m1 = new Meeting(0, null,Day.TUESDAY, "09:00", "12:50",null, null, null);
		Meeting m2 = new Meeting(0, null,Day.TUESDAY, "09:00", "12:50",null, null, null);
		assertEquals(true,m1.equals(m2));
	}
	
}
