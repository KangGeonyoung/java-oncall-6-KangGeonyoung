package oncall.util;

public enum ErrorMessage {
    INVALID_INPUT("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    STRING_IS_NULL("[ERROR] 해당 입력 값이 NULL 입니다."),
    STRING_IS_EMPTY_OR_BLANK("[ERROR] 해당 입력 값에 공백이 있거나 빈 값입니다.");

    private String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
