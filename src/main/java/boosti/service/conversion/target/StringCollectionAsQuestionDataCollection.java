package boosti.service.conversion.target;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuestionData;

public class StringCollectionAsQuestionDataCollection
    extends SourceAsTarget<Collection<String>, Collection<QuestionData>> {

  /** {@inheritDoc} */
  public StringCollectionAsQuestionDataCollection(Collection<String> source) {
    super(source);
  }

  @Override
  public Collection<QuestionData> content() {
    Predicate<String> linesWithCorrectStarting = line -> of("#", "//").noneMatch(line::startsWith);
    return source.stream()
        .filter(Objects::nonNull)
        .filter(not(String::isBlank))
        .filter(linesWithCorrectStarting)
        .map(StringAsQuestionData::new)
        .map(StringAsQuestionData::content)
        .collect(toList());
  }
}
