package boosti.service.parse;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import boosti.web.model.QuestionData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:upload.properties")
public class CommaSeparatedParser implements ContentParser {

  @Value("${separator}")
  private String separator;

  public void setSeparator(String separator) {
    this.separator = separator;
  }

  @Override
  public Collection<QuestionData> parseFrom(Collection<String> values) {
    return values.stream()
        .filter((Predicate.not(String::isBlank)))
        .filter(it -> !it.startsWith("#"))
        .map(this::parse)
        .collect(Collectors.toList());
  }

  private QuestionData parse(String value) {
    checkInputValue(value);

    var index = value.indexOf(separator);

    var topic = value.substring(0, index).trim();
    var text = value.substring(index + 1).trim();

    ensureQuestionCouldBeCreated(topic, text);

    return QuestionData.builder().withTopic(topic).withText(text).build();
  }

  private void checkInputValue(String input) {
    if (!input.contains(separator)) {
      throw new ParserException(
          "Input value cannot be parsed, separator=" + separator + " input=" + input);
    }
  }

  private void ensureQuestionCouldBeCreated(String topic, String text) {
    if (topic.isBlank() || text.isBlank()) {
      throw new ParserException("Could not create 'Question' from empty values.");
    }
  }
}
