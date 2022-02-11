package boosti.web.api.export.converter;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import boosti.domain.Question;
import org.junit.jupiter.api.Test;

class ByteArrayConverterTest {

  Converter<byte[]> converter = new ByteArrayConverter();

  @Test
  void shouldReturnEmptyResourceForEmptyCollection() {
    // when
    var result = converter.apply(emptyList());

    // then
    assertArrayEquals(new byte[] {}, result);
  }

  @Test
  void shouldReturnValidResourceForECollectionOfQuestions() {
    // given
    var question = new Question();
    question.setText("<text>");

    // when
    var result = converter.apply(List.of(question));

    // then
    assertArrayEquals(new byte[] {60, 116, 101, 120, 116, 62, 10}, result);
  }
}
