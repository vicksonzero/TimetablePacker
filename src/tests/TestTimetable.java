package tests;



import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import timetableGen.Course;
import timetableGen.meeting.Day;
import timetableGen.meeting.Lecture;
import timetableGen.meeting.Meeting;
import timetableGen.meeting.Tutorial;
import timetableGen.timetable.*;

public class TestTimetable {

	@Test
	public void testOneDay() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		
		m = Meeting.create(0, "T01", Day.MONDAY, "09:00", "10:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.MONDAY, "11:00", "12:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.MONDAY, "13:00", "13:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.MONDAY, "14:00", "15:50", null, null, null);
		tt.getMeetings().add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}
	
	@Test
	public void testOneDayWithConflicts() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		
		m = Meeting.create(0, "T01", Day.MONDAY, "09:00", "10:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.MONDAY, "10:00", "12:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.MONDAY, "13:00", "13:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.MONDAY, "14:00", "15:50", null, null, null);
		tt.getMeetings().add(m);
		
		assertTrue("timetable should have conflicts", tt.hasConflict());
	}

	@Test
	public void testDifferentDays() throws ParseException {
		Timetable tt = new Timetable();
		Meeting m;
		
		
		m = Meeting.create(0, "T01", Day.MONDAY, "09:00", "10:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.MONDAY, "11:00", "12:00", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.TUESDAY, "10:00", "11:50", null, null, null);
		tt.getMeetings().add(m);

		m = Meeting.create(0, "T01", Day.TUESDAY, "12:00", "15:00", null, null, null);
		tt.getMeetings().add(m);
		
		assertTrue("timetable should have no conflicts", !tt.hasConflict());
	}

	@Test
	public void testDifferentDaysWithConflicts() {
		Timetable tt = new Timetable();
		Meeting m;
		
		try {
			m = Meeting.create(0, "T01", Day.MONDAY, "09:00","10:50", null, null, null);
			tt.getMeetings().add(m);

			m = Meeting.create(0, "T01", Day.MONDAY, "11:00", "12:00", null, null, null);
			tt.getMeetings().add(m);

			m = Meeting.create(0, "T01", Day.TUESDAY, "10:00", "11:50", null, null, null);
			tt.getMeetings().add(m);

			m = Meeting.create(0, "T01", Day.TUESDAY, "11:00", "15:00", null, null, null);
			tt.getMeetings().add(m);
			
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
			m = Meeting.create(0, "T01", Day.MONDAY, "10:00", "11:50", null, null, null);
			tt1.getMeetings().add(m);
			m = Meeting.create(0, "T01",Day.TUESDAY, "10:00", "11:50", null, null, null);
			tt1.getMeetings().add(m);		
			m = Meeting.create(0, "T01",Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			tt1.getMeetings().add(m);
			m = Meeting.create(0, "T01",Day.THURSDAY, "10:00", "11:50",null, null, null);
			tt1.getMeetings().add(m);		
			m = Meeting.create(0, "T01",Day.FRIDAY, "10:00", "11:50",null, null, null);
			tt1.getMeetings().add(m);	
			m = Meeting.create(0, "T01",Day.SATURDAY, "10:00", "11:50",null, null, null);
			tt1.getMeetings().add(m);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int result=tt1.compareTo(tt2);
		assertEquals(result,1);
		
	}
	
	
	@Test
	public void testGenComNoTut() throws ParseException{
		//public static ArrayList<Timetable> genCom(int i,ArrayList<Course> courses){
		ArrayList<Course> courses=new ArrayList<Course>();
		Meeting m1=new Lecture(001, "C01",Day.SATURDAY, "10:00", "11:50",null, null, null);
		Meeting m2= new Lecture(002, "C02",Day.WEDNESDAY, "10:00", "11:50",null, null, null);
		//Meeting m3 = new Tutorial(003, "T01",Day.THURSDAY, "10:00", "11:50",null, null, null);
		Meeting m4=new Lecture(004, "T02",Day.FRIDAY, "10:00", "11:50",null, null, null);
		
		Course c1=new Course("12345");
		Course c2=new Course("22345");
		
		c1.addLecture((Lecture) m1)
			.addLecture((Lecture) m2);
		
		c2.addLecture((Lecture) m4);
		
		courses.add(c1);
		courses.add(c2);
		ArrayList<Timetable> result=Timetable.genCom(0, courses);
		ArrayList<Timetable> expectResult=new ArrayList<Timetable>();
		Timetable tt1=new Timetable();
		tt1.getMeetings().add(m1);
		
		tt1.getMeetings().add(m4);
		
		Timetable tt2=new Timetable();
		tt2.getMeetings().add(m2);
		
		tt2.getMeetings().add(m4);
		
		expectResult.add(tt1);
		expectResult.add(tt2);
		
		assertTrue(expectResult.containsAll(result) && result.containsAll(expectResult));
	}
	
	@Test
	public void testGenCom() throws ParseException{
		//public static ArrayList<Timetable> genCom(int i,ArrayList<Course> courses){
		ArrayList<Course> courses=new ArrayList<Course>();
		Meeting m1=new Lecture(001, "C01",Day.SATURDAY, "10:00", "11:50",null, null, null);
		Meeting m2= new Lecture(002, "C02",Day.WEDNESDAY, "10:00", "11:50",null, null, null);
		Meeting m3 = new Tutorial(003, "T01",Day.THURSDAY, "10:00", "11:50",null, null, null);
		Meeting m4 =new Lecture(004, "C02",Day.FRIDAY, "10:00", "11:50",null, null, null);
		Meeting m5 = new Tutorial(005, "T02",Day.THURSDAY, "10:00", "11:50",null, null, null);
		
		Course c1=new Course("12345");
		Course c2=new Course("22345");
		
		c1.addLecture((Lecture) m1)
			.addLecture((Lecture) m2)
			.addTutorial((Tutorial) m3);
		
		c2.addLecture((Lecture) m4)
			.addTutorial((Tutorial)m5);
		
		courses.add(c1);
		courses.add(c2);
		ArrayList<Timetable> result=Timetable.genCom(0, courses);
		ArrayList<Timetable> expectResult=new ArrayList<Timetable>();
		Timetable tt1=new Timetable();
		tt1.getMeetings().add(m1);
		tt1.getMeetings().add(m3);
		tt1.getMeetings().add(m4);
		tt1.getMeetings().add(m5);
		
		Timetable tt2=new Timetable();
		tt2.getMeetings().add(m2);
		tt2.getMeetings().add(m3);
		tt2.getMeetings().add(m4);
		tt2.getMeetings().add(m5);
		
		expectResult.add(tt1);
		expectResult.add(tt2);
		
		assertTrue(expectResult.containsAll(result) && result.containsAll(expectResult));
	}
	
	@Test
	public void testToString() throws ParseException{
		Timetable tt=new Timetable();
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=Meeting.create(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		int crn2=45070;
		Meeting m2=Meeting.create(crn2,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		tt.getMeetings().add(m);
		tt.getMeetings().add(m2);
		
		String expected=m.toString()+"\n"+m2.toString()+"\n";
		assertEquals(expected,tt.toString());
	}
	
	@Test
	public void testReturnArray() throws ParseException{
		Timetable tt=new Timetable();
		int crn=45069;
		String sessionType="T02";
		Day dayofWeek=Day.MONDAY;
		String startTimeString="09:00";
		String endTimeString="09:50";
		String campus="MMW";
		String room="1411";
		String instructor="LI Shuaicheng";
		Meeting m=Meeting.create(crn,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		int crn2=45070;
		Meeting m2=Meeting.create(crn2,  sessionType, dayofWeek,  startTimeString,  endTimeString,  campus,  room,  instructor);
		
		tt.getMeetings().add(m);
		tt.getMeetings().add(m2);
		
		ArrayList<Meeting> expected=new ArrayList<Meeting>();
		expected.add(m);
		expected.add(m2);
		
		assertEquals(tt.getMeetings(),expected);
	}

}
