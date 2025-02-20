package az.softwarevillage.book.dto.response;


import java.time.LocalDateTime;

public class ErrorResponse {
    private Integer errorCode;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String errorCode, String message,LocalDateTime timestamp) {
        this.errorCode = Integer.valueOf(errorCode);
        this.message = message;
        this.timestamp=timestamp;
    }

    public ErrorResponse() {
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = Integer.valueOf(errorCode);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
