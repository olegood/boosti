package boosti.domain;

import java.util.Set;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_question_id")
  @SequenceGenerator(name = "sq_question_id")
  private Long id;

  @OneToOne private Category category;

  private String text;
  private String answer;

  @Convert(converter = TagsAttributeConverter.class)
  private Set<String> tags;
}
