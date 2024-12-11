package schedule.constants;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private int value;
    private int endOfMonth;

    Month(int value, int endOfMonth) {
        this.value = value;
        this.endOfMonth = endOfMonth;
    }

    public static Month getByValue(int value) {
        for(Month month : Month.values()) {
            if(month.value == value) {
                return month;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public int getEndOfMonth() {
        return endOfMonth;
    }
}
