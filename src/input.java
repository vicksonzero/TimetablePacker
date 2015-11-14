import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class input {
	ArrayList<String>course = new ArrayList<String>();
	Arraylist<String>session = new Arraylist<String>();
	
	File f = new File(filename);
    try 
    {
    	Scanner scanner = new Scanner(f);
    	
    	while (scanner.hasNext()) 
        {
        	String coursecode = scanner.next();
        	
        	
            String[] data = line.split(" ");
            entries.add(new Meeting(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], data[6]));
            
            
        }
        scanner.close();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
   
    public String getSessionType()
    {
        return sessionType;
    }
	
}
