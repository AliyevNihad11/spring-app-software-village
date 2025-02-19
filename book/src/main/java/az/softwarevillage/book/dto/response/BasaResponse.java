package az.softwarevillage.book.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public class BasaResponse {
    private int code;
    private String message;
    private LocalDateTime timestamp;

    public BasaResponse(int code, String message, LocalDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    public BasaResponse(int i, String s) {
        // default constructor
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    // Success mesajını qaytaracaq metod
    public static BasaResponse getSuccessMessage() {
        // `new BasaResponse()` ilə müvəffəqiyyət mesajı obyektini yarat
        return new BasaResponse(200, "Operation successful",LocalDateTime.now());
    }
}
