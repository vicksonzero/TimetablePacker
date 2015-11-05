import java.util.ArrayList;
import java.util.Collections;

import timetableGen.Timetable;


public class Controller {

	private ArrayList<Timetable> timetables=new ArrayList<Timetable>();
	
	public void main(String args[]){
		//sort according to level of recommendation
		Collections.sort(timetables,Collections.reverseOrder());
		
		for (int i=0;i<3;i++)
			System.out.println(timetables.get(i));
	}
	
}
