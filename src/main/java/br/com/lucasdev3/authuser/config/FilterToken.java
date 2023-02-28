package br.com.lucasdev3.authuser.config;

import br.com.lucasdev3.authuser.entities.User;
import br.com.lucasdev3.authuser.repositories.UserRepository;
import br.com.lucasdev3.authuser.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterToken extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;

  @Autowired
  UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String token;
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
      token = authorizationHeader.replace("Bearer", "").trim();
      String subject = this.tokenService.getSubject(token);
      User user = this.userRepository.findByUsername(subject).orElse(null);
      if (user != null) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    filterChain.doFilter(request, response);

  }
}
