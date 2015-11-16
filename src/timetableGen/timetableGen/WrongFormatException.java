package timetableGen.timetableGen;

public class WrongFormatException extends Exception {
	
	public String msg = "";
	public WrongFormatException(String msg) {

		this.msg = msg;
	}

}
