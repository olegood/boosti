package boosti.service.conversion.target;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.ofNullable;

import java.util.function.Function;

import boosti.domain.Question;
import boosti.service.conversion.SourceAsTarget;

public class QuestionAsByteArray extends SourceAsTarget<Question, byte[]> {

  public QuestionAsByteArray(Question source) {
    super(source);
  }

  @Override
  public byte[] content() {
    Function<String, byte[]> toBytes = it -> it.getBytes(UTF_8);
    return ofNullable(source).map(Question::getText).map(toBytes).orElse(new byte[] {});
  }
}
