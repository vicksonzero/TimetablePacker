package timetableGen.exception;



public class WrongFormatException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String msg = "";
	public WrongFormatException(String msg) {

		this.msg = msg;
	}

}
