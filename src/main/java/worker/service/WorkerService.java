package worker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import worker.constants.WorkerType;
import worker.entity.Worker;
import worker.repository.WorkerRepository;
import worker.repository.WorkerRepositoryImpl;
import worker.utils.WorkerValidator;

public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = new WorkerRepositoryImpl();
    }

    public void registeredWorkers(String inputWorkers, WorkerType type) {
        List<String> names = new ArrayList<>();
        validateInputWorkers(inputWorkers, type);
        names = Arrays.asList(inputWorkers.split(","));

        for (int i = 1; i <= names.size(); i++) {
            register(i, names.get(i - 1), type);
        }
    }

    private void register(int id, String name, WorkerType type) {
        Worker worker = new Worker(id, name, type);
        workerRepository.save(worker);
    }

    private void validateInputWorkers(String inputWorkers, WorkerType type) {
        if (type.equals(WorkerType.WEEKDAY_WORKER)) {
            WorkerValidator.weekdayWorkersValidate(inputWorkers);
            return;
        }

        if (type.equals(WorkerType.HOLIDAY_WORKER)) {
            List<String> weekdayWorkersNames = getAllWeekdayWorkerNames();
            WorkerValidator.holidayWorkersValidate(inputWorkers, weekdayWorkersNames);
        }
    }

    private List<String> getAllWeekdayWorkerNames() {
        return workerRepository.findAllWeekDayWorkersNames();
    }

    public List<Worker> getAllWeekdayWorkers() {
        return workerRepository.findAllWeekDayWorkers();
    }

    public List<Worker> getAllHolidayWorkers() {
        return workerRepository.findAllHolidayWorkers();
    }

    public void clearAll() {
        workerRepository.removeAll();
    }
}
