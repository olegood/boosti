package boosti.web.api.export;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import boosti.model.Question;
import org.junit.jupiter.api.Test;

class ToPlainTextConverterTest {

  ToPlainTextConverter converter = new ToPlainTextConverter();

  @Test
  void shouldReturnEmptyResourceForEmptyCollection() {
    // when
    var result = converter.apply(emptyList());

    // then
    assertArrayEquals(new byte[] {}, result);
  }

  @Test
  void shouldReturnValidResourceForECollectionOfQuestions() {
    // when
    var result = converter.apply(List.of(new Question("", "<text>")));

    // then
    assertArrayEquals(new byte[] {60, 116, 101, 120, 116, 62, 10}, result);
  }
}
