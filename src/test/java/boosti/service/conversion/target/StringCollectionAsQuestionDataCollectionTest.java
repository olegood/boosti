package boosti.service.conversion.target;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import boosti.web.model.QuestionData;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// todo: rename tests
class StringCollectionAsQuestionDataCollectionTest {

  @Test
  void shouldReturnEmptyStreamForEmptyStringValues() {
    // given
    var collectionQuestionData = new StringCollectionAsQuestionDataCollection(emptyList());

    // then
    assertThat(collectionQuestionData.content(), Matchers.empty());
  }

  @Test
  void shouldIgnoreEmptyLines() {
    // given
    var collectionQuestionData =
        new StringCollectionAsQuestionDataCollection(List.of("\n", "   ", "\n\n"));

    // then
    assertThat(collectionQuestionData.content(), Matchers.empty());
  }

  @Test
  void shouldIgnoreLinesStartingWithDash() {
    // when
    var collectionQuestionData =
        new StringCollectionAsQuestionDataCollection(
            List.of("# This is a comment", "Java,What is JVM? "));

    // then
    var result = collectionQuestionData.content();

    assertThat(result, Matchers.hasSize(1));
    assertThat(result, Matchers.hasItem(QuestionData.builder().withText("What is JVM?").build()));
  }

  @Test
  void shouldIgnoreLinesStartingWithSlashes() {
    // when
    var collectionQuestionData =
        new StringCollectionAsQuestionDataCollection(
            List.of("// This is a comment", "Java,What is JVM? "));

    // then
    var result = collectionQuestionData.content();

    assertThat(result, Matchers.hasSize(1));
    assertThat(result, Matchers.hasItem(QuestionData.builder().withText("What is JVM?").build()));
  }

  @Test
  void shouldCorrectlyParseLinesContainingIgnoredSymbols() {
    // when
    var collectionQuestionData =
        new StringCollectionAsQuestionDataCollection(
            List.of(
                "// This is a comment",
                "Java,Is pair of symbols '//' - single line comment in Java? ",
                "CSV,Will CSV parser ignore lines with starting # (dash)?"));

    // then
    var result = collectionQuestionData.content();

    assertThat(result, Matchers.hasSize(2));
    assertThat(
        result,
        Matchers.containsInAnyOrder(
            QuestionData.builder()
                .withText("Is pair of symbols '//' - single line comment in Java?")
                .build(),
            QuestionData.builder()
                .withText("Will CSV parser ignore lines with starting # (dash)?")
                .build()));
  }

  @Test
  void shouldCorrectlyParseMixedLines() {
    // when
    var collectionQuestionData =
        new StringCollectionAsQuestionDataCollection(
            List.of("# This is a comment", "Java,What is JVM? ", "   SQL  , Hey, what is DDL?"));

    // then
    var result = collectionQuestionData.content();

    assertThat(result, Matchers.hasSize(2));
    assertThat(
        result,
        Matchers.containsInAnyOrder(
            QuestionData.builder().withText("What is JVM?").build(),
            QuestionData.builder().withText("Hey, what is DDL?").build()));
  }

  @ParameterizedTest
  @MethodSource("inputCollectionsAndResultProvider")
  void shouldParseCorrectUsingSimpleSeparator(
      Collection<String> source, Collection<QuestionData> expectedResult) {
    // given
    var collectionQuestionData = new StringCollectionAsQuestionDataCollection(source);

    // when
    var result = collectionQuestionData.content();

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
                    .withText(",,Could you please describe Strategy design pattern?")
                    .build())),
        arguments(
            List.of("Java,What is JVM?"),
            List.of(QuestionData.builder().withText("What is JVM?").build())),
        arguments(
            List.of("SQL  ,What is DDL? "),
            List.of(QuestionData.builder().withText("What is DDL?").build())));
  }
}
