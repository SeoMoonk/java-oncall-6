package oncall;

import global.config.AppConfig;
import schedule.controller.ScheduleController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        ScheduleController scheduleController = appConfig.getScheduleController();

        scheduleController.setUpDefaultScheduleTable();
    }
}
