package com.github.sejoung.hmac.util;

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
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public abstract class HmacAuthenticationUtils {


    public static String calculateContentToSign(HttpServletRequest servletRequest,
        String keyString) {
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(servletRequest);
        return generateHMAC(new String(request.getContentAsByteArray()), keyString);

    }

    public static String generateHMAC(String msg, String keyString) {
        Mac mac = HmacUtils
            .getInitializedMac(HmacAlgorithms.HMAC_SHA_256,
                keyString.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(bytes);
    }

}
