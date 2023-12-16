package oncall.domain;

import oncall.util.ErrorMessage;
import oncall.util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Date {

    private static List<String> dayOfWeek = new ArrayList<>(List.of("일", "월", "화", "수", "목", "금", "토"));
    private static int month;
    private static String day;

    public static void isValidDate(String input) {
        try {
            InputValidator.isNull(input);
            InputValidator.isEmptyOrBlank(input);
            List<String> date = convertDate(input);
            month = Integer.parseInt(date.get(0));
            day = date.get(1);
            if (!dayOfWeek.contains(day)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    public static List<String> convertDate(String input) {
        List<String> date = Stream.of(input.split(",")).collect(Collectors.toList());
        return date;
    }

    public int getLastDateOfMonth(int month) {
        List<Integer> twentyEightMonth = new ArrayList<>(List.of(2));
        List<Integer> thirtyMonth = new ArrayList<>(List.of(4, 6, 9, 11));
        List<Integer> thirtyOneMonth = new ArrayList<>(List.of(1, 3, 5, 7, 8, 10, 12));
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
}
