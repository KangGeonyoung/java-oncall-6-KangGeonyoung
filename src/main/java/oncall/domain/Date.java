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
}
