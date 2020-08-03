package com.cepardov.challenge.configuration;

import com.cepardov.challenge.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author cepardov on 02-08-20
 */
public class JwtTokenFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public JwtTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        String token = tokenService.resolveToken((HttpServletRequest) req);
        if (token != null && !token.isEmpty() && tokenService.validateToken(token)) {
            Authentication auth = tokenService.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }
}
