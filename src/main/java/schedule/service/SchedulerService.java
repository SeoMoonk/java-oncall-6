package schedule.service;

import java.util.ArrayList;
import java.util.List;
import schedule.dto.WeeklyAssignedResult;
import schedule.entity.Schedule;
import worker.entity.Worker;
import worker.service.WorkerService;

public class SchedulerService {

    private final ScheduleService scheduleService;
    private final WorkerService workerService;

    public SchedulerService(ScheduleService scheduleService, WorkerService workerService) {
        this.scheduleService = scheduleService;
        this.workerService = workerService;
    }

    public void assignWorkers() {
        List<List<Schedule>> weeklySchedules = scheduleService.getWeeklySchedules();
        List<Worker> weekdayWorkers = workerService.getAllWeekdayWorkers();
        List<Worker> holidayWorkers = workerService.getAllHolidayWorkers();
        WeeklyAssignedResult weeklyAssignedResult = new WeeklyAssignedResult(weekdayWorkers, holidayWorkers,
                0, 0);

        for (List<Schedule> weeklySchedule : weeklySchedules) {
            assignForWeekly(weeklySchedule, weeklyAssignedResult);
        }
    }

    private void assignForWeekly(List<Schedule> weeklySchedule, WeeklyAssignedResult weeklyAssignedResult) {
        List<String> assignedWorkers = new ArrayList<>();
        List<Worker> weekdayWorkers = weeklyAssignedResult.afterWeekDayWorkers();
        List<Worker> holidayWorkers = weeklyAssignedResult.afterHolidayWorkers();

        for (Schedule s : weeklySchedule) {

            boolean flag = false;

            if(s.isHoliday()) {
                Worker worker = holidayWorkers.get(0);

                if(assignedWorkers.contains(worker)) {

                }

            }
        }
    }
}
