package tests;



import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import timetableGen.meeting.*;
import timetableGen.rating.*;


public class TestRating {

	@Test
	//No Lesson
	public void testNoLesson() {
		ArrayList<Meeting> meetingList=new ArrayList<Meeting>();
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,0);		
	}
	
	@Test
	//One Day Off; No Time Diff; No Morning lesson; No Night Lesson
	public void testOneDayOff() throws ParseException {
		Meeting m ;
		ArrayList<Meeting> meetingList=new ArrayList<Meeting>();
		
		
			m = new Meeting(0, "", null, Day.MONDAY, "10:00", "11:50", null, null, null);
			meetingList.add(m);
			
			m = new Meeting(0, "", null,Day.TUESDAY, "10:00", "11:50", null, null, null);
			meetingList.add(m);
			
			m = new Meeting(0, "", null,Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
		
			m = new Meeting(0, "", null,Day.THURSDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			
			m = new Meeting(0, "", null,Day.FRIDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			
			m = new Meeting(0, "", null,Day.SATURDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			
			
			RateHandler rating = new RateHandler(meetingList);
			int score = rating.rate();
			assertEquals(score,6+0+0+0);		
	}
	
	@Test
	//No Day Off; No Time Diff; No Morning lesson; Night Lesson
	public void testMorningLesson() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		
		try{
			m = new Meeting(0, "", null, Day.MONDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.TUESDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.WEDNESDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.THURSDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.FRIDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.SATURDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,6+0+6+0);		
	}
	
	
	@Test
	//No Day Off; Time Diff; No Morning lesson; No Night Lesson
	public void testTimeDiff() {
		
		//wrong test case here, please design a test case that will only have score in time difference method or by other means, do not involve other untested method.
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		
		try {
			m = new Meeting(0, "", null, Day.MONDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.MONDAY, "13:00", "14:50",null, null, null);;
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.TUESDAY, "10:00", "11:50",null, null, null);;
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.THURSDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.FRIDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null, Day.SATURDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,7);		
	}
	
	@Test
	//No Day Off; No Morning lesson; No Night Lesson; Time Diff
	public void testMoreTimeDiff() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
			
		try {
			m = new Meeting(0, "", null,Day.MONDAY, "11:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.TUESDAY, "11:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.WEDNESDAY, "11:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.THURSDAY, "11:00", "12:50",null, null, null);
			meetingList.add(m);
			//DAY HAVE TIME DIFF
			m = new Meeting(0, "", null,Day.FRIDAY, "17:00", "18:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.FRIDAY, "14:00", "14:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.FRIDAY, "11:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.FRIDAY, "15:00", "16:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.FRIDAY, "19:00", "21:50",null, null, null);
			meetingList.add(m);
			//END
			m = new Meeting(0, "", null,Day.SATURDAY, "11:00", "12:50",null, null, null);
			meetingList.add(m);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				

		//timeDiff=2
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.timeDiff();
		//assertEquals(score,7+2);		
		assertEquals(score,1);		
	}		
	
	@Test
	//No Day Off; No Morning lesson;No Night Lesson; Time Diff
	public void testNightLesson() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		
		
		try {
			m = new Meeting(0, "", null,Day.MONDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.TUESDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.WEDNESDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.THURSDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.FRIDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.SATURDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,6+0+0+6);		
	}

	
	@Test
	//TWO Day Off; TWO Morning lesson; TWO Night Lesson; Time Diff
	public void testAll() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
			
		try {
			m = new Meeting(0, "", null,Day.MONDAY, "09:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.TUESDAY, "09:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.WEDNESDAY, "15:00", "16:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.THURSDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, "", null,Day.FRIDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		//night lesson=2
		//morning lesson=2
		//timeDiff=3
		//days=5
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,5+3+2+2);		
	}

		
		

}
