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


    public boolean isEndAssignment(int date) {
        int lastDate = Date.getLastDateOfMonth(month);
        if (date == lastDate) {
            return true;
        }
        return false;
    }

    private static void assignWhenWeekday(int month, String day) {
        if (Date.isWeekday(day)) {
            // 평일인데 공휴일인 경우
            if (Date.isHoliday(month, date)) {
                finalAssignmentResult.add(holidayMember.get(0));
                holidayMember.remove(0);
                return;
            }
            finalAssignmentResult.add(weekdayMember.get(0));
            weekdayMember.remove(0);
        }
    }

    private static void assignWhenHoliday(String day) {
        if (!Date.isWeekday(day)) {
            finalAssignmentResult.add(holidayMember.get(0));
            holidayMember.remove(0);
        }
    }

    public static void isAssignedOnce(List<String> weekdayMember, List<String> holidayMember) {
        for (int i = 0; i < weekdayMember.size(); i++) {
            if (!holidayMember.contains(weekdayMember.get(i))) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        }
    }


}
