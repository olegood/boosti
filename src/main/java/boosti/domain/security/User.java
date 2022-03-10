package boosti.domain.security;

import java.util.Set;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Security entity: {@code User}
 *
 * @author Oleg Anastassov
 */
@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;

  @Convert(converter = UserRolesAttribute.class)
  private Set<String> roles;
}
