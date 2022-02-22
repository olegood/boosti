package boosti.service.conversion.target;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class JsonAsQuestionDataCollectionTest {

  @Test
  void shouldGetContentFromValidJsonFile() throws Exception {
    // given
    var path = Path.of("src/test/resources/import/question/json").resolve("questions_valid.json");
    var fileAsString = Files.readString(path);

    // when
    var result = new JsonAsQuestionDataCollection(fileAsString).content();

    // then
    assertThat(result, hasSize(1));
  }
}
