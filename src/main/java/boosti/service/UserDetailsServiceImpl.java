package boosti.service;

import static java.util.Optional.ofNullable;
import static org.springframework.security.core.userdetails.User.withUsername;

import boosti.domain.User;
import boosti.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepo;

  public UserDetailsServiceImpl(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepo
        .findByEmailIgnoreCase(email)
        .map(this::toUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: <" + email + ">"));
  }

  private UserDetails toUserDetails(User user) {
    var userBuilder = withUsername(user.getEmail()).password(user.getPassword());
    ofNullable(user.getRole()).ifPresentOrElse(userBuilder::roles, userBuilder::roles);
    return userBuilder.build();
  }
}
