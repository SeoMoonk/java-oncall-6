package schedule.constants;

import java.util.Optional;

public enum DayOfWeek {
    MONDAY("월",1),
    TUESDAY("화",2),
    WEDNESDAY("수",3),
    THURSDAY("목",4),
    FRIDAY("금",5),
    SATURDAY("토",6),
    SUNDAY("일",7);

    private String name;
    private int value;

    DayOfWeek(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Optional<DayOfWeek> getByName(String name) {
        for(DayOfWeek d : DayOfWeek.values()) {
            if(d.name.equals(name)) {
                return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    public int getValue() {
        return value;
    }
}
