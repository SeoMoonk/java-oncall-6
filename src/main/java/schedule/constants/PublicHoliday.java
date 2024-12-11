package schedule.constants;

import java.util.ArrayList;
import java.util.List;

public enum PublicHoliday {

    신정(1, 1),
    삼일절(3, 1),
    어린이날(5, 5),
    현충일(6, 6),
    광복절(8, 15),
    개천절(10, 3),
    한글날(10, 9),
    성탄절(12, 25);


    private int monthValue;
    private int dayValue;

    PublicHoliday(int monthValue, int dayValue) {
        this.monthValue = monthValue;
        this.dayValue = dayValue;
    }

    public static List<PublicHoliday> getAllByMonthValue(int monthValue) {
        List<PublicHoliday> publicHolidays = new ArrayList<>();

        for(PublicHoliday h : PublicHoliday.values()) {
            if(h.getMonthValue() == monthValue) {
                publicHolidays.add(h);
            }
        }

        return publicHolidays;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public int getDayValue() {
        return dayValue;
    }
}
