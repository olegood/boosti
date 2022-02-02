package boosti.service.parse;

import java.util.Collection;

import boosti.model.Question;

@FunctionalInterface
public interface Parser {

  /**
   * Converts input stream of string into questions.
   *
   * @param values origin stream of string
   * @return stream of parsed {@link Question}
   */
  Collection<Question> parseFrom(Collection<String> values);
}
