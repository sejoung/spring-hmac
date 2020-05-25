package com.github.sejoung.hmac.util;

import java.io.IOException;
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

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public abstract class HmacAuthenticationUtils {


    public static String calculateContentToSign(HttpServletRequest request, String keyString) {

        try (Reader reader = request.getReader()) {
            String msg = IOUtils.toString(reader);
            return generateHMAC(msg, keyString);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static String generateHMAC(String msg, String keyString) {
        Mac mac = HmacUtils
            .getInitializedMac(HmacAlgorithms.HMAC_SHA_256,
                keyString.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(bytes);
    }

}
