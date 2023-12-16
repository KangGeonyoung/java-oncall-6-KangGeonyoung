package oncall.domain;

import oncall.util.ErrorMessage;
import oncall.util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Date {

    private static List<String> dayOfWeek = new ArrayList<>(List.of("일", "월", "화", "수", "목", "금", "토"));
    private static List<String> dayOfWeekday = new ArrayList<>(List.of("월", "화", "수", "목", "금"));
    private static List<String> dayOfHoliday = new ArrayList<>(List.of("1/1", "3/1", "5/5", "6/6", "8/15", "10/3", "10/9", "12/25"));
    private static List<Integer> twentyEightMonth = new ArrayList<>(List.of(2));
    private static List<Integer> thirtyMonth = new ArrayList<>(List.of(4, 6, 9, 11));
    private static List<Integer> thirtyOneMonth = new ArrayList<>(List.of(1, 3, 5, 7, 8, 10, 12));
    private static int month;
    private static String day;

    public void isValidDate(String input) {
        try {
            InputValidator.isNull(input);
            InputValidator.isEmptyOrBlank(input);
            List<String> date = convertDate(input);
            month = Integer.parseInt(date.get(0));
            isValidRangeMonth();
            day = date.get(1);
            if (!dayOfWeek.contains(day)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    private static void isValidRangeMonth() {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    public static List<String> convertDate(String input) {
        List<String> date = Stream.of(input.split(",")).collect(Collectors.toList());
        return date;
    }

    public static int getLastDateOfMonth(int month) {
        int lastDate = 0;
        if (twentyEightMonth.contains(month)) {
            lastDate = 28;
        }
        if (thirtyMonth.contains(month)) {
            lastDate = 30;
        }
        if (thirtyOneMonth.contains(month)) {
            lastDate = 31;
        }
        return lastDate;
    }

    public static boolean isWeekday(String day) {
        if (dayOfWeekday.contains(day)) {
            return true;
        }
        return false;
    }

    public static boolean isHoliday(int month, int date) {
        String compareDay = month + "/" + date;
        if (dayOfHoliday.contains(compareDay)) {
            return true;
        }
        return false;
    }

    public int getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }
}
