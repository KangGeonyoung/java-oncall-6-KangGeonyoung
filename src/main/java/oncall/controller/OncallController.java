package oncall.controller;

import oncall.domain.Date;
import oncall.domain.EmergencyWorkAssignment;
import oncall.domain.Member;
import oncall.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class OncallController {

    private Date dateSystem;
    private Member memberSystem;
    private EmergencyWorkAssignment assignmentSystem;

    private static List<String> weekdayMember;
    private static List<String> holidayMember;

    private static List<String> finalResult;

    public OncallController() {
        this.dateSystem = new Date();
        this.memberSystem = new Member();
        weekdayMember = new ArrayList<>();
        holidayMember = new ArrayList<>();
        finalResult = new ArrayList<>();
    }

    public void start() {
        inputDate();
        int month = dateSystem.getMonth();
        String day = dateSystem.getDay();

        inputWeekdayMember();

        assignmentSystem = new EmergencyWorkAssignment(weekdayMember, holidayMember, month, day);

        List<String> finalAssignmentResult = assignmentSystem.startWorkAssignment();
    }

    private void inputHolidayMember() {
        try {
            holidayMember = memberSystem.isValidMember(InputView.readHolidayMember());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputWeekdayMember();
        }
    }

    private void inputWeekdayMember() {
        try {
            weekdayMember = memberSystem.isValidMember(InputView.readWeekdayMember());
            inputHolidayMember();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputWeekdayMember();
        }
    }

    private void inputDate() {
        try {
            dateSystem.isValidDate(InputView.readDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputDate();
        }
    }

}
