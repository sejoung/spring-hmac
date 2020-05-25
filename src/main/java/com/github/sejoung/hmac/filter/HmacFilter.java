package com.github.sejoung.hmac.filter;

import com.github.sejoung.hmac.util.HmacAuthenticationUtils;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HmacFilter extends OncePerRequestFilter {

    private String hmacKey;

    public HmacFilter(String hmacKey) {
        this.hmacKey = hmacKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain) {
        String signature = request.getHeader("Signature");
        if (StringUtils.isEmpty(signature)) {

            throw new BadCredentialsException("Signature is null");
        }
        String hmacCode = HmacAuthenticationUtils.calculateContentToSign(request, hmacKey);

        if (StringUtils.isEmpty(hmacCode)) {
            throw new BadCredentialsException("hmacCode is null");
        }

        if (!signature.equals(hmacCode)) {
            throw new BadCredentialsException(
                "hmacCode = " + hmacCode + " Signature = " + signature);
        }

    }

}
