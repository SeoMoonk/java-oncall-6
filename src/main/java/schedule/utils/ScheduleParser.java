package schedule.utils;

import java.util.Arrays;
import java.util.List;
import schedule.constants.DayOfWeek;
import schedule.constants.Month;
import schedule.constants.ScheduleStatic;

public class ScheduleParser {

    public static List<String> splitMonthAndDayOfWeek(String input) {
        return Arrays.stream(input.split(ScheduleStatic.MONTH_AND_DAY_OF_WEEK_SEPARATOR)).toList();
    }

    public static Month parsingMonth(String inputMonth) {
        return Month.getByValue(Integer.parseInt(inputMonth));
    }

    public static DayOfWeek parsingDayOfWeek(String inputDayOfWeek) {
        return DayOfWeek.getByName(inputDayOfWeek).get();
    }
}
