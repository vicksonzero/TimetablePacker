package testTimetableGen;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import timetableGen.Input;
import timetableGen.meeting.Course;
import timetableGen.timetableGen.WrongFomatException;

public class TestInput {

	@Test
	public void testWholeInput() {
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		Input i = new Input(f);
		ArrayList<Course> courses;
		try {
			courses = i.parse();
			assertEquals(3, courses.size());
		} catch (FileNotFoundException e) {
			fail();
		} catch (WrongFomatException e) {
			fail();
		}
	}
	
	@Test
	public void testLecProcessMeeting() {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		try {
			mi.processMeetingLine(c, "41706	C01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	15:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
			assertEquals(1, c.lectureList.size());
			assertEquals(0, c.tutorialList.size());
		} catch (WrongFomatException e) {
			fail();
		}
	}
	
	@Test(expected=WrongFomatException.class)
	public void testLecProcessMeetingWrongTime() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41706	C01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	1a:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	
	@Test(expected=WrongFomatException.class)
	public void testLecProcessMeetingWrongEndTime() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41706	C01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	13:00 - 1a:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	
	@Test(expected=WrongFomatException.class)
	public void testLecProcessMeetingWrongCourseCode() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41a06	C01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	13:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	
	@Test(expected=WrongFomatException.class)
	public void testLecProcessMeetingWrongDay() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41706	C01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	r	13:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	

	@Test
	public void testTutProcessMeeting() {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		try {
			mi.processMeetingLine(c, "41706	T01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	15:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
			assertEquals(1, c.tutorialList.size());
			assertEquals(0, c.lectureList.size());
		} catch (WrongFomatException e) {
			fail();
		}
	}
	
	@Test(expected=WrongFomatException.class)
	public void testTutProcessMeetingWrongTime() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41706	T01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	1a:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	
	@Test(expected=WrongFomatException.class)
	public void testTutProcessMeetingWrongEndTime() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41706	T01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	13:00 - 1a:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	
	@Test(expected=WrongFomatException.class)
	public void testTutProcessMeetingWrongCourseCode() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41a06	T01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	13:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	
	@Test(expected=WrongFomatException.class)
	public void testTutProcessMeetingWrongDay() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41706	T01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	r	13:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}
	
	@Test(expected=WrongFomatException.class)
	public void testTutProcessMeetingWrongSessionType() throws WrongFomatException {
		class MockInput extends Input{

			public MockInput(File f) {
				super(f);
				// TODO Auto-generated constructor stub
			}
			public void processMeetingLine(Course currentCourse, String line) throws WrongFomatException{
				super.processMeetingLine(currentCourse, line);
			}
			
		}
		File f = new File("src/testTimetableGen/testcases/test02_3courses.txt");
		MockInput mi = new MockInput(f);
		Course c = new Course();
		mi.processMeetingLine(c, "41706	a01	3	Main Campus	Y	B	8	128	N	11/01/2016 - 23/04/2016	R	13:00 - 16:50	AC1	LT-18	KEUNG Wai Jacky");
		
	}

}
