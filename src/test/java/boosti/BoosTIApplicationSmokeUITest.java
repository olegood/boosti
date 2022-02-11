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
import org.junit.jupiter.api.Test;

class BoosTIApplicationSmokeUITest {

  @AfterAll
  static void afterAll() {
    closeWebDriver();
  }

  @Test
  void smokePageQuestions() {
    // when
    navigate("/questions");

    // then
    $(byText("Export Selected")).should(exist, disabled);
    $(byText("Delete Selected")).should(exist, disabled);
  }

  @Test
  void smokePageImport() {
    // when
    navigate("/import");

    // then
    $(byText("Import File")).should(exist);
    $(byText("Browse")).should(exist, enabled);
    $(byText("Select a file to show details")).should(exist);
    $(byText("Upload")).should(exist, disabled);
  }

  @Test
  void smokePageAbout() {
    // when
    navigate("/about");

    // then
    checkTextElementsPresent("Name:", "Group:", "Artifact:", "Version:", "React:");
  }

  void checkTextElementsPresent(String... values) {
    stream(values).forEach(value -> $(byText(value)).should(exist));
  }

  private void navigate(String relativeUrl) {
    open("http://localhost:3000" + relativeUrl);
  }
}
