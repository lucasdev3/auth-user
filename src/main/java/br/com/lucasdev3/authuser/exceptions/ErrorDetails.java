package br.com.lucasdev3.authuser.exceptions;

import lombok.Getter;

@Getter
public class ErrorDetails {

  private Integer status;

  private String message;

  public ErrorDetails(Integer status, String message) {

    super();

    this.status = status;

    this.message = message;

  }

}
