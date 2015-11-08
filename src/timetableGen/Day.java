package timetableGen;

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
}
