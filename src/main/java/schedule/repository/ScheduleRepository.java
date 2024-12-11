package schedule.repository;

import java.util.List;
import java.util.Optional;
import schedule.constants.DayOfWeek;
import schedule.entity.Schedule;

public interface ScheduleRepository {
    void save(Schedule schedule);
    void saveAll(List<Schedule> schedules);
    List<Schedule> findAll();
    Optional<Schedule> findByDayValue(int dayValue);
    void modify(Schedule schedule);
    List<Schedule> findAllByDayOfWeek(DayOfWeek dayOfWeek);
    List<Schedule> findBetweenStartDayValueAndEndDayValue(int startDayValue, int endDayValue);
}
