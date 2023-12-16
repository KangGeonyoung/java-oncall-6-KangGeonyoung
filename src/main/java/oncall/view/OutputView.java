package oncall.view;

import java.text.DecimalFormat;

public class OutputView {

    public static DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public static void printPrice(int paymentPrice) {
        System.out.println(decimalFormat.format(paymentPrice) + "원");
    }
}
