package tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import timetableGen.*;
import timetableGen.Rating.*;
import timetableGen.meeting.Meeting;

public class TestRating {

/*	@Test
	//No Lesson
	public void testNoLesson() {
		Meeting m ;
		ArrayList<Meeting> meetingList = null;
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,0);		
	}*/
	
	@SuppressWarnings("null")
	@Test
	//One Day Off; No Time Diff; No Morning lesson; No Night Lesson
	public void testOneDayOff() {
		Meeting m ;
		ArrayList<Meeting> meetingList=new ArrayList<Meeting>();
		
		try {
			m = new Meeting(0, null, Day.MONDAY, "10:00", "11:50", null, null, null);
			meetingList.add(m);
			System.out.println("1");
			m = new Meeting(0, null,Day.TUESDAY, "10:00", "11:50", null, null, null);
			meetingList.add(m);
			System.out.println("2");	
			m = new Meeting(0, null,Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			System.out.println("3");
			m = new Meeting(0, null,Day.THURSDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			System.out.println("4");
			m = new Meeting(0, null,Day.FRIDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			System.out.println("5");
			m = new Meeting(0, null,Day.SATURDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			System.out.println("6");
			
		} catch (ParseException e) {
			System.out.println("bug!");
			e.printStackTrace();
		}
		
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,6+0+0+0);		
	}
	
	//No Day Off; No Time Diff; No Morning lesson; Night Lesson
	public void testMorningLesson() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		
		
		try{
			m = new Meeting(0, null, Day.MONDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.TUESDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.WEDNESDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.THURSDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.FRIDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.SATURDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.SUNDAY, "09:00", "11:50",null, null, null);
			meetingList.add(m);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,7+0+7+0);		
	}
	
	//No Day Off; Time Diff; No Morning lesson; No Night Lesson
	public void testTimeDiff() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		
		try {
			m = new Meeting(0, null, Day.MONDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.MONDAY, "13:00", "14:50",null, null, null);;
			meetingList.add(m);
			m = new Meeting(0, null, Day.TUESDAY, "10:00", "11:50",null, null, null);;
			meetingList.add(m);
			m = new Meeting(0, null, Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.THURSDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.FRIDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.SATURDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null, Day.SUNDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,7+1+0+0);		
	}
	
	//No Day Off; No Morning lesson;No Night Lesson; Time Diff
	public void testNightLesson() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		
		
		try {
			m = new Meeting(0, null,Day.MONDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.TUESDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.WEDNESDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.THURSDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.FRIDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.SATURDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.SUNDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,7+0+0+7);		
	}

	//TWO Day Off; TWO Morning lesson; TWO Night Lesson; Time Diff
	public void testAll() {
		Meeting m ;
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
			
		try {
			m = new Meeting(0, null,Day.MONDAY, "09:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.TUESDAY, "09:00", "12:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.WEDNESDAY, "10:00", "11:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.WEDNESDAY, "15:00", "16:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.THURSDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
			m = new Meeting(0, null,Day.FRIDAY, "19:00", "20:50",null, null, null);
			meetingList.add(m);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		RateHandler rating = new RateHandler(meetingList);
		int score = rating.rate();
		assertEquals(score,2+3+2+2);		
	}

		
		

}
