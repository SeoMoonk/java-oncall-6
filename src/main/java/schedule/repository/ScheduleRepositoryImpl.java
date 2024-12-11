package schedule.repository;

import java.util.ArrayList;
import java.util.List;
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
}
