package meeting;

import java.util.ArrayList;
import java.util.List;

import timetableGen.Meeting;

public class Lecture
{
    public List<Lecture> extract(List<Meeting> meeting)
    {
        List<Lecture> lectureType = new ArrayList<Lecture>();
        for (Meeting e : meeting) 
        {
        	if (e.getSessionType().charAt(0) == 'C' )
        	{
        		//lectureType.add();
        	}
        }
        return lectureType;
    }
}

