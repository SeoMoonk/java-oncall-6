package global.config;

import schedule.controller.ScheduleController;
import schedule.repository.ScheduleRepository;
import schedule.repository.ScheduleRepositoryImpl;
import schedule.service.ScheduleService;
import worker.controller.WorkerController;
import worker.repository.WorkerRepository;
import worker.repository.WorkerRepositoryImpl;
import worker.service.WorkerService;

public class AppConfig {

    private final ScheduleController scheduleController;
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    private final WorkerController workerController;
    private final WorkerService workerService;
    private final WorkerRepository workerRepository;

    public AppConfig() {
        scheduleRepository = new ScheduleRepositoryImpl();
        scheduleService = new ScheduleService(scheduleRepository);
        scheduleController = new ScheduleController(scheduleService);

        workerRepository = new WorkerRepositoryImpl();
        workerService = new WorkerService(workerRepository);
        workerController = new WorkerController(workerService);
    }

    public ScheduleController getScheduleController() {
        return scheduleController;
    }

    public WorkerController getWorkerController() {
        return workerController;
    }
}
