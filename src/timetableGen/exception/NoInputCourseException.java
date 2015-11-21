package timetableGen.exception;


public class NoInputCourseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String msg = "";
	public NoInputCourseException(String msg) {

		this.msg = msg;
	}
}
