package boosti.web.model;

import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object.
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionData {

  private Long id;

  private String topic;
  private String text;
  private String answer;

  private Set<String> tags;

  @Override
  public boolean equals(Object other) {
    if (other instanceof QuestionData questionData) {
      return Objects.equals(this.topic, questionData.topic) && Objects.equals(this.text, questionData.text);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(topic, text);
  }
}
