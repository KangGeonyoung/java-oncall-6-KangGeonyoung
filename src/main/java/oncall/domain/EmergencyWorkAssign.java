package oncall.domain;

import java.util.List;

public class EmergencyWorkAssign {

    private List<String> weekdayMember;
    private List<String> holidayMember;
    private int month;
    private String day;

    public EmergencyWorkAssign(List<String> weekdayMember, List<String> holidayMember, int month, String day) {
        this.weekdayMember = weekdayMember;
        this.holidayMember = holidayMember;
        this.month = month;
        this.day = day;
    }

}
