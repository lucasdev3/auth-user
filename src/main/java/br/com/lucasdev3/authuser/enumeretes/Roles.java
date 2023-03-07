package br.com.lucasdev3.authuser.enumeretes;

import lombok.ToString;

@ToString
public enum Roles {

  USER("USER"),
  ADMIN("USER");

  Roles(String value) {
  }
}
