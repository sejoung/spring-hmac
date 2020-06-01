package com.github.sejoung.hmac.util;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.github.sejoung.hmac.api.common.constants.ErrorCode;
import org.springframework.test.web.servlet.ResultMatcher;

public abstract class ResultCodeUtil {

    public static ResultMatcher getResultCode(ErrorCode resultCode) {
        return jsonPath("$.ResultCode").value(resultCode.getCode().value());
    }

    public static ResultMatcher getMessages(ErrorCode resultCode) {
        return jsonPath("$.Messages").value(resultCode.getMessage());
    }
}
