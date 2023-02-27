package br.com.lucasdev3.authuser.service;

import br.com.lucasdev3.authuser.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  public String generateToken(User user) {
    return JWT.create()
        .withIssuer("Products")
        .withSubject(user.getUsername())
        .withClaim("id", user.getId())
        .withExpiresAt(LocalDateTime.now().plusMinutes(1).toInstant(ZoneOffset.of("-03:00")))
        .sign(Algorithm.HMAC256("secreta"));
  }

  public String getSubject (String token) {
    return JWT.require(Algorithm.HMAC256("secreta"))
        .withIssuer("Products")
        .build().verify(token).getSubject();
  }

}
