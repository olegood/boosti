package boosti.service.conversion.target;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.QuestionData;

public class StringCollectionAsQuestionDataCollection
    extends SourceAsTarget<Collection<String>, Collection<QuestionData>> {

  public StringCollectionAsQuestionDataCollection(Collection<String> source) {
    super(source);
  }

  @Override
  public Collection<QuestionData> content() {
    return source.stream()
        .filter(Objects::nonNull)
        .filter((Predicate.not(String::isBlank)))
        .filter(it -> !it.startsWith("#"))
        .map(StringAsQuestionData::new)
        .map(StringAsQuestionData::content)
        .collect(Collectors.toList());
  }
}
