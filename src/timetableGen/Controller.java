package timetableGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

import timetableGen.exception.NoInputCourseException;
import timetableGen.exception.WrongFormatException;
import timetableGen.rating.*;
import timetableGen.timetable.Timetable;

public class Controller{

	private static ArrayList<Timetable> timetables=new ArrayList<Timetable>();
		
	public static void main(String args[]) throws FileNotFoundException, WrongFormatException, NoInputCourseException{
		Scanner sc=new Scanner(System.in);
		//Input file path
		System.out.println("Please input filepath: ");
		String filepath=sc.nextLine();
				
		//Input file
		File file=new File(filepath);
		Input input=new Input(file);
		
		//Process file into arrayList and classes
		ArrayList<Course> courses=input.parse();
		if (courses.size()==0){
			sc.close();
			throw new NoInputCourseException("No course is get.");
		}
		
		//generate all combinations with existing lectures and tutorials under courses (conflict of time exist)
		ArrayList<Timetable> allCombinations=Timetable.genCom(0,courses);
		
		//filter out timetables that have time conflict
		for (Timetable tt:allCombinations){
			if(!tt.hasConflict())
				timetables.add(tt);
		}
		
		if(timetables.size()<=0){
			System.out.printf("No possible timetables found\n");
			sc.close();
			return;
		}
		
		// prompt for timetable preference
		RateHandler compositeComparator = interactiveMakeComparator(sc);
		
		//sort according to level of recommendation
		Collections.sort(timetables,compositeComparator);
		
		// prepare anM:\_season04\TimetablePacker\src\tests\testcases\test02_3courses.txtd print top 3
		String result="";
		
		// result summary
		result += String.format("Showing top %d of %d possible timetables\n", 
				(timetables.size() > 3? 3: timetables.size()), 
				timetables.size());
		
		for (int i=0; i < timetables.size() && i<3; i++){
			result+=("Rank "+(i+1)+": \n" + timetables.get(i));
		}
		
		System.out.println(result);
		sc.close();
	}
	
	
	public static String getResult(String result){
		return result;		
	}
	
	/**
	 * Interactively create a RateHandler that contains an arrayList of sub-comparator 
	 * @param sc
	 * @return RateHandler
	 */
	public static RateHandler interactiveMakeComparator(Scanner sc){
		
		// prepare a list of choices
		ArrayList<TimetableComparator> comparators = new ArrayList<TimetableComparator>();
		comparators.add(new CompareDayCount());
		comparators.add(new CompareMorningLessons());
		comparators.add(new CompareEveningLessons());
		comparators.add(new CompareTimeDiff());
		comparators.add(new CompareGapHours());
		
		// result
		RateHandler result = new RateHandler();
		
		// prompt
		System.out.println("Choose from the following sorting criteria:");
		
		// list choices
		for(int i=0; i < comparators.size(); i++){
			System.out.printf("%d. %s\n", i+1, comparators.get(i).name);
		}
		
		// flag for retries
		boolean inputIsGood = false;
		
		// holder
		String input = "";
		ArrayList<Integer> ordering = new ArrayList<Integer>();
		
		// loop until input has correct format
		do{
			// prompt
			System.out.println("Input the numbers of the rules, separated by space bar");
			System.out.println("(eg: 1 5 3 4):");
			
			// input
			input=sc.nextLine();
			
			// check against regex for correct format (number space number space number...number)
			inputIsGood = Pattern.matches("\\d( \\d)*", input);
			
			if(inputIsGood){
				//parse input to a list of orderings
				ordering = parseOrdering(input);
				
				for(int i=0; i < ordering.size(); i++){
					if(ordering.get(i)<1 || ordering.get(i)>comparators.size()){
						System.out.printf("Rule ID is between 1 and %d, inclusive\n", comparators.size());
						inputIsGood = false;
						break;
					}
				}
			}


		}while(! inputIsGood);
		
		
		// transform ordering to result
		for(int i=0; i < ordering.size(); i++){
			result.addComparator(comparators.get(ordering.get(i)-1));
		}
		
		// return
		return result;
		
		
	}
	
	public static ArrayList<Integer> parseOrdering(String str){
		ArrayList<Integer> result = new ArrayList<Integer>();
		String[] ordering = str.split(" ");
		
		for(int i=0; i < ordering.length; i++){
			result.add(Integer.parseInt(ordering[i]));
		}
		
		return result;
	}

		
}
	
