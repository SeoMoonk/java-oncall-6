package schedule.utils;

import java.util.Arrays;
import java.util.List;
import schedule.constants.ScheduleStatic;

public class ScheduleParser {

    public static List<String> splitMonthAndDayOfWeek(String input) {
        return Arrays.stream(input.split(ScheduleStatic.MONTH_AND_DAY_OF_WEEK_SEPARATOR)).toList();
    }
}
