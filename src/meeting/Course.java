package meeting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import timetableGen.Meeting;

public class Course 
{
	List<Tutorial> tutorialList = new ArrayList<Tutorial>();
	List<Lecture> lectureList = new ArrayList<Lecture>();
	
	 for (Input e : input) 
     {
     	if (e.getSessionType().charAt(0) == 'C' )
     	{
     		lectureList.add(new Meeting);
     	}
     	
     	else if (e.getSessionType().charAt(0) == 'T' )
    	{
    		tutorialList.add(new Meeting);
    	}
     }
	
	/*   public List<Meeting>parse (String filename)
    {
        List<Meeting> entries = new ArrayList<Meeting>();
        File f = new File(filename);
        try (Scanner scanner = new Scanner(f);)
        {
            while (scanner.hasNext()) 
            {
            	String line = scanner.next();
                String[] data = line.split(" ");
                entries.add(new Meeting(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], data[6]));
            }
            scanner.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return entries;
    }
    
 */
}