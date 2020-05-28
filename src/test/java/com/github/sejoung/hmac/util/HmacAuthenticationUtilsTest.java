package com.github.sejoung.hmac.util;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;

@Slf4j
public class HmacAuthenticationUtilsTest {

    @Test
    public void 메시지_바디가_있을때_정상() {
        MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.POST.name(), "test");
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String msg = "{\"loyPgmNo\":\"LP0001\",\"trackingNo\":\"7f8d1e25-e766-11e6-91bc-a0b3ccc9c371\",\"tgNo\":\"A100\",\"siteCode\":\"30\",\"csrId\":\"test001\",\"pbpId\":\"1234567890\",\"actionType\":\"MW\"}";
        request.setContent(msg.getBytes());
        String keyString = "1234567890";
        String hmac = HmacAuthenticationUtils.calculateContentToSign(request, keyString);
        Assertions.assertThat(hmac.toUpperCase()).as("틀려요")
            .isEqualTo("FC6304929A09E8BBEA24EF2F31762F2BA5445BB18F7427C0536147516C35D1D5");
    }


    @Test
    public void 메시지_바디가_없을때_정상() {
        MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.POST.name(), "test");
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String keyString = "1234567890";
        String hmac = HmacAuthenticationUtils.calculateContentToSign(request, keyString);
        Assertions.assertThat(hmac.toUpperCase()).as("틀려요")
            .isEqualTo("BA92E0BFA35D8A018831F3675F0B4314C8468D4D96E6981B85BA3390C23308FD");
    }
}