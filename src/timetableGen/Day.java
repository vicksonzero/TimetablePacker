package timetableGen;

import timetableGen.timetableGen.WrongFomatException;

public enum Day {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int value;
    private Day(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static Day stringToDay(String str) throws WrongFomatException{
    	switch(str){
    	case "M": return Day.MONDAY;
    	case "T": return Day.TUESDAY;
    	case "W": return Day.WEDNESDAY;
    	case "R": return Day.THURSDAY;
    	case "F": return Day.FRIDAY;
    	case "S": return Day.SATURDAY;
    	default: throw new WrongFomatException("Day should be one of MTWRFS");
    	}
    }
}
