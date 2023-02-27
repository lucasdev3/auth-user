package br.com.lucasdev3.authuser.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @GetMapping("/products")
  public List<String> products() {
    return Arrays.asList("produto1", "produto13", "produto14", "produto15");
  }


}
