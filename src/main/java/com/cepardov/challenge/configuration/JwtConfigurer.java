package com.cepardov.challenge.configuration;

import com.cepardov.challenge.service.TokenService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final TokenService tokenService;

  public JwtConfigurer(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void configure(HttpSecurity http) {
    JwtTokenFilter customFilter = new JwtTokenFilter(tokenService, null);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
