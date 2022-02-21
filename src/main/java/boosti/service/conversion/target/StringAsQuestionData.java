package boosti.service.conversion.target;

import java.util.Arrays;

import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuestionData;

public class StringAsQuestionData extends SourceAsTarget<String, QuestionData> {

  private final String separator;

  public StringAsQuestionData(String source) {
    this(source, ",");
  }

  public StringAsQuestionData(String source, String separator) {
    super(source);
    this.separator = separator;
  }

  @Override
  public QuestionData content() {
    checkContainsSeparator(source);

    var separatorIndex = source.indexOf(separator);

    var topic = source.substring(0, separatorIndex).trim();
    var text = source.substring(separatorIndex + 1).trim();

    checkExtractedValuesNotEmpty(topic, text);

    return QuestionData.builder().withText(text).build();
  }

  private void checkContainsSeparator(String value) {
    if (!value.contains(separator)) {
      throw new IllegalArgumentException(
          "Input value cannot be parsed, separator=" + separator + " source=" + value);
    }
  }

  private void checkExtractedValuesNotEmpty(String... values) {
    Arrays.stream(values)
        .filter(String::isBlank)
        .forEach(
            value -> {
              throw new IllegalArgumentException("Could not create 'Question' from empty values.");
            });
  }
}
