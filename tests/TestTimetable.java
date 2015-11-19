

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import timetableGen.Day;
import timetableGen.Timetable;
import timetableGen.meeting.Course;
import timetableGen.meeting.Lecture;
import timetableGen.meeting.Meeting;
import timetableGen.meeting.Tutorial;

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
	
	
	@Test
	public void testGenComNoTut() throws ParseException{
		//public static ArrayList<Timetable> genCom(int i,ArrayList<Course> courses){
		ArrayList<Course> courses=new ArrayList<Course>();
		Meeting m1=new Lecture(001, "C01",Day.SATURDAY, "10:00", "11:50",null, null, null);
		Meeting m2= new Lecture(002, "C02",Day.WEDNESDAY, "10:00", "11:50",null, null, null);
		//Meeting m3 = new Tutorial(003, "T01",Day.THURSDAY, "10:00", "11:50",null, null, null);
		Meeting m4=new Lecture(004, "T02",Day.FRIDAY, "10:00", "11:50",null, null, null);
		
		Course c1=new Course();
		Course c2=new Course();
		
		c1.lectureList.add((Lecture) m1);
		c1.lectureList.add((Lecture) m2);
		
		c2.lectureList.add((Lecture) m4);
		
		courses.add(c1);
		courses.add(c2);
		ArrayList<Timetable> result=Timetable.genCom(0, courses);
		ArrayList<Timetable> expectResult=new ArrayList<Timetable>();
		Timetable tt1=new Timetable();
		tt1.meetings.add(m1);
		
		tt1.meetings.add(m4);
		
		Timetable tt2=new Timetable();
		tt2.meetings.add(m2);
		
		tt2.meetings.add(m4);
		
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
		
		Course c1=new Course();
		Course c2=new Course();
		
		c1.lectureList.add((Lecture) m1);
		c1.lectureList.add((Lecture) m2);
		c1.tutorialList.add((Tutorial) m3);
		
		c2.lectureList.add((Lecture) m4);
		c2.tutorialList.add((Tutorial)m5);
		
		courses.add(c1);
		courses.add(c2);
		ArrayList<Timetable> result=Timetable.genCom(0, courses);
		ArrayList<Timetable> expectResult=new ArrayList<Timetable>();
		Timetable tt1=new Timetable();
		tt1.meetings.add(m1);
		tt1.meetings.add(m3);
		tt1.meetings.add(m4);
		tt1.meetings.add(m5);
		
		Timetable tt2=new Timetable();
		tt2.meetings.add(m2);
		tt2.meetings.add(m3);
		tt2.meetings.add(m4);
		tt2.meetings.add(m5);
		
		expectResult.add(tt1);
		expectResult.add(tt2);
		
		assertTrue(expectResult.containsAll(result) && result.containsAll(expectResult));
	}
	
	/*@Test
	public void testGenCom0Course() throws ParseException{
		ArrayList<Course> courses=new ArrayList<Course>();
		ArrayList<Timetable> result=Timetable.genCom(0, courses);
		ArrayList<Timetable> expectResult=new ArrayList<Timetable>();
		assertTrue(expectResult.containsAll(result) && result.containsAll(expectResult));
	}*/
}
