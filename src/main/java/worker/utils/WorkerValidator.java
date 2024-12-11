package worker.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkerValidator {

    public static void weekdayWorkersValidate(String input) {
        List<String> weekdayWorkers;
        validateCanSeparateBySeparator(input);
        weekdayWorkers = Arrays.asList(input.split(","));
        integratedWorkersValidate(weekdayWorkers);
    }

    public static void holidayWorkersValidate(String input, List<String> weekdayWorkersNames) {
        List<String> hodidayWorkers;
        validateCanSeparateBySeparator(input);
        hodidayWorkers = Arrays.asList(input.split(","));
        integratedWorkersValidate(hodidayWorkers);
        validateMaxCountForHoliday(weekdayWorkersNames, hodidayWorkers);
    }

    private static void integratedWorkersValidate(List<String> workers) {
        validateHasDuplicatedWorker(workers);
        validateWorkersNameLength(workers);
        validateWorkersMinimumCount(workers);
        validateMaxCountForWeekDay(workers);
        validateContainsBlank(workers);
    }

    private static void validateCanSeparateBySeparator(String input) {
        if (!input.contains(",")) {
            throw new IllegalArgumentException("[ERROR] 근무자들의 이름은 쉼표로 구분되어 입력되어야 합니다.");
        }
    }

    private static void validateHasDuplicatedWorker(List<String> workers) {
        Set<String> workersTemp = new HashSet<>(workers);
        if (workersTemp.size() != workers.size()) {
            throw new IllegalArgumentException("[ERROR] 근무자들의 이름은 중복되어 입력될 수 없습니다.");
        }
    }

    private static void validateWorkersNameLength(List<String> workers) {
        for (String name : workers) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("[ERROR] 근무자의 이름은 5자를 넘을 수 없습니다.");
            }
        }
    }

    private static void validateWorkersMinimumCount(List<String> workers) {
        if (workers.size() < 5) {
            throw new IllegalArgumentException("[ERROR] 각각 최소 5명의 근무자가 등록되어야 합니다.");
        }
    }

    private static void validateMaxCountForWeekDay(List<String> workers) {
        if (workers.size() > 35) {
            throw new IllegalArgumentException("[ERROR] 최대 근무자 수는 35명 입니다");
        }
    }

    private static void validateMaxCountForHoliday(List<String> weekdayWorkers, List<String> holidayWorkers) {
        Set<String> totalWorkers = new HashSet<>(weekdayWorkers);
        totalWorkers.addAll(holidayWorkers);
        if (totalWorkers.size() > 35) {
            throw new IllegalArgumentException("[ERROR] 최대 근무자 수는 35명 입니다");
        }
    }

    private static void validateContainsBlank(List<String> workers) {
        for (String name : workers) {
            if (name.isBlank() || name.contains(" ")) {
                throw new IllegalArgumentException("[ERROR] 공백이거나 공백이 포함된 이름은 불가합니다.");
            }
        }
    }
}
