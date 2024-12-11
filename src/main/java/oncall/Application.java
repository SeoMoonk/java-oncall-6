package oncall;

import global.config.AppConfig;
import schedule.controller.ScheduleController;
import worker.controller.WorkerController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        ScheduleController scheduleController = appConfig.getScheduleController();
        WorkerController workerController = appConfig.getWorkerController();

        scheduleController.setUpDefaultScheduleTable();
        workerController.employmentWorkers();
    }
}
