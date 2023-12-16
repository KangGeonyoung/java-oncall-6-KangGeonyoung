package oncall.domain;

import oncall.util.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class EmergencyWorkAssignment {

    private static List<String> weekdayMember;
    private static List<String> holidayMember;
    private static List<String> finalAssignmentResult;
    private int month;
    private String day;

    public EmergencyWorkAssignment(List<String> weekdayMember, List<String> holidayMember, int month, String day) {
        this.weekdayMember = weekdayMember;
        this.holidayMember = holidayMember;
        this.finalAssignmentResult = new ArrayList<>();
        this.month = month;
        this.day = day;
    }



    public static void isAssignedOnce(List<String> weekdayMember, List<String> holidayMember) {
        for (int i = 0; i < weekdayMember.size(); i++) {
            if (!holidayMember.contains(weekdayMember.get(i))) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        }
    }


}
