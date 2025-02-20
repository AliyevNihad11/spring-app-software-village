package az.softwarevillage.book.exception;

import az.softwarevillage.book.dto.response.ErrorResponse;
import az.softwarevillage.book.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setErrorCode(ErrorCodeEnum.User_Not_Found_Error.getCode());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUserNotFoundException(Exception e) {
        e.printStackTrace();
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ErrorCodeEnum.Unknown_Error.getMessage());
        error.setErrorCode(ErrorCodeEnum.Unknown_Error.getCode());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNumberFormatException(MethodArgumentTypeMismatchException e) {

        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setTimestamp(LocalDateTime.now());
        return error;
    }


}
