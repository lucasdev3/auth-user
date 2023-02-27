package br.com.lucasdev3.authuser.service;

import br.com.lucasdev3.authuser.entities.User;
import br.com.lucasdev3.authuser.models.RegisterModel;
import br.com.lucasdev3.authuser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElse(null);
  }

  public void register(RegisterModel registerModel) {
    try {
      if (!userRepository.existsByUsername(registerModel.username())) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User(registerModel.username(),
            bCryptPasswordEncoder.encode(registerModel.password()));
        userRepository.save(user);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
