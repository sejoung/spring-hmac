package com.github.sejoung.hmac.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sejoung.hmac.api.common.constants.ErrorCode;
import com.github.sejoung.hmac.api.common.dto.Result;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ResponseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendParameterMissing(HttpServletResponse response) {
        var resultCode = ErrorCode.PARAMETER_MISSING;
        writerJsonObject(response, resultCode);
    }


    private static void writerJsonObject(HttpServletResponse response,
        ErrorCode resultCode) {
        response.setStatus(HttpServletResponse.SC_OK);
        var result = Result.builder().resultCode(resultCode.getCode().value())
            .messages(resultCode.getMessage()).build();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(result));
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
