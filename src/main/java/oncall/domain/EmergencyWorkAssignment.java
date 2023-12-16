package oncall.domain;

import oncall.util.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class EmergencyWorkAssignment {

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

    public static void startWorkAssignment(int month, String day) {
        // 평일일때 평일 사원 명단에서 추출
        assignWhenWeekday(month, day);

        // 휴일 일때 휴일 사원 명단에서 추출
        assignWhenHoliday(day);
    }



    // 평일 배정
    private static void assignWhenWeekday(int month, String day) {
        if (Date.isWeekday(day)) {
            // 평일인데 공휴일인 경우
            if (Date.isHoliday(month, date)) {
                assignHolidayMember();
                return;
            }
            assignWeekdayMember();
        }
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
    private static void assignWhenHoliday(String day) {
        if (!Date.isWeekday(day)) {
            assignHolidayMember();
        }
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

    public boolean isEndAssignment(int date) {
        int lastDate = Date.getLastDateOfMonth(month);
        if (date == lastDate) {
            return true;
        }
        return false;
    }
}
