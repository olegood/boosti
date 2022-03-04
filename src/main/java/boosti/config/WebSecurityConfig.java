package boosti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  public WebSecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    //
    // todo: switch to database storage
    // auth.userDetailsService(userDetailsService);
    //
    auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("root@email.org")
        .password(passwordEncoder().encode("root@email.org"))
        .roles("ROOT")
        .and()
        .withUser("author@email.org")
        .password(passwordEncoder().encode("author@email.org"))
        .roles("AUTHOR")
        .and()
        .withUser("user@email.org")
        .password(passwordEncoder().encode("user@email.org"))
        .roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/api/**")
        .fullyAuthenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
