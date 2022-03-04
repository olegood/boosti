package arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "boosti")
public class AllClassesRulesArchTest {

  @ArchTest
  ArchRule allClassesNamesRule =
      noClasses()
          .that()
          .resideOutsideOfPackages("..model..", "..domain..")
          .should()
          .haveNameMatching(".*(o|e)r$")
          .because("Recommendation from @yegor256");
}
