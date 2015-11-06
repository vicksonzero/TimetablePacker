package meeting;

import java.util.ArrayList;
import java.util.List;

import timetableGen.Meeting;

public class Tutorial 
{	
	public List<Tutorial> extract(List<Meeting> meeting)
    {
        List<Tutorial> tutorialType = new ArrayList<Tutorial>();
        for (Meeting e : meeting) 
        {
        	if (e.getSessionType().charAt(0) == 'T' )
        	{
        		//tutorialType.add();
        	}
        }
        return tutorialType;
    }
}