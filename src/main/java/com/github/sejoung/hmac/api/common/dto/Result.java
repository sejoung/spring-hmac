package com.github.sejoung.hmac.api.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Result {

    @JsonProperty("ResultCode")
    private int resultCode;
    @JsonProperty("Messages")
    private String messages;

    @Builder
    private Result(int resultCode, String messages) {
        this.resultCode = resultCode;
        this.messages = messages;
    }

}
