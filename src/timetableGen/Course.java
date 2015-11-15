package timetableGen;

//import MeetingSet;

import java.util.ArrayList;

public class Course {
	
	public ArrayList<MeetingSet> MeetingSetinACourse = new ArrayList<MeetingSet>(); 
	
	
	public ArrayList<MeetingSet> generateMeetingSet(ArrayList<Tutorial> tutlist, ArrayList<Lecture> lecturelist){
		for(int i=0;i<tutlist.size();i++){
			for(int j=0;j<lecturelist.size();j++){
				MeetingSet m = new MeetingSet( tutlist.get(i), lecturelist.get(j));
				MeetingSetinACourse.add(m);
			}
		}
		return MeetingSetinACourse;
	}
	
}