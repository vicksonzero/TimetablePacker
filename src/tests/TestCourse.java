package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import timetableGen.Course;


public class TestCourse {

	@Test
	public void testCode() {
		Course c=new Course("12345");
		assertEquals("12345",c.getCode());
	}
	

}
