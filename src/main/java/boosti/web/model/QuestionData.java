package boosti.web.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Data Transfer Object. */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionData {

  private Long id;

  private SimpleRefData category;

  private String text;
  private String answer;

  private Collection<SimpleRefData> tags;
}
