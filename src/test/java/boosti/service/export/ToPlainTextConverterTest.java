package boosti.service.export;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;

class ToPlainTextConverterTest {

  ToPlainTextConverter converter = new ToPlainTextConverter();

  @Test
  void shouldReturnEmptyResourceForEmptyCollection() {
    // when
    var result = (ByteArrayResource) converter.apply(emptyList());

    // then
    assertArrayEquals(result.getByteArray(), new byte[] {});
  }
}
