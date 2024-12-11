package schedule.service;

import java.util.List;
import schedule.repository.ScheduleRepository;
import schedule.utils.ScheduleParser;
import schedule.utils.ScheduleValidator;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void setUpDefaultScheduleTable(String input) {
        List<String> monthAndDayOfWeek = ScheduleParser.splitMonthAndDayOfWeek(input);
        ScheduleValidator.monthAndDayOfWeekValidate(monthAndDayOfWeek);
    }
}
