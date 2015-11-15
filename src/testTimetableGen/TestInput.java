package testTimetableGen;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import timetableGen.Input;
import timetableGen.meeting.Course;

public class TestInput {

	@Test
	public void test() {
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		Input i = new Input(f);
		ArrayList<Course> courses = i.parse();
		assertEquals(3, courses.size());
	}

}
