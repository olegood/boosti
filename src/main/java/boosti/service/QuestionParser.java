package boosti.service;

import boosti.model.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:upload.properties")
public class QuestionParser {

  @Value("${separator}")
  private String separator;

  public void setSeparator(String separator) {
    this.separator = separator;
  }

  public Question parse(String value) {
    checkInputValue(value);

    var index = value.indexOf(separator);

    var topic = value.substring(0, index).trim();
    var text = value.substring(index + 1).trim();

    ensureQuestionCouldBeCreated(topic, text);

    return new Question(topic, text);
  }

  private void checkInputValue(String input) {
    if (input.isBlank() || !input.contains(separator)) {
      throw new RuntimeException(
              "Input value cannot be parsed, separator=" + separator + " input=" + input);
    }
  }

  private void ensureQuestionCouldBeCreated(String topic, String text) {
    if (topic.isBlank() || text.isBlank()) {
      throw new RuntimeException("Could not create 'Question' from empty values.");
    }
  }
}
