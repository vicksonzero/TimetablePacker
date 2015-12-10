package timetableGen.exception;

public class WrongFormatException extends Exception {
	
	/**
	 * Called by Input class processMeetingLine(Course, String) method if the reading txt file is in wrong format
	 */
	private static final long serialVersionUID = 1L;
	public String msg = "";
	public WrongFormatException(String msg) {

		this.msg = msg;
	}

}
