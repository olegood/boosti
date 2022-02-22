package boosti.service.conversion.target;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class XmlAsQuestionDataCollectionTest {

  @Test
  void shouldGetContentFromValidXmlFile() throws Exception {
    // given
    var path = Path.of("src/test/resources/import/question/xml").resolve("questions_valid.xml");
    var fileAsString = Files.readString(path);

    // when
    var result = new XmlAsQuestionDataCollection(fileAsString).content();

    // then
    assertThat(result, hasSize(2));
  }
}
