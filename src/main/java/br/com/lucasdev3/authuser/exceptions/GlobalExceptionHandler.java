package br.com.lucasdev3.authuser.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDetails> userNotFoundException(UserNotFoundException ex) {
    ErrorDetails errorModel = new ErrorDetails(404, ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ErrorDetails> tokenExpiredException(TokenExpiredException ex) {
    ErrorDetails errorModel = new ErrorDetails(400, ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorDetails> authenticationException(AuthenticationException ex) {
    ErrorDetails errorModel = new ErrorDetails(400, ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity <?> globleExcpetionHandler(Exception ex) {
    ErrorDetails errorModel = new ErrorDetails(500, ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
