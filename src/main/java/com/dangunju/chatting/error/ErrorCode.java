package com.dangunju.chatting.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_EXISTS_RESERVATION_ERROR(HttpStatus.NOT_FOUND, "400.1", "등록되지 않은 예약번호"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
