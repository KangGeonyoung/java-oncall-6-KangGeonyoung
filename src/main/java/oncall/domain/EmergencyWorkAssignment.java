package oncall.domain;

import oncall.util.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class EmergencyWorkAssignment {
    private static List<String> dayOfWeek = new ArrayList<>(List.of("일", "월", "화", "수", "목", "금", "토"));
    private static List<String> weekdayMember;
    private static List<String> holidayMember;
    private static List<String> finalAssignmentResult;
    private static int month;
    private static int date;
    private static String day;

    public EmergencyWorkAssignment(List<String> weekdayMember, List<String> holidayMember, int month, String day) {
        this.weekdayMember = weekdayMember;
        this.holidayMember = holidayMember;
        this.finalAssignmentResult = new ArrayList<>();
        this.month = month;
        this.date = 1;
        this.day = day;
    }

    public static List<String> startWorkAssignment() {
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
        String previousWorkMember = finalAssignmentResult.get(finalAssignmentResult.size() - 1);
        // 연속 2일 근무자라면
        if (previousWorkMember == weekdayMember.get(0)) {
            replaceMemberInWeekday();
        }
        finalAssignmentResult.add(weekdayMember.get(0));
        weekdayMember.remove(0);
    }

    private static void replaceMemberInWeekday() {
        String currentMember = weekdayMember.get(0);
        weekdayMember.set(0, weekdayMember.get(1));
        weekdayMember.set(1, currentMember);
    }


    // 휴일 배정
    private static void assignWhenHoliday(int month) {
        assignHolidayMember();
    }

    private static void assignHolidayMember() {
        String previousWorkMember = finalAssignmentResult.get(finalAssignmentResult.size() - 1);
        // 연속 2일 근무자라면
        if (previousWorkMember == holidayMember.get(0)) {
            replaceMemberInHoliday();
        }
        finalAssignmentResult.add(holidayMember.get(0));
        holidayMember.remove(0);
    }

    private static void replaceMemberInHoliday() {
        String currentMember = holidayMember.get(0);
        holidayMember.set(0, holidayMember.get(1));
        holidayMember.set(1, currentMember);
    }

    public static void isAssignedOnce(List<String> weekdayMember, List<String> holidayMember) {
        for (int i = 0; i < weekdayMember.size(); i++) {
            if (!holidayMember.contains(weekdayMember.get(i))) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        }
    }

    public static boolean isEndAssignment() {
        int lastDate = Date.getLastDateOfMonth(month);
        if (date == lastDate + 1) {
            return true;
        }
        return false;
    }
}
