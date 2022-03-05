package boosti.service;

import static java.util.Collections.emptySet;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Set;

import boosti.domain.security.User;
import boosti.repo.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

  @Mock UserRepository userRepository;
  @InjectMocks UserDetailsServiceImpl userDetailsService;

  @Test
  void shouldThrowUserNotFoundIfNoUserExist() {
    // given
    when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(empty());

    // when
    var exception =
        assertThrows(
            UsernameNotFoundException.class,
            () -> userDetailsService.loadUserByUsername("unknown@email.com"));

    // then
    var expectedMessage = "User not found: <unknown@email.com>";
    var actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  @Test
  void shouldSetUsernameAsEmailAndPassword() {
    // given
    when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(of(user()));

    // when
    var details = userDetailsService.loadUserByUsername("fake@email.com");

    // then
    assertThat(details.getUsername(), is(notNullValue()));
    assertThat(details.getPassword(), is(notNullValue()));
  }

  @Test
  void shouldSafelyConvertRolesToEmptySetIfRolesIsNull() {
    // given
    var user = userWithRoles(null);
    when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(of(user));

    // when
    var details = userDetailsService.loadUserByUsername("any@email.com");

    // then
    assertThat(details.getAuthorities(), hasSize(0));
  }

  @Test
  void shouldSetRolesAsAuthorities() {
    // given
    var user = userWithRoles(Set.of("ADMIN", "USER", "CONTENT_MANAGER"));
    when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(of(user));

    // when
    var details = userDetailsService.loadUserByUsername("any@email.com");

    // then
    assertThat(
        details.getAuthorities(),
        containsInAnyOrder(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER"),
            new SimpleGrantedAuthority("ROLE_CONTENT_MANAGER")));
  }

  private User user() {
    var user = new User();

    user.setEmail("test@email.com");
    user.setPassword("<password>");
    user.setRoles(emptySet());

    return user;
  }

  private User userWithRoles(Set<String> roles) {
    var user = user();
    user.setRoles(roles);
    return user;
  }
}
