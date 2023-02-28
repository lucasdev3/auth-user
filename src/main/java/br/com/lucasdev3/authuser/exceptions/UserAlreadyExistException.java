package br.com.lucasdev3.authuser.exceptions;

public class UserAlreadyExistException extends RuntimeException {

  public UserAlreadyExistException(String message) {

    super(message);

  }

}
