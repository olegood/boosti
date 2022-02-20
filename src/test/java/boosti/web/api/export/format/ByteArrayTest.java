package boosti.web.api.export.format;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import boosti.domain.Question;
import boosti.web.api.export.target.ByteArray;
import org.junit.jupiter.api.Test;

class ByteArrayTest {

  @Test
  void shouldReturnEmptyByteArrayForEmptyCollectionOfQuestions() {
    // given
    var byteArray = new ByteArray(emptyList());

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

    var byteArray = new ByteArray(List.of(question));

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {60, 116, 101, 120, 116, 62, 10}, result);
  }

  @Test
  void shouldReturnEmptyByteArrayIfQuestionTextIsNull() {
    // given
    var byteArray = new ByteArray(List.of(new Question()));

    // when
    var result = byteArray.content();

    // then
    assertArrayEquals(new byte[] {}, result);
  }
}
