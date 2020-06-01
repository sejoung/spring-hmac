package com.github.sejoung.hmac.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sejoung.hmac.api.common.constants.ErrorCode;
import com.github.sejoung.hmac.api.common.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

@Slf4j
public class ResponseUtilsTest {


    private String getJson(ErrorCode resultCode) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var result = Result.builder().resultCode(resultCode.getCode().value())
            .messages(resultCode.getMessage()).build();
        return objectMapper.writeValueAsString(result);
    }

    @Test
    public void sendSignatureEmptyError() throws JsonProcessingException {
        MockHttpServletResponse response = new MockHttpServletResponse();
        ResponseUtils.sendParameterMissing(response);
        var json = new String(response.getContentAsByteArray());
        Assertions.assertThat(json).as("시그니처 없음")
            .isEqualTo(getJson(ErrorCode.PARAMETER_MISSING));

    }

}