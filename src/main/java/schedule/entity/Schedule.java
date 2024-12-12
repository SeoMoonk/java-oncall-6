package schedule.entity;

import schedule.constants.DayOfWeek;
import schedule.constants.Month;
import worker.entity.Worker;

public class Schedule {
    private int monthValue;             //월
    private int dayValue;
    private DayOfWeek dayOfWeek;        //요일
    private boolean isHoliday;          //주말 or 공휴일 여부
    private Worker worker;            //작업자


    public Schedule(int monthValue, int dayValue, DayOfWeek dayOfWeek, boolean isHoliday) {
        this.monthValue = monthValue;
        this.dayValue = dayValue;
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
        this.worker = null;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public int getDayValue() {
        return dayValue;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void applyPublicHoliday() {
        isHoliday = true;
    }

    public void assignWorker(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }
}
