package arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(
    packages = "boosti",
    importOptions = {ImportOption.DoNotIncludeTests.class})
public class ApiClassesArchTest {

  @ArchTest
  ArchRule endPointClassesRule =
      classes()
          .that()
          .resideInAPackage("..api..")
          .should()
          .beAnnotatedWith(RestController.class)
          .andShould()
          .haveSimpleNameStartingWith("EndPoint");
}
