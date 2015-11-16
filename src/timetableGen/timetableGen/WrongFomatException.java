package timetableGen.timetableGen;

public class WrongFomatException extends Exception {
	
	public String msg = "";
	public WrongFomatException(String msg) {

		this.msg = msg;
	}

}
