package br.com.lucasdev3.authuser.controller;

import br.com.lucasdev3.authuser.entities.User;
import br.com.lucasdev3.authuser.models.LoginModel;
import br.com.lucasdev3.authuser.models.RegisterModel;
import br.com.lucasdev3.authuser.service.AuthenticationService;
import br.com.lucasdev3.authuser.service.TokenService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private final AuthenticationManager authenticationManager;

  @Autowired
  private final TokenService tokenService;

  @Autowired
  private final AuthenticationService authenticationService;

  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService,
      AuthenticationService authenticationService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(loginModel.username(), loginModel.password());
    Authentication authenticate = this.authenticationManager.authenticate(
        usernamePasswordAuthenticationToken);
    User user = (User) authenticate.getPrincipal();
    return new ResponseEntity<>(Arrays.asList(tokenService.generateToken(user), user), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterModel registerModel) {
    try {
      authenticationService.register(registerModel);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>("Falha ao cadastrar usuario!", HttpStatus.BAD_REQUEST);
  }

}
