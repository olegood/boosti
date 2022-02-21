package boosti.service.conversion.target;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import boosti.domain.Question;
import org.junit.jupiter.api.Test;

class QuestionsAsByteArrayTest {

  @Test
  void shouldReturnEmptyByteArrayForEmptyCollectionOfQuestions() {
    // given
    var byteArray = new QuestionsAsByteArray(emptyList());

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

    var byteArray = new QuestionsAsByteArray(List.of(question));

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {60, 116, 101, 120, 116, 62, 10}, result);
  }

  @Test
  void shouldReturnEmptyByteArrayIfQuestionTextIsNull() {
    // given
    var byteArray = new QuestionsAsByteArray(List.of(new Question()));

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {}, result);
  }
}
