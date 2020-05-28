package com.github.sejoung.hmac.util;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public abstract class HmacAuthenticationUtils {


    public static String calculateContentToSign(HttpServletRequest servletRequest,
        String keyString) {
        log.debug("keyString = {}", keyString);
        HttpServletRequest request = new ContentCachingRequestWrapper(servletRequest);
        return generateHMAC(convertHttpRequest(request), keyString);
    }

    private static String convertHttpRequest(HttpServletRequest request) {
        try (Reader reader = request.getReader()) {
            return IOUtils.toString(reader);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

    private static String generateHMAC(String message, String keyString) {
        log.debug("generateHMAC message = {}", message);
        Mac mac = HmacUtils
            .getInitializedMac(HmacAlgorithms.HMAC_SHA_256,
                keyString.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(bytes);
    }

}
