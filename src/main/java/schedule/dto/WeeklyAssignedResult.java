package schedule.dto;

import java.util.List;
import worker.entity.Worker;

public record WeeklyAssignedResult(
        List<Worker> afterWeekDayWorkers,
        List<Worker> afterHolidayWorkers,
        int weekDayWorkerIndex,
        int holidayWorkerIndex
) {
}
