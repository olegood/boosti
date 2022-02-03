package boosti;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.util.Arrays.stream;

import org.junit.jupiter.api.Test;

class SelenideDemoUITest {

  @Test
  void aboutPageShouldHaveAllInformation() {
    open("http://localhost:3000/");
    $(byText("About")).click();

    checkElementsExist("Name:", "Group:", "Artifact:", "Version:", "React:");
  }

  void checkElementsExist(String... values) {
    stream(values).forEach(value -> $(byText(value)).should(exist));
  }
}
