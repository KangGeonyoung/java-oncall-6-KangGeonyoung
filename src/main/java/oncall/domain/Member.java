package oncall.domain;

import oncall.util.ErrorMessage;
import oncall.util.InputValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Member {

    public static List<String> isValidMember(String input) {
        InputValidator.isNull(input);
        InputValidator.isEmptyOrBlank(input);
        List<String> inputMember = convertMember(input);
        isDistinctMember(inputMember);

        return inputMember;
    }

    private static void isDistinctMember(List<String> inputMember) {
        Set<String> distinctMember = new HashSet<>(inputMember);
        if (inputMember.size() != distinctMember.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    public static List<String> convertMember(String input) {
        List<String> member = Stream.of(input.split(",")).collect(Collectors.toList());
        return member;
    }
}
