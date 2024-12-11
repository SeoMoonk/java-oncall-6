package schedule.controller;

import global.view.InputView;
import schedule.service.ScheduleService;

public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public void setUpDefaultScheduleTable() {
        String inputMonthAndDayOfWeek = InputView.inputMonthAndDatOfWeek();
        System.out.println(inputMonthAndDayOfWeek);
    }
}
