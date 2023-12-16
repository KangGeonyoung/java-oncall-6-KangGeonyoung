package oncall.domain;

import oncall.util.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class EmergencyWorkAssignment {
    private static List<String> dayOfWeek = new ArrayList<>(List.of("일", "월", "화", "수", "목", "금", "토"));
    private static List<String> weekdayMember = new ArrayList<>();
    private static List<String> holidayMember = new ArrayList<>();
    private static int memberNumber;
    private static List<String> finalAssignmentResult = new ArrayList<>();
    ;
    private static int month;
    private static int date;
    private static String day;
    private static int currentPosition_weekday;
    private static int currentPosition_holiday;

    public EmergencyWorkAssignment(List<String> newWeekdayMember, List<String> newHolidayMember, int month, String day) {
        weekdayMember.addAll(newWeekdayMember);
        holidayMember.addAll(newHolidayMember);
        this.month = month;
        this.date = 1;
        this.day = day;
        currentPosition_weekday = 0;
        currentPosition_holiday = 0;
        memberNumber = 0;
    }

    public List<String> startWorkAssignment() {
        isAssignedOnce(weekdayMember, holidayMember);

        // 해당 월의 마지막 날이 올 때까지
        while (!isEndAssignment()) {
            if (Date.isWeekday(day)) {
                assignWhenWeekday(month);
            }
            if (!Date.isWeekday(day)) {
                assignWhenHoliday(month);
            }
            changeNextDate();
        }
        return finalAssignmentResult;
    }

    private static void changeNextDate() {
        date += 1;
        day = moveNextDay(day);
    }

    private static String moveNextDay(String day) {
        int currentDayIdx = dayOfWeek.indexOf(day);
        currentDayIdx += 1;
        if (currentDayIdx == 7) {
            currentDayIdx = 0;
        }
        day = dayOfWeek.get(currentDayIdx);
        return day;
    }


    // 평일 배정
    private static void assignWhenWeekday(int month) {
        // 평일인데 공휴일인 경우
        if (Date.isHoliday(month, date)) {
            assignHolidayMember();
            return;
        }
        assignWeekdayMember();
    }

    private static void assignWeekdayMember() {
        String previousWorkMember = "";
        if (finalAssignmentResult.size() != 0) {
            previousWorkMember = finalAssignmentResult.get(finalAssignmentResult.size() - 1);
        }
        // 연속 2일 근무자라면
        if (previousWorkMember.equals(weekdayMember.get(currentPosition_weekday))) {
            replaceMemberInWeekday();
        }
        finalAssignmentResult.add(weekdayMember.get(currentPosition_weekday));
        currentPosition_weekday += 1;
        if (currentPosition_weekday == memberNumber) {
            currentPosition_weekday = 0;
        }
    }

    private static void replaceMemberInWeekday() {
        String currentMember = weekdayMember.get(currentPosition_weekday);
        weekdayMember.set(currentPosition_weekday, weekdayMember.get(currentPosition_weekday + 1));
        weekdayMember.set(currentPosition_weekday + 1, currentMember);
    }


    // 휴일 배정
    private static void assignWhenHoliday(int month) {
        assignHolidayMember();
    }

    private static void assignHolidayMember() {
        String previousWorkMember = "";
        if (finalAssignmentResult.size() != 0) {
            previousWorkMember = finalAssignmentResult.get(finalAssignmentResult.size() - 1);
        }
        // 연속 2일 근무자라면
        if (previousWorkMember.equals(holidayMember.get(currentPosition_holiday))) {
            replaceMemberInHoliday();
        }
        finalAssignmentResult.add(holidayMember.get(currentPosition_holiday));
        currentPosition_holiday += 1;
        if (currentPosition_holiday == memberNumber) {
            currentPosition_holiday = 0;
        }
    }

    private static void replaceMemberInHoliday() {
        String currentMember = holidayMember.get(currentPosition_holiday);
        holidayMember.set(currentPosition_holiday, holidayMember.get(currentPosition_holiday + 1));
        holidayMember.set(currentPosition_holiday + 1, currentMember);
    }

    public static void isAssignedOnce(List<String> weekdayMember, List<String> holidayMember) {
        for (int i = 0; i < weekdayMember.size(); i++) {
            if (!holidayMember.contains(weekdayMember.get(i))) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        }
        memberNumber = weekdayMember.size();
    }

    public static boolean isEndAssignment() {
        int lastDate = Date.getLastDateOfMonth(month);
        if (date == lastDate + 1) {
            return true;
        }
        return false;
    }
}
