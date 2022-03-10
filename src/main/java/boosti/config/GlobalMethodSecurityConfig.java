package boosti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    var handler = (DefaultMethodSecurityExpressionHandler) super.createExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy());
    return handler;
  }

  @Bean
  public RoleHierarchy roleHierarchy() {
    var roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ROOT > ROLE_AUTHOR > ROLE_USER");
    return roleHierarchy;
  }
}
