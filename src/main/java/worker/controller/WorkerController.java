package worker.controller;

import global.view.InputView;
import worker.constants.WorkerType;
import worker.service.WorkerService;

public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    public void employmentWorkers() {
        try {
            employmentWeekdayWorkers();
            employmentHolidayWorkers();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            workerService.clearAll();
            employmentWorkers();
        }
    }

    private void employmentWeekdayWorkers() {
        String inputWeekdayWorkers = InputView.inputWeekdayWorkers();
        workerService.registeredWorkers(inputWeekdayWorkers, WorkerType.WEEKDAY_WORKER);
    }

    private void employmentHolidayWorkers() {
        String inputHolidayWorkers = InputView.inputHolidayWorkers();
        workerService.registeredWorkers(inputHolidayWorkers, WorkerType.HOLIDAY_WORKER);
    }
}
