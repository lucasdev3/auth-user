package br.com.lucasdev3.authuser.service;

import br.com.lucasdev3.authuser.entities.User;
import br.com.lucasdev3.authuser.exceptions.UserAlreadyExistException;
import br.com.lucasdev3.authuser.exceptions.UserNotFoundException;
import br.com.lucasdev3.authuser.models.RegisterModel;
import br.com.lucasdev3.authuser.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService, UserDetailsPasswordService {

  @Autowired
  private UserRepository userRepository;

  private static final Logger logger = Logger.getLogger(AuthenticationService.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(() -> {
      logger.info("Nenhum usuario encontrado!");
      throw new UserNotFoundException("Nenhum usuario encontrado!");
    });
  }

  public void register(RegisterModel registerModel) {
    Boolean userFound = userRepository.existsByUsername(registerModel.username());
    if (!userFound) {
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      User user = new User(registerModel.username(),
          bCryptPasswordEncoder.encode(registerModel.password()));
      user.setAuthorities("ROLE_USER");
      userRepository.save(user);
      logger.info("Usuario cadastrado com sucesso!");
    } else {
      throw new UserAlreadyExistException("Usuario já cadastrado!");
    }
  }

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    return null;
  }

}
