package oncall.view;

import oncall.domain.Date;

import java.text.DecimalFormat;

public class OutputView {

    public static void printWorkAssignmentResult(int month, int date, String day, String name) {
        String result = "";
        result += month + "월 " + date + "일 " + day;
        if (Date.isHoliday(month, date)) {
            result += "(휴일)";
        }
        result += " " + name;
        System.out.println(result);
    }

}
