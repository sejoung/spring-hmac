package com.github.sejoung.hmac.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.io.IOUtils;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class HmacAuthenticationUtils {

    public static String calculateContentToSign(HttpServletRequest request,
        String keyString) {
        log.debug("keyString = {}", keyString);

        return generateHMAC(convertHttpRequest(request), keyString);
    }

    private static String convertHttpRequest(HttpServletRequest request) {

        try (var reader = request.getReader()) {
            return IOUtils.toString(reader);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

    private static String generateHMAC(String message, String keyString) {
        log.debug("generateHMAC message = {}", message);
        var mac = HmacUtils
            .getInitializedMac(HmacAlgorithms.HMAC_SHA_256,
                keyString.getBytes(StandardCharsets.UTF_8));
        var bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(bytes);
    }

}
