package timetableGen.rating;

import java.util.Comparator;

import timetableGen.timetable.Timetable;

public class TimetableComparator implements Comparator<Timetable> {
	/**
	 * This is an empty sub-comparator (parent of other sub-comparator)
	 */
	public String name = "";

	@Override
	public int compare(Timetable o1, Timetable o2) {
		return 0;
	}

}
