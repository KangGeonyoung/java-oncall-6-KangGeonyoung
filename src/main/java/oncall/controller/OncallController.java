package oncall.controller;

import oncall.domain.Date;
import oncall.domain.EmergencyWorkAssignment;
import oncall.domain.Member;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class OncallController {

    private static List<String> dayOfWeek = new ArrayList<>(List.of("일", "월", "화", "수", "목", "금", "토"));
    private Date dateSystem;
    private Member memberSystem;
    private EmergencyWorkAssignment assignmentSystem;

    private static List<String> weekdayMember;
    private static List<String> holidayMember;

    private static List<String> finalResult;
    private static int month;
    private static int date;
    private static String day;


    public OncallController() {
        this.dateSystem = new Date();
        this.memberSystem = new Member();
        weekdayMember = new ArrayList<>();
        holidayMember = new ArrayList<>();
        finalResult = new ArrayList<>();
        date = 1;
    }

    public void start() {
        inputDate();
        month = dateSystem.getMonth();
        day = dateSystem.getDay();
        inputWeekdayMember();
        startWorkAssignment(month, day, date);
    }

    private void startWorkAssignment(int month, String day, int date) {
        assignmentSystem = new EmergencyWorkAssignment(weekdayMember, holidayMember, month, day);
        List<String> finalAssignmentResult = assignmentSystem.startWorkAssignment();
        printWorkAssignmentResult(month, date, day, finalAssignmentResult);
    }

    private static void printWorkAssignmentResult(int month, int date, String day, List<String> finalAssignmentResult) {
        for (int i = 0; i < Date.getLastDateOfMonth(month); i++) {
            OutputView.printWorkAssignmentResult(month, date, day, finalAssignmentResult.get(i));
            day = moveNextDay(day);
            date += 1;
        }
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

    private static String moveNextDay(String day) {
        int currentDayIdx = dayOfWeek.indexOf(day);
        currentDayIdx += 1;
        if (currentDayIdx == 7) {
            currentDayIdx = 0;
        }
        day = dayOfWeek.get(currentDayIdx);
        return day;
    }
}
