package com.snapp.dangidongi.security;

import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user =
        this.userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("cannot find username: " + username));
    return new UserDetail(user);
  }
}
