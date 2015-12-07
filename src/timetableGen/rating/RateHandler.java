package timetableGen.rating;

import java.util.ArrayList;
import java.util.Comparator;

import timetableGen.timetable.Timetable;

public class RateHandler extends TimetableComparator {
	
	public String name = "RateHandler";
	
	private ArrayList<Comparator<Timetable> > comparators = new ArrayList<Comparator<Timetable> >();
	
	public RateHandler(){
		this.comparators.add(new CompareDayCount());
	}
	
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
