package oncall.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    private Date dateSystem;
    @BeforeEach
    void setUp() {
        dateSystem = new Date();
    }

    @Test
    void 월_범위_벗어났을경우() {
        String input = "0,화";
        assertThrows(IllegalArgumentException.class, () -> {
            dateSystem.isValidDate(input);
        });
    }

    @Test
    void 요일_숫자입력할경우() {
        String input = "1,13";
        assertThrows(IllegalArgumentException.class, () -> {
            dateSystem.isValidDate(input);
        });
    }

    @Test
    void 공휴일_정상작동_확인() {
        int month = 1;
        int date = 1;
        boolean result = dateSystem.isHoliday(month, date);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    void 월요일_평일인지_확인() {
        String day = "월";
        boolean result = dateSystem.isWeekday(day);
        Assertions.assertThat(result).isEqualTo(true);
    }
}