package timetableGen.meeting;

import timetableGen.exception.WrongFormatException;

/**
 * Hold Day information, parse between numeric value, string format and Day for in/ouput
 */

public enum Day {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);
    
    String[] friendlyNames = {"", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};

    private final int value;
    private Day(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    /**
     * Inputting Day in string format and return in Day 
     * @param str
     * @return
     * @throws WrongFormatException
     */
    
    public static Day stringToDay(String str) throws WrongFormatException{
    	//Converting String to char for switch statement so that it can be run with JDK 1.6
    	if (str.length()>1){
    		throw new WrongFormatException("Day should be one of MTWRFS");
    	}
    	char c=str.charAt(0);
    	switch(c){
	    	case 'M': return Day.MONDAY;
	    	case 'T': return Day.TUESDAY;
	    	case 'W': return Day.WEDNESDAY;
	    	case 'R': return Day.THURSDAY;
	    	case 'F': return Day.FRIDAY;
	    	case 'S': return Day.SATURDAY;
	    	default: throw new WrongFormatException("Day should be one of MTWRFS");
    	}
    }
    public String toString(){
    	return this.friendlyNames[this.getValue()];
    }
}
