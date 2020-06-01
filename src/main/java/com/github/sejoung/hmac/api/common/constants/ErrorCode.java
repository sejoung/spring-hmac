package com.github.sejoung.hmac.api.common.constants;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.sejoung.hmac.api.common.serializer.ErrorCodeSerializer;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonSerialize(using = ErrorCodeSerializer.class)
@Getter
public enum ErrorCode {

    PARAMETER_UNKNOWN(HttpStatus.BAD_REQUEST, "파라미터 오류"),
    PARAMETER_MISSING(HttpStatus.BAD_REQUEST, "파라미터 없음"),
    UNSUPPORTED_OPERATION(HttpStatus.NOT_IMPLEMENTED, "지원하지 않는 명령");

    private HttpStatus code;
    private String message;

    ErrorCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

}

