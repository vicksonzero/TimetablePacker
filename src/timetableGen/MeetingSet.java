package timetableGen;
import java.util.ArrayList;

public class MeetingSet {

	public ArrayList<String> meetingset = new ArrayList<String>();
	
	public void generateMeetingSet(int coursenum){
		for(int i=0; i<coursenum; i++){
			meetingset.add(Course.courselist.get(i));
		}
	}
	
	
	
	

}

