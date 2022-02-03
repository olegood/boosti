package boosti;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static java.util.Arrays.stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BooTIApplicationSmokeUITest {

  @BeforeAll
  static void beforeAll() {
    open("http://localhost:3000/");
  }

  @AfterAll
  static void afterAll() {
    closeWebDriver();
  }

  @Test
  void smokeAllTabsExist() {
    // expect
    checkTextElementsPresent("Questions", "Import", "About");
  }

  @Test
  void smokeTabQuestions() {
    // when
    $(byText("Questions")).click();

    // then
    $(byText("No rows")).should(exist);
    $(byText("Export Selected")).should(exist, disabled);
  }

  @Test
  void smokeTabImport() {
    // when
    $(byText("Import")).click();

    // then
    $(byText("Import File")).should(exist);
    $(byText("Browse")).should(exist, enabled);
    $(byText("Select a file to show details")).should(exist);
    $(byText("Upload")).should(exist, disabled);
  }

  @Test
  void smokeTabAbout() {
    // when
    $(byText("About")).click();

    // then
    checkTextElementsPresent("Name:", "Group:", "Artifact:", "Version:", "React:");
  }

  void checkTextElementsPresent(String... values) {
    stream(values).forEach(value -> $(byText(value)).should(exist));
  }
}
