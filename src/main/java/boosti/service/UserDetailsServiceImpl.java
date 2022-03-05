package boosti.service;

import static java.util.Optional.ofNullable;
import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Collection;
import java.util.stream.Stream;

import boosti.domain.security.User;
import boosti.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
        .findByEmailIgnoreCase(email)
        .map(this::toUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: <" + email + ">"));
  }

  private UserDetails toUserDetails(User user) {
    return withUsername(user.getEmail())
        .password(user.getPassword())
        .roles(getRolesFrom(user))
        .build();
  }

  private String[] getRolesFrom(User user) {
    return safeStream(user.getRoles()).toArray(String[]::new);
  }

  private <E> Stream<E> safeStream(Collection<E> collection) {
    return ofNullable(collection).stream().flatMap(Collection::stream);
  }
}
