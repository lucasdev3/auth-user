package br.com.lucasdev3.authuser.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDetails> userNotFoundException(UserNotFoundException ex) {
    ErrorDetails errorModel = new ErrorDetails(404, ex.getMessage());
    logger.error("Status: " + HttpStatus.NOT_FOUND.value() + " | " + ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorDetails> userAlreadyExistException(UserAlreadyExistException ex) {
    ErrorDetails errorModel = new ErrorDetails(400, ex.getMessage());
    logger.error("Status: " + HttpStatus.NOT_FOUND.value() + " | " + ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ErrorDetails> tokenExpiredException(TokenExpiredException ex) {
    ErrorDetails errorModel = new ErrorDetails(400, ex.getMessage());
    logger.error("Status: " + HttpStatus.BAD_REQUEST.value() + " | " + ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorDetails> authenticationException(AuthenticationException ex) {
    ErrorDetails errorModel = new ErrorDetails(400, ex.getMessage());
    logger.error("Status: " + HttpStatus.BAD_REQUEST.value() + " | " + ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globleExcpetionHandler(Exception ex) {
    ErrorDetails errorModel = new ErrorDetails(500, ex.getMessage());
    logger.error("Status: " + HttpStatus.INTERNAL_SERVER_ERROR.value() + " | " + ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
