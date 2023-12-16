package oncall.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Member memberSystem;
    @BeforeEach
    void setUp() {
        memberSystem = new Member();
    }

    @Test
    void 닉네임_문자아닌경우() {
        String input = "하원,주니,123,제발";
        assertThrows(IllegalArgumentException.class, () -> {
            memberSystem.isValidMember(input);
        });
    }

    @Test
    void 닉네임_5자_초과할경우() {
        String input = "어려워요살려주세요,하원,제발";
        assertThrows(IllegalArgumentException.class, () -> {
            memberSystem.isValidMember(input);
        });
    }
}