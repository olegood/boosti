package boosti.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import boosti.model.Question;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QuestionParserTest {

  QuestionParser parser = new QuestionParser();

  @BeforeEach
  void setUp() {
    parser.setSeparator(",");
  }

  @ParameterizedTest
  @MethodSource("inputValueAndExpectedResultProvider")
  void shouldParseCorrectUsingSimpleSeparator(String input, Question expectedResult) {
    // when
    var result = parser.parse(input);

    // then
    assertThat(result, Matchers.is(expectedResult));
  }

  static Stream<Arguments> inputValueAndExpectedResultProvider() {
    return Stream.of(
        arguments(
            "Design Patterns,,,Could you please describe Strategy design pattern?",
            new Question(
                "Design Patterns", ",,Could you please describe Strategy design pattern?")),
        arguments("Java,What is JVM?", new Question("Java", "What is JVM?")),
        arguments("SQL  ,What is DDL? ", new Question("SQL", "What is DDL?")));
  }

  @Test
  void shouldThrowExceptionIfInputValueHasNoSeparatorInIt() {
    // when
    var exception =
        assertThrows(
            RuntimeException.class, () -> parser.parse("Value that does not contain separator"));

    // then
    var expectedMessage =
        "Input value cannot be parsed, separator=, input=Value that does not contain separator";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  @Test
  void shouldThrowExceptionIfInputValueIsEmpty() {
    // when
    var exception = assertThrows(RuntimeException.class, () -> parser.parse(""));

    // then
    var expectedMessage = "Input value cannot be parsed, separator=, input=";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  @ParameterizedTest
  @MethodSource("emptyTopicOrTextProvider")
  void shouldThrowExceptionWhenTopicOrTextAreEmpty(String input) {
    // when
    var exception = assertThrows(RuntimeException.class, () -> parser.parse(input));

    // then
    var expectedMessage = "Could not create 'Question' from empty values.";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  static Stream<Arguments> emptyTopicOrTextProvider() {
    return Stream.of(
        arguments(" , <topic is empty>"), arguments(" <text is empty> , "), arguments("     , "));
  }
}
