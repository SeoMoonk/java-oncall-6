package global.config;

import schedule.controller.ScheduleController;
import schedule.repository.ScheduleRepository;
import schedule.repository.ScheduleRepositoryImpl;
import schedule.service.ScheduleService;

public class AppConfig {

    private final ScheduleController scheduleController;
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    public AppConfig() {
        scheduleRepository = new ScheduleRepositoryImpl();
        scheduleService = new ScheduleService(scheduleRepository);
        scheduleController = new ScheduleController(scheduleService);
    }

    public ScheduleController getScheduleController() {
        return scheduleController;
    }
}
