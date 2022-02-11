package boosti.service.parse;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import boosti.web.model.QuestionData;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CommaSeparatedParserTest {

  CommaSeparatedParser parser = new CommaSeparatedParser();

  @BeforeEach
  void setUp() {
    parser.setSeparator(",");
  }

  @Test
  void shouldReturnEmptyStreamForEmptyStringValues() {
    // expect
    assertThat(parser.parseFrom(emptyList()), Matchers.empty());
  }

  @Test
  void shouldReturnSingletonListForOneValidQuestion() {
    // given
    var values = List.of("Java,What is JVM?");
    var expected =
        singletonList(QuestionData.builder().withTopic("Java").withText("What is JVM?").build());

    // when
    var result = parser.parseFrom(values);

    // then
    assertThat(result, Matchers.is(expected));
  }

  @Test
  void shouldIgnoreEmptyLines() {
    // when
    var values = List.of("\n   \n");

    // then
    var result = parser.parseFrom(values);

    assertThat(result, Matchers.empty());
  }

  @Test
  void shouldIgnoreLinesStartWithCommentSymbol() {
    // when
    var values = List.of("# This is a comment", "Java,What is JVM? ");

    // then
    var result = parser.parseFrom(values);

    assertThat(result, Matchers.hasSize(1));
    assertThat(
        result,
        Matchers.hasItem(
            QuestionData.builder().withTopic("Java").withText("What is JVM?").build()));
  }

  @Test
  void shouldCorrectlyParseMixedLines() {
    // when
    var values =
        List.of("# This is a comment", "Java,What is JVM? ", "   SQL  , Hey, what is DDL?");

    // then
    var result = parser.parseFrom(values);

    assertThat(result, Matchers.hasSize(2));
    assertThat(
        result,
        Matchers.containsInAnyOrder(
            QuestionData.builder().withTopic("Java").withText("What is JVM?").build(),
            QuestionData.builder().withTopic("SQL").withText("Hey, what is DDL?").build()));
  }

  @ParameterizedTest
  @MethodSource("inputCollectionsAndResultProvider")
  void shouldParseCorrectUsingSimpleSeparator(
      Collection<String> values, Collection<QuestionData> expectedResult) {
    // when
    var result = parser.parseFrom(values);

    // then
    assertThat(result, Matchers.hasSize(expectedResult.size()));
    assertThat(result, Matchers.is(expectedResult));
  }

  static Stream<Arguments> inputCollectionsAndResultProvider() {
    return Stream.of(
        arguments(
            List.of("Design Patterns,,,Could you please describe Strategy design pattern?"),
            List.of(
                QuestionData.builder()
                    .withTopic("Design Patterns")
                    .withText(",,Could you please describe Strategy design pattern?")
                    .build())),
        arguments(
            List.of("Java,What is JVM?"),
            List.of(QuestionData.builder().withTopic("Java").withText("What is JVM?").build())),
        arguments(
            List.of("SQL  ,What is DDL? "),
            List.of(
                QuestionData.builder().withTopic("SQL").withText("What is DDL?").build())));
  }

  @Test
  void shouldThrowExceptionIfInputValueHasNoSeparatorInIt() {
    // given
    var values = List.of("Value that does not contain separator");

    // when
    var exception = assertThrows(RuntimeException.class, () -> parser.parseFrom(values));

    // then
    var expectedMessage =
        "Input value cannot be parsed, separator=, input=Value that does not contain separator";
    var actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  @ParameterizedTest
  @MethodSource("emptyTopicOrTextProvider")
  void shouldThrowExceptionWhenTopicOrTextAreEmpty(Collection<String> values) {
    // when
    var exception = assertThrows(RuntimeException.class, () -> parser.parseFrom(values));

    // then
    var expectedMessage = "Could not create 'Question' from empty values.";
    var actualMessage = exception.getMessage();

    assertThat(actualMessage, Matchers.is(expectedMessage));
  }

  static Stream<Arguments> emptyTopicOrTextProvider() {
    return Stream.of(
        arguments(List.of(" , <topic is empty>")),
        arguments(List.of(" <text is empty> , ")),
        arguments(List.of("     , ")));
  }
}
