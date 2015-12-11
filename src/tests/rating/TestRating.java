package tests.rating;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;





import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import timetableGen.Course;
import timetableGen.exception.WrongFormatException;
import timetableGen.meeting.Day;
import timetableGen.meeting.Meeting;
import timetableGen.rating.*;
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
	
	
	@Test
	public void testPerference() throws ParseException, WrongFormatException{
		
		RateHandler handler=new RateHandler();
		handler.addComparator(new CompareDayCount());
		handler.addComparator(new CompareMorningLessons());
		handler.addComparator(new CompareEveningLessons());
		handler.addComparator(new CompareTimeDiff());
		
		//Preference as Fewest days> Fewest Morning Lessons > Fewest Evening Lessions > Shortest days
		Course course1=new Course("CS3342");
		Course course2=new Course("CS3333");
		Course course3=new Course("CS3334");
		Meeting m;
		Timetable tt1=new Timetable();
		m=Meeting.create(41706,"C01", Day.MONDAY,  "09:00", "10:50",  null,  null,  null).setCourse(course1);
		
		tt1.getMeetings().add(m);
		m=Meeting.create(41711,"T01", Day.MONDAY,  "12:00", "12:50",  null,  null,  null).setCourse(course1);	
		tt1.getMeetings().add(m);
		m=Meeting.create(41701,"C01", Day.MONDAY,  "13:00", "13:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41712,"T01", Day.MONDAY,  "14:00", "14:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41116,"C01", Day.MONDAY,  "20:00", "21:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41745,"T01", Day.MONDAY,  "16:00", "16:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		
		
		Timetable tt2=new Timetable();
		m=Meeting.create(41706,"C01", Day.MONDAY,  "09:00", "10:50",  null,  null,  null).setCourse(course1);		
		tt2.getMeetings().add(m);
		m=Meeting.create(41711,"T02", Day.TUESDAY,  "20:00", "21:50",  null,  null,  null).setCourse(course1);		
		tt2.getMeetings().add(m);
		m=Meeting.create(41701,"C01", Day.MONDAY,  "13:00", "13:50",  null,  null,  null).setCourse(course2);		
		tt2.getMeetings().add(m);
		m=Meeting.create(41712,"T01", Day.MONDAY,  "14:00", "14:50",  null,  null,  null).setCourse(course2);		
		tt2.getMeetings().add(m);
		m=Meeting.create(41116,"C01", Day.MONDAY,  "20:00", "21:50",  null,  null,  null).setCourse(course3);		
		tt2.getMeetings().add(m);
		m=Meeting.create(41745,"T01", Day.MONDAY,  "16:00", "16:50",  null,  null,  null).setCourse(course3);		
		tt2.getMeetings().add(m);
		
		Timetable tt3=new Timetable();
		m=Meeting.create(41706,"C01", Day.MONDAY,  "09:00", "10:50",  null,  null,  null).setCourse(course1);		
		tt3.getMeetings().add(m);
		m=Meeting.create(42741,"T03", Day.TUESDAY,  "09:00", "10:50",  null,  null,  null).setCourse(course1);		
		tt3.getMeetings().add(m);
		m=Meeting.create(41701,"C01", Day.MONDAY,  "13:00", "13:50",  null,  null,  null).setCourse(course2);		
		tt3.getMeetings().add(m);
		m=Meeting.create(41712,"T01", Day.MONDAY,  "14:00", "14:50",  null,  null,  null).setCourse(course2);		
		tt3.getMeetings().add(m);
		m=Meeting.create(41116,"C01", Day.MONDAY,  "20:00", "21:50",  null,  null,  null).setCourse(course3);		
		tt3.getMeetings().add(m);
		m=Meeting.create(41745,"T01", Day.MONDAY,  "16:00", "16:50",  null,  null,  null).setCourse(course3);		
		tt3.getMeetings().add(m);
		
		ArrayList<Timetable> timetables=new ArrayList<Timetable>();
		timetables.add(tt3);
		timetables.add(tt2);
		timetables.add(tt1);
		
		ArrayList<Timetable> timetablesExpected=new ArrayList<Timetable>();
		timetablesExpected.add(tt1);
		timetablesExpected.add(tt2);
		timetablesExpected.add(tt3);
		
		Collections.sort(timetables, handler);
		
		boolean sameSeq=true;
		for (int i=0;i<3;i++){
			if (! timetables.get(i).equals(timetablesExpected.get(i)))
				sameSeq=false;	
		}
		
		assertTrue("Should be same seq of objects",sameSeq);
		
		}
	
	
	
	@Test
	public void testPerference2() throws ParseException, WrongFormatException{
		
		RateHandler handler=new RateHandler();
		handler.addComparator(new CompareTimeDiff());
		handler.addComparator(new CompareEveningLessons());
		handler.addComparator(new CompareMorningLessons());
		handler.addComparator(new CompareDayCount());
		
	
		//Preference as Shortest days> Fewest Evening Lessons >Fewest Morning Lessons >  Fewest days
		Course course1=new Course("CS3342");
		Course course2=new Course("CS3333");
		Course course3=new Course("CS3334");
		Meeting m;
		Timetable tt1=new Timetable();
		m=Meeting.create(41706,"C01", Day.MONDAY,  "11:00", "11:50",  null,  null,  null).setCourse(course1);
		tt1.getMeetings().add(m);
		m=Meeting.create(41711,"T01", Day.TUESDAY,  "12:00", "12:50",  null,  null,  null).setCourse(course1);	
		tt1.getMeetings().add(m);
		m=Meeting.create(41701,"C01", Day.WEDNESDAY,  "13:00", "13:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41712,"T01", Day.THURSDAY,  "14:00", "14:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41116,"C01", Day.FRIDAY,  "15:00", "15:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41745,"T01", Day.SATURDAY,  "16:00", "16:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		
		
		Timetable tt2=new Timetable();
		m=Meeting.create(41706,"C01", Day.MONDAY,  "11:00", "11:50",  null,  null,  null).setCourse(course1);
		tt1.getMeetings().add(m);
		m=Meeting.create(41718,"T02", Day.MONDAY,  "13:00", "14:50",  null,  null,  null).setCourse(course1);	
		tt1.getMeetings().add(m);
		m=Meeting.create(41701,"C01", Day.WEDNESDAY,  "13:00", "13:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41712,"T01", Day.THURSDAY,  "14:00", "14:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41116,"C01", Day.FRIDAY,  "15:00", "15:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41745,"T01", Day.SATURDAY,  "16:00", "16:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		
		Timetable tt3=new Timetable();
		m=Meeting.create(41706,"C01", Day.MONDAY,  "11:00", "11:50",  null,  null,  null).setCourse(course1);
		tt1.getMeetings().add(m);
		m=Meeting.create(41711,"T01", Day.TUESDAY,  "12:00", "12:50",  null,  null,  null).setCourse(course1);	
		tt1.getMeetings().add(m);
		m=Meeting.create(41701,"C01", Day.WEDNESDAY,  "13:00", "13:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41712,"T01", Day.THURSDAY,  "14:00", "14:50",  null,  null,  null).setCourse(course2);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41116,"C01", Day.FRIDAY,  "15:00", "15:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		m=Meeting.create(41746,"T02", Day.FRIDAY,  "20:00", "21:50",  null,  null,  null).setCourse(course3);		
		tt1.getMeetings().add(m);
		
		ArrayList<Timetable> timetables=new ArrayList<Timetable>();
		timetables.add(tt3);
		timetables.add(tt2);
		timetables.add(tt1);
		
		ArrayList<Timetable> timetablesExpected=new ArrayList<Timetable>();
		timetablesExpected.add(tt1);
		timetablesExpected.add(tt2);
		timetablesExpected.add(tt3);
		
		Collections.sort(timetables, handler);
		
		boolean sameSeq=true;
		for (int i=0;i<3;i++){
			if (! timetables.get(i).equals(timetablesExpected.get(i)))
				sameSeq=false;	
		}
		
		assertTrue("Should be same seq of objects",sameSeq);
		
		}
}
		

