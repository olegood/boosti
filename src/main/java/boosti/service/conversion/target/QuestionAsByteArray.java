package boosti.service.conversion.target;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Optional;

import boosti.domain.Question;
import boosti.service.conversion.SourceAsTarget;

public class QuestionAsByteArray extends SourceAsTarget<Question, byte[]> {

  public QuestionAsByteArray(Question source) {
    super(source);
  }

  @Override
  public byte[] content() {
    return Optional.ofNullable(source)
        .map(Question::getText)
        .map(it -> it.getBytes(UTF_8))
        .orElse(new byte[] {});
  }
}
