package com.example.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.exceptions.EmailNotFoundException;
import com.example.exceptions.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);


  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
          throws IOException, ServletException {
    logger.error("Unauthorized error: {}", authException.getMessage());

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    final Map<String, Object> body = new HashMap<>();

    ExceptionResponse exceptionResponse = new ExceptionResponse();

    //EmailNotFoundException

    if(authException instanceof BadCredentialsException){
      exceptionResponse.setCode(HttpStatus.UNAUTHORIZED.value());
      exceptionResponse.setMessage("Invalid username or password");
    }
    if(authException instanceof EmailNotFoundException){
      exceptionResponse.setCode(HttpStatus.NOT_FOUND.value());
      exceptionResponse.setMessage(authException.getMessage());
    }
    if(authException instanceof InsufficientAuthenticationException){
      exceptionResponse.setCode(HttpStatus.UNAUTHORIZED.value());
      exceptionResponse.setMessage("Full authentication is required to access this resource");
    }

    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    body.put("error", "Unauthorized");
    body.put("message", authException.getMessage());
    body.put("path", request.getServletPath());

    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), exceptionResponse);
  }

}
