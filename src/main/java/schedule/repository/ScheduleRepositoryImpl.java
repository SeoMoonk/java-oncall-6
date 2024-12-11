package schedule.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import schedule.constants.DayOfWeek;
import schedule.entity.Schedule;

public class ScheduleRepositoryImpl implements ScheduleRepository {

    private List<Schedule> storedSchedules = new ArrayList<>();

    @Override
    public void save(Schedule schedule) {
        storedSchedules.add(schedule);
    }

    @Override
    public void saveAll(List<Schedule> schedules) {
        storedSchedules.addAll(schedules);
    }

    @Override
    public List<Schedule> findAll() {
        List<Schedule> schedules = storedSchedules;
        return schedules;
    }

    @Override
    public Optional<Schedule> findByDayValue(int dayValue) {
        return storedSchedules.stream()
                .filter(s -> dayValue == s.getDayValue())
                .findFirst();
    }

    @Override
    public void modify(Schedule afterSchedule) {
        Schedule beforeSchedule = findByDayValue(afterSchedule.getDayValue()).get();
        storedSchedules.set(storedSchedules.indexOf(beforeSchedule), afterSchedule);
    }

    @Override
    public List<Schedule> findAllByDayOfWeek(DayOfWeek dayOfWeek) {
        List<Schedule> targetSchedules = new ArrayList<>();
        for(Schedule s : storedSchedules) {
            if(s.getDayOfWeek().equals(dayOfWeek)) {
                targetSchedules.add(s);
            }
        }
        return targetSchedules;
    }

    @Override
    public List<Schedule> findBetweenStartDayValueAndEndDayValue(int startDayValue, int endDayValue) {
        return storedSchedules.subList(startDayValue, endDayValue);
    }
}
