package schedule.utils;

import java.util.List;
import java.util.Optional;
import schedule.constants.DayOfWeek;
import schedule.constants.ScheduleStatic;

public class ScheduleValidator {

    public static void monthAndDayOfWeekValidate(List<String> inputMonthAndDayOfWeek) {
        validateSuccessfulSeparate(inputMonthAndDayOfWeek);
        monthValidate(inputMonthAndDayOfWeek.get(0));
        dayOfWeekValidate(inputMonthAndDayOfWeek.get(1));
    }

    private static void validateSuccessfulSeparate(List<String> monthAndDayOfWeek) {
        if(monthAndDayOfWeek.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 월,요일이 하나의 쉼표로 구분되어 입력되어야 합니다.");
        }
    }

    private static void monthValidate(String inputMonth) {
        integratedNumberValidate(inputMonth);
        validateMonthValueRange(inputMonth);
    }

    private static void dayOfWeekValidate(String inputDayOfWeek) {
        Optional<DayOfWeek> dayOfWeekByName = DayOfWeek.getByName(inputDayOfWeek);

        if(dayOfWeekByName.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 요일은 (월, 화, 수, 목, 금, 토, 일) 로 입력되어야 합니다.");
        }
    }

    private static void integratedNumberValidate(String input) {
        validateContainsPositiveSign(input);
        validateContainsBlank(input);
        validateCanParsedToInt(input);
    }

    private static void validateContainsPositiveSign(String input) {
        if(input.contains("+")) {
            throw new IllegalArgumentException("[ERROR] + 기호를 포함한 입력은 허용하지 않습니다.");
        }
    }

    private static void validateContainsBlank(String input) {
        if(input.contains(" ") || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백이거나 공백이 포함된 입력은 허용하지 않습니다.");
        }
    }

    private static void validateCanParsedToInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자로 변환할 수 없는 값은 입력할 수 없습니다.");
        }
    }

    private static void validateMonthValueRange(String inputMonth) {
        int monthValue = Integer.parseInt(inputMonth);

        if(monthValue < ScheduleStatic.START_OF_MONTH || monthValue > ScheduleStatic.END_OF_MONTH) {
            throw new IllegalArgumentException("[ERROR] 월 은 1월부터 12월 사이의 값으로 입력되어야 합니다.");
        }
    }
}
