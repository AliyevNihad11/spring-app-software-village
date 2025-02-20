package az.softwarevillage.book.enums;

public enum ErrorCodeEnum {
    Email_Already_Exist_Error("Email already exist!", 10),
    Username_Already_Exist_Error("Username already exist!", 20),
    User_Not_Found_Error("User not found",30),
    Unknown_Error("Unknown Error",44);

    private final String message;
    private final int code;

    ErrorCodeEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return String.valueOf(code);
    }
}
