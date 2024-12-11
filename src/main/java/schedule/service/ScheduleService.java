package schedule.service;

import java.util.ArrayList;
import java.util.List;
import schedule.constants.DayOfWeek;
import schedule.constants.Month;
import schedule.entity.Schedule;
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
        Month month = ScheduleParser.parsingMonth(monthAndDayOfWeek.get(0));
        DayOfWeek startDayOfWeek = ScheduleParser.parsingDayOfWeek(monthAndDayOfWeek.get(1));
        createDefaultScheduleTable(month, startDayOfWeek);
    }

    private void createDefaultScheduleTable(Month month, DayOfWeek startDayOfWeek) {
        List<Schedule> schedules = new ArrayList<>();
        int monthValue = month.getValue();
        int endOfMonth = month.getEndOfMonth();
        int dayOfWeekValue= startDayOfWeek.getValue();

        for(int dayValue=1; dayValue<=endOfMonth; dayValue++) {
            DayOfWeek dayOfWeek = DayOfWeek.getByValue(dayOfWeekValue);
            boolean isHoliday = DayOfWeek.isWeekEnd(dayOfWeekValue);
            Schedule schedule = new Schedule(monthValue, dayValue, dayOfWeek, isHoliday);
            schedules.add(schedule);
            dayOfWeekValue = dayOfWeekValue % 7 + 1;
        }
        scheduleRepository.saveAll(schedules);
    }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }
}
