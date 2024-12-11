package schedule.repository;

import java.util.List;
import schedule.entity.Schedule;

public interface ScheduleRepository {
    void save(Schedule schedule);
    void saveAll(List<Schedule> schedules);
    List<Schedule> findAll();
}
