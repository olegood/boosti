package arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

@AnalyzeClasses(packages = "boosti", importOptions = ImportOption.OnlyIncludeTests.class)
public class UnitTestRulesArchTest {

  @ArchTest
  ArchRule unitTestShouldNotStartsWithTest =
      noMethods()
          .that()
          .areAnnotatedWith(Test.class)
          .should()
          .haveNameMatching("test.*")
          .because("");

  @ArchTest
  ArchRule unitTestShouldNotHavePublicOrStaticModifiers =
      noMethods().that().areAnnotatedWith(Test.class).should().bePublic().because("");
}
