

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import timetableGen.Input;
import timetableGen.Timetable;
import timetableGen.WrongFormatException;
import timetableGen.meeting.Course;

public class Controller {

	private ArrayList<Timetable> timetables=new ArrayList<Timetable>();
	//private static final String FILENAME = "input-file.txt";
	
	public void main(String args[]) throws FileNotFoundException, WrongFormatException{
		Scanner sc=new Scanner(System.in);
		//Input filepath
		System.out.println("Please input filepath: ");
		String filepath=sc.next();
				
		//Input file
		File file=new File(filepath);
		Input input=new Input(file);
		
		//Process file into arraylist and classes
		ArrayList<Course> courses=input.parse();		
		
		//generate all combinations with existing lectures and tutorials under courses (conflict of time exist)
		ArrayList<Timetable> allCombinations=Timetable.genCom(0,courses);
		
		//filter out timetables that have time conflict
		for (Timetable tt:allCombinations){
			if(!tt.hasConflict())
				timetables.add(tt);
		}
		
		//sort according to level of recommendation
		Collections.sort(timetables,Collections.reverseOrder());
		
		for (int i=0;i<3;i++){
			System.out.println(timetables.get(i));
		}
		sc.close();
	}

		
}
	
