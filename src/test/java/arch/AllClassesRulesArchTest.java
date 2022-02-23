package arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "boosti")
public class AllClassesRulesArchTest {

  @ArchTest
  ArchRule allClassesNamesRule =
      classes()
          .that()
          .resideOutsideOfPackages("..model..")
          .should()
          .haveNameNotMatching(".*(or|er)$");
}
