package worker.repository;

import java.util.ArrayList;
import java.util.List;
import worker.constants.WorkerType;
import worker.entity.Worker;

public class WorkerRepositoryImpl implements WorkerRepository {

    private List<Worker> storedWeekDayWorkers = new ArrayList<>();
    private List<Worker> storedHoliDayWorkers = new ArrayList<>();

    @Override
    public void save(Worker worker) {
        if (worker.getType().equals(WorkerType.WEEKDAY_WORKER)) {
            storedWeekDayWorkers.add(worker);
            return;
        }

        if (worker.getType().equals(WorkerType.HOLIDAY_WORKER)) {
            storedHoliDayWorkers.add(worker);
        }
    }

    @Override
    public List<Worker> findAllWeekDayWorkers() {
        List<Worker> weekdayWorkers = new ArrayList<>(storedWeekDayWorkers);
        return weekdayWorkers;
    }

    @Override
    public List<Worker> findAllHolidayWorkers() {
        List<Worker> holidayWorkers = new ArrayList<>(storedHoliDayWorkers);
        return holidayWorkers;
    }

    @Override
    public List<String> findAllWeekDayWorkersNames() {
        List<String> weekdayWorkersNames = new ArrayList<>();

        for (Worker worker : storedWeekDayWorkers) {
            weekdayWorkersNames.add(worker.getName());
        }

        return weekdayWorkersNames;
    }

    @Override
    public void removeAll() {
        storedWeekDayWorkers = new ArrayList<>();
        storedHoliDayWorkers = new ArrayList<>();
    }
}
