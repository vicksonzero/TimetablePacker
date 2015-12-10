package timetableGen.rating;

import java.util.ArrayList;
import java.util.Comparator;

import timetableGen.timetable.Timetable;

public class RateHandler implements Comparator<Timetable> {
	/**
	 * Comparator of timetables.
	 * Contains a list of sub-comparator in order under the user preference
	 */
	public String name = "RateHandler";
	
	private ArrayList<Comparator<Timetable> > comparators = new ArrayList<Comparator<Timetable> >();
	
	
	public RateHandler addComparator(Comparator<Timetable> c){
		this.comparators.add(c);
		return this;
	}

	@Override
	public int compare(Timetable o1, Timetable o2) {
		for(Comparator<Timetable> c : this.comparators){
			int comparison = c.compare(o1, o2);
			if(comparison!=0){
				return comparison;
			}
		}
		return 0;
	}
	
	
	

}
