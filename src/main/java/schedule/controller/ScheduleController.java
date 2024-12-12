package schedule.controller;

import global.view.InputView;
import java.util.List;
import schedule.entity.Schedule;
import schedule.service.ScheduleService;
import schedule.service.SchedulerService;

public class ScheduleController {

    private final ScheduleService scheduleService;
    private final SchedulerService schedulerService;

    public ScheduleController(ScheduleService scheduleService, SchedulerService schedulerService) {
        this.scheduleService = scheduleService;
        this.schedulerService = schedulerService;
    }

    public void setUpDefaultScheduleTable() {
        try {
            String inputMonthAndDayOfWeek = InputView.inputMonthAndDatOfWeek();
            scheduleService.setUpDefaultScheduleTable(inputMonthAndDayOfWeek);
            scheduleService.applyPublicHoliday();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            setUpDefaultScheduleTable();
        }
    }

    public void assign() {
        schedulerService.assignWorkers();

        List<Schedule> all = scheduleService.getAll();

        for(Schedule s : all) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.getDayValue());
            sb.append(s.getDayOfWeek());
            sb.append(s.getWorker().getName());
            System.out.println(sb.toString());
        }
    }
}
