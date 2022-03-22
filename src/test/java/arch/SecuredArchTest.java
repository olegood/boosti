package arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;

@AnalyzeClasses(packages = "boosti.web.api")
public class SecuredArchTest {

  @ArchTest
  ArchRule unsafeDeleteEndpointsShouldBeSecured =
      methods()
          .that()
          .areAnnotatedWith(DeleteMapping.class)
          .should()
          .beAnnotatedWith(Secured.class);
}
