package timetableGen;

public class EmptyCourse extends Course {
	
	private static EmptyCourse singletonInstance = null;
	public EmptyCourse() {
		super("");
	}

	public static Course getInstance() {
		if(singletonInstance == null){
			singletonInstance = new EmptyCourse();
		}
		return singletonInstance;
	}

}
