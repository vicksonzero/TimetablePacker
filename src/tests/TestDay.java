package tests;

import static org.junit.Assert.*;
import timetableGen.exception.*;
import timetableGen.meeting.Day;

import org.junit.Test;


public class TestDay {
	

	@Test
	public void testSat() throws WrongFormatException {
		assertEquals(Day.SATURDAY,Day.stringToDay("S"));
	}
	
	@Test
	public void testMon() throws WrongFormatException {
		assertEquals(Day.MONDAY,Day.stringToDay("M"));
	}
	
	@Test
	public void testTue() throws WrongFormatException {
		assertEquals(Day.TUESDAY,Day.stringToDay("T"));
	}
	
	@Test
	public void testWed() throws WrongFormatException {
		assertEquals(Day.WEDNESDAY,Day.stringToDay("W"));
	}
	
	@Test
	public void testThu() throws WrongFormatException {
		assertEquals(Day.THURSDAY,Day.stringToDay("R"));
	}
	
	@Test
	public void testFri() throws WrongFormatException {
		assertEquals(Day.FRIDAY,Day.stringToDay("F"));
	}
	
	@Test(expected=WrongFormatException.class)
	public void testException() throws WrongFormatException {
		Day.stringToDay("E");
		
	}
	
	@Test(expected=WrongFormatException.class)
	public void testException2() throws WrongFormatException {
		Day.stringToDay("Su");
		
	}
	
	@Test
	public void testVal() throws WrongFormatException {
		assertEquals(1,Day.MONDAY.getValue());
	}
	
	

}
