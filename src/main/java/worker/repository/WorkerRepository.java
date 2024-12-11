package worker.repository;

import java.util.List;
import worker.entity.Worker;

public interface WorkerRepository {
    void save(Worker worker);

    List<Worker> findAllWeekDayWorkers();

    List<Worker> findAllHolidayWorkers();

    List<String> findAllWeekDayWorkersNames();

    void removeAll();
}
