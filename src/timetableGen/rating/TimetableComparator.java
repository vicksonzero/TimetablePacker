package timetableGen.rating;

import java.util.Comparator;

import timetableGen.timetable.Timetable;

public abstract class TimetableComparator implements Comparator<Timetable> {
	/**
	 * This is an empty sub-comparator (parent of other sub-comparator)
	 */
	public String name = "";


}
