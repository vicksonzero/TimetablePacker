package timetableGen.exception;


public class NoInputCourseException extends Exception {
	/**
	 * Called when no course is extracted after txt file input
	 */
	private static final long serialVersionUID = 1L;
	public String msg = "";
	public NoInputCourseException(String msg) {

		this.msg = msg;
	}
}
