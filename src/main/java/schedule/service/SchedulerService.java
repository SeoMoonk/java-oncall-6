package schedule.service;

import java.util.ArrayDeque;
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

        for (List<Schedule> schedules : weeklySchedules) {
            List<String> weeklyWeekDayWorkerNames = new ArrayList<>();
            List<String> weeklyHolidayWorkerNames = new ArrayList<>();
            for (Schedule s : schedules) {
                if (s.isHoliday()) {
                    weeklyHolidayWorkerNames.add(holidayAssign(s, weeklyHolidayWorkerNames, holidayWorkers));
                    continue;
                }
                weeklyWeekDayWorkerNames.add(weekdayAssign(s, weeklyWeekDayWorkerNames, weekdayWorkers));
            }
        }
    }

    public String holidayAssign(Schedule schedule, List<String> weeklyHolidayWorkerNames, List<Worker> holidayWorkers) {
        List<String> needToAvoidList = new ArrayList<>();
        if (schedule.getDayValue() != 1) {
            needToAvoidList.add(scheduleService.getLastDayWorkersName(schedule.getDayValue()));
        }
        needToAvoidList.addAll(weeklyHolidayWorkerNames);

        for(Worker w : holidayWorkers) {
            if(!needToAvoidList.contains(w.getName())) {
                schedule.assignWorker(w);
                scheduleService.modify(schedule);
                holidayWorkers.remove(w);
                holidayWorkers.addLast(w);
                return w.getName();
            }
        }

        throw new IllegalArgumentException("[ERROR] 적합한 근무자가 없습니다.");
    }

    public String weekdayAssign(Schedule schedule, List<String> weeklyWeekDayWorkerNames, List<Worker> weekdayWorkers) {
        List<String> needToAvoidList = new ArrayList<>();
        if (schedule.getDayValue() != 1) {
            needToAvoidList.add(scheduleService.getLastDayWorkersName(schedule.getDayValue()));
        }
        needToAvoidList.addAll(weeklyWeekDayWorkerNames);

        for(Worker w : weekdayWorkers) {
            if(!needToAvoidList.contains(w.getName())) {
                schedule.assignWorker(w);
                scheduleService.modify(schedule);
                weekdayWorkers.remove(w);
                weekdayWorkers.addLast(w);
                return w.getName();
            }
        }

        throw new IllegalArgumentException("[ERROR] 적합한 근무자가 없습니다.");
    }
}
