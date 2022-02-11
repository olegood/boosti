package boosti.web.api.export.converter;

import static java.nio.ByteBuffer.allocate;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Collection;

import boosti.domain.Question;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ByteArrayConverter implements Converter<byte[]> {

  @Override
  public byte[] apply(Collection<Question> questionData) {
    return questionData.stream()
        .map(Question::getText)
        .map(it -> (it + '\n').getBytes(UTF_8))
        .reduce(
            new byte[] {},
            (one, two) -> allocate(one.length + two.length).put(one).put(two).array());
  }
}