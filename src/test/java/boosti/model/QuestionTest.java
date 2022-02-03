package boosti.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QuestionTest {

  @ParameterizedTest
  @MethodSource("questionsEqualityProvider")
  void checkTwoQuestionsAreEqual(Question one, Object two, boolean expected) {
    // when
    var result = one.equals(two);

    // then
    assertThat(result, Matchers.is(expected));
  }

  static Stream<Arguments> questionsEqualityProvider() {
    return Stream.of(
        arguments(from(42L, "<topic>", "<text>"), from(58L, "<topic>", "<text>"), true),
        arguments(from(42L, "<topic One>", "<text One>"), from(58L, "<topic>", "<text>"), false),
        arguments(from(42L, "<topic>", "<text 1>"), from(58L, "<topic>", "<text 2>"), false),
        arguments(from(42L, "<topic 1>", "<text>"), from(58L, "<topic 2>", "<text>"), false),
        arguments(from(42L, "<topic One>", "<text One>"), new Object(), false));
  }

  private static Question from(Long id, String topic, String text) {
    return new Question(id, topic, text);
  }

  @Test
  void twoQuestionsShouldHaveTheSameHashIfTheyAreEqual() {
    // given
    var one = new Question(42L, "<topic>", "<text>");
    var two = new Question(58L, "<topic>", "<text>");

    // when
    var hashOne = one.hashCode();
    var hashTwo = two.hashCode();

    // then
    assertEquals(hashOne, hashTwo);
  }

  @Test
  void twoQuestionsShouldNotHaveTheSameHashIfTheyAreNotEqual() {
    // given
    var one = new Question(42L, "<topic>", "<text>");
    var two = new Question(58L, "<topic Two>", "<text Two>");

    // when
    var hashOne = one.hashCode();
    var hashTwo = two.hashCode();

    // then
    assertNotEquals(hashOne, hashTwo);
  }
}
