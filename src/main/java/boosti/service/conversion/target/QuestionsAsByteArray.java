package boosti.service.conversion.target;

import static java.nio.ByteBuffer.allocate;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Collection;
import java.util.Objects;

import boosti.domain.Question;
import boosti.service.conversion.SourceAsTarget;

public class QuestionsAsByteArray extends SourceAsTarget<Collection<Question>, byte[]> {

  public QuestionsAsByteArray(Collection<Question> source) {
    super(source);
  }

  @Override
  public byte[] content() {
    return source.stream()
        .map(Question::getText)
        .filter(Objects::nonNull)
        .map(it -> (it + '\n').getBytes(UTF_8))
        .reduce(
            new byte[] {},
            (one, two) -> allocate(one.length + two.length).put(one).put(two).array());
  }
}
