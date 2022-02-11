package boosti.service.parse;

import java.util.Collection;

import boosti.web.model.QuestionData;

@FunctionalInterface
public interface ContentParser {

  /**
   * Converts input stream of string into questions.
   *
   * @param values origin stream of string
   * @return stream of parsed {@link QuestionData}
   */
  Collection<QuestionData> parseFrom(Collection<String> values);
}
