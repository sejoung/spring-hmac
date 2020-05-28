package com.github.sejoung.hmac.filter;

import com.github.sejoung.hmac.util.HmacAuthenticationUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HmacFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;
    private String hmacKey;

    public HmacFilter(String hmacKey, AuthenticationManager authenticationManager) {
        this.hmacKey = hmacKey;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws IOException, ServletException {

        String signature = request.getHeader("Signature");
        if (StringUtils.isEmpty(signature)) {
            log.debug("signature is null ");
            response
                .sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        String hmacCode = HmacAuthenticationUtils.calculateContentToSign(request, hmacKey);

        if (StringUtils.isEmpty(hmacCode)) {
            log.debug("hmacCode is null ");
            response
                .sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        if (!signature.equals(hmacCode)) {
            log.debug("hmacCode = {} signature = {}", hmacCode, signature);
            response
                .sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken("user", "user"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }

}
