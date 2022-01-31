package boosti.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class QuestionTest {

  @Test
  void twoQuestionsShouldBeEqualIfTopicAndTextAreTheSame() {
    // given
    var one = new Question(42L, "<topic>", "<text>");
    var two = new Question(58L, "<topic>", "<text>");

    // when
    var result = one.equals(two);

    // then
    assertThat(result, Matchers.is(true));
  }

  @Test
  void twoQuestionsShouldNoBeEqualIfTopicAndTextDiffer() {
    // given
    var one = new Question(42L, "<topic One>", "<text One>");
    var two = new Question(58L, "<topic>", "<text>");

    // when
    var result = one.equals(two);

    // then
    assertFalse(result);
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
  void twoQuestionsShouldNotHaveTheSameHashIfTheyAreEqual() {
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
