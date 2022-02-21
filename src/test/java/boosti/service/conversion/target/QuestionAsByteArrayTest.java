package boosti.service.conversion.target;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import boosti.domain.Question;
import org.junit.jupiter.api.Test;

class QuestionAsByteArrayTest {

  @Test
  void shouldReturnEmptyByteArrayForQuestionWithEmptyText() {
    // given
    var question = new Question();
    question.setText("");

    var byteArray = new QuestionAsByteArray(new Question());

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {}, result);
  }

  @Test
  void shouldReturnEmptyByteArrayWhenQuestionIsNull() {
    // given
    var byteArray = new QuestionAsByteArray(null);

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {}, result);
  }

  @Test
  void shouldReturnValidByteArrayForNonNullQuestionText() {
    // given
    var question = new Question();
    question.setText("<text>");

    var byteArray = new QuestionAsByteArray(question);

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {60, 116, 101, 120, 116, 62}, result);
  }

  @Test
  void shouldReturnEmptyByteArrayIfQuestionTextIsNull() {
    // given
    var byteArray = new QuestionAsByteArray(new Question());

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {}, result);
  }
}
