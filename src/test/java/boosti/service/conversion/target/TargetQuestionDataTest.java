package boosti.service.conversion.target;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import boosti.web.model.QuestionData;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// todo: check tests names
class TargetQuestionDataTest {

  @Test
  void shouldReturnSingletonListForOneValidQuestion() {
    // given
    var questionAsData = new TargetQuestionData("Java,What is JVM?");
    var expected = QuestionData.builder().withText("What is JVM?").build();

    // when
    var result = questionAsData.content();

    // then
    assertThat(result, Matchers.is(expected));
  }

  @Test
  void shouldThrowExceptionIfInputValueHasNoSeparatorInIt() {
    // given
    var questionAsData = new TargetQuestionData("Value that does not contain separator");

    // when
    var exception = assertThrows(RuntimeException.class, questionAsData::content);

    // then
    var expectedMessage =
        "Input value cannot be parsed, separator=, source=Value that does not contain separator";
    var actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  @ParameterizedTest
  @MethodSource("emptyTopicOrTextProvider")
  void shouldThrowExceptionWhenTopicOrTextAreEmpty(String source) {
    // given
    var questionAsData = new TargetQuestionData(source);

    // when
    var exception = assertThrows(RuntimeException.class, questionAsData::content);

    // then
    var expectedMessage = "Could not create 'Question' from empty values.";
    var actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  static Stream<Arguments> emptyTopicOrTextProvider() {
    return Stream.of(
        arguments(" , <topic is empty>"),
        arguments(" <text is empty> , "),
        arguments("     ,       "));
  }
}
