package br.com.lucasdev3.authuser;

import br.com.lucasdev3.authuser.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthUserProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthUserProjectApplication.class, args);

    User user = new User();

  }

}
