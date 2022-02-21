package boosti.service.conversion.target;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import boosti.service.conversion.Target;
import boosti.web.model.QuestionData;

public class TargetCollectionQuestionData
    extends Target<Collection<String>, Collection<QuestionData>> {

  public TargetCollectionQuestionData(Collection<String> source) {
    super(source);
  }

  @Override
  public Collection<QuestionData> content() {
    return source.stream()
        .filter(Objects::nonNull)
        .filter((Predicate.not(String::isBlank)))
        .filter(it -> !it.startsWith("#"))
        .map(TargetQuestionData::new)
        .map(TargetQuestionData::content)
        .collect(Collectors.toList());
  }
}
