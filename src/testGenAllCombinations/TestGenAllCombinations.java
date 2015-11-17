package testGenAllCombinations;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import timetableGen.Course;
import timetableGen.Day;
import timetableGen.Lecture;
import timetableGen.MeetingSet;
import timetableGen.Tutorial;

public class TestGenAllCombinations {
	
	@Test 
	public void testGenMeetingSet(){
		
		Tutorial T1 = new Tutorial(Day.MONDAY, 9, 10);
		Tutorial T2 = new Tutorial(Day.MONDAY, 10, 11);
		Lecture L1 = new Lecture(Day.MONDAY, 11, 12);
		Lecture L2 = new Lecture(Day.MONDAY, 12, 13);
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
		boolean result = false;
		
		MeetingSetinACourse = c1.generateMeetingSet(m1, m2);
		for( int i=0; i<MeetingSetinACourse.size(); i++){
			if(MeetingSetinACourse.get(i).equals(ExpectedList.get(i))){
			result = true;
			}
		}
			
		assertEquals(result, true);//should be true
	}
			

}
