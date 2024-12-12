package schedule.service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import schedule.constants.DayOfWeek;
import schedule.constants.Month;
import schedule.constants.PublicHoliday;
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
        int dayOfWeekValue = startDayOfWeek.getValue();

        for (int dayValue = 1; dayValue <= endOfMonth; dayValue++) {
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

    public void applyPublicHoliday() {
        int monthValue = getByDayValue(1).getMonthValue();
        List<PublicHoliday> publicHolidays = PublicHoliday.getAllByMonthValue(monthValue);

        for (PublicHoliday h : publicHolidays) {
            Schedule schedule = getByDayValue(h.getDayValue());
            schedule.applyPublicHoliday();
            modify(schedule);
        }
    }

    private Schedule getByDayValue(int dayValue) {
        Optional<Schedule> maybeDayValue = scheduleRepository.findByDayValue(dayValue);
        if (maybeDayValue.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 접근할 수 없는 값입니다.");
        }
        return maybeDayValue.get();
    }

    public List<List<Schedule>> getWeeklySchedules() {
        List<List<Schedule>> weeklySchedules = new ArrayList<>();
        List<Schedule> sundaySchedules = getAllByDayOfWeek(DayOfWeek.SUNDAY);
        int startDay = 1;

        while (!sundaySchedules.isEmpty()) {
            int sundayValue = sundaySchedules.get(0).getDayValue();
            weeklySchedules.add(getBetweenStartDayValueAndEndDayValue(startDay, sundayValue));
            startDay = sundayValue + 1;
            sundaySchedules.removeFirst();
        }

        weeklySchedules.add(getBetweenStartDayValueAndEndDayValue(startDay, getAll().size()));

        return weeklySchedules;
    }

    private List<Schedule> getAllByDayOfWeek(DayOfWeek dayOfWeek) {
        return scheduleRepository.findAllByDayOfWeek(dayOfWeek);
    }

    public void modify(Schedule schedule) {
        scheduleRepository.modify(schedule);
    }

    public List<Schedule> getBetweenStartDayValueAndEndDayValue(int startDayValue, int endDayValue) {
        return scheduleRepository.findBetweenStartDayValueAndEndDayValue(startDayValue, endDayValue);
    }

    public String getLastDayWorkersName(int dayValue) {
        return scheduleRepository.findByDayValue(dayValue-1).get().getWorker().getName();
    }
}
