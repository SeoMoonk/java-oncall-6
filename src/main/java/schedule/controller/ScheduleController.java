package schedule.controller;

import global.view.InputView;
import java.util.List;
import schedule.entity.Schedule;
import schedule.service.ScheduleService;

public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public void setUpDefaultScheduleTable() {
        try {
            String inputMonthAndDayOfWeek = InputView.inputMonthAndDatOfWeek();
            scheduleService.setUpDefaultScheduleTable(inputMonthAndDayOfWeek);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            setUpDefaultScheduleTable();
        }

    }
}
