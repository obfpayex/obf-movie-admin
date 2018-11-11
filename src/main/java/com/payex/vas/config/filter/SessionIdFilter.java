package com.payex.vas.config.filter;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.payex.vas.util.Constants.SESSION_ID;

@Configuration
public class SessionIdFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(SessionIdFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        appendSessionIdToLogContext(request);
        filterChain.doFilter(request, response);
    }

    private void appendSessionIdToLogContext(HttpServletRequest request) {
        try {
            if (request.getRequestURI().startsWith("/api")) {
                String sessionId = getSessionId(request);
                MDC.put(SESSION_ID, sessionId);
            }
        } catch (Exception ex) {
            log.warn("Unable to set SessionId on Logger.", ex);
        }
    }

    private String getSessionId(HttpServletRequest request) {
        String sessionId = request.getHeader(SESSION_ID);
        if (sessionId == null || sessionId.length() != 36) {
            sessionId = UUID.randomUUID().toString();
            log.warn("Generating a new session id: '{}'", sessionId);
        }
        return sessionId;
    }
}
