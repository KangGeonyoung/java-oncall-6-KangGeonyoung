package oncall.domain;

import oncall.util.ErrorMessage;
import oncall.util.InputValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Member {

    public List<String> isValidMember(String input) {
        InputValidator.isNull(input);
        InputValidator.isEmptyOrBlank(input);
        List<String> inputMember = convertMember(input);
        isValidType(inputMember);
        isDistinctMember(inputMember);
        isExceedNameLength(inputMember);
        return inputMember;
    }

    private static void isValidType(List<String> inputMember) {
        for (int i = 0; i < inputMember.size(); i++) {
            String word = inputMember.get(i);
            isLetter(word);
        }
    }

    private static void isLetter(String word) {
        for (int i = 0; i < word.length(); i++) {
            char digit = word.charAt(i);
            if (Character.isDigit(digit) == true) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        }
    }

    private static void isExceedNameLength(List<String> inputMember) {
        for (int i = 0; i < inputMember.size(); i++) {
            if (inputMember.get(i).length() > 5) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
            }
        }
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
