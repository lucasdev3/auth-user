package br.com.lucasdev3.authuser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @PostMapping("/")
  public String login() {
    return "Home";
  }

}
