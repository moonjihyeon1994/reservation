package com.dangunju.chatting.error;

import com.dangunju.chatting.error.exception.ReservationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static ResponseEntity<ErrorResponse> getErrorResponseEntity(ErrorCode errorCode) {
        ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(ReservationNotFoundException e) {
        return GlobalExceptionHandler.getErrorResponseEntity(ErrorCode.NOT_EXISTS_RESERVATION_ERROR);
    }

}
