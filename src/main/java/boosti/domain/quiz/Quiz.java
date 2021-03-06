package boosti.domain.quiz;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import boosti.domain.Question;
import lombok.Data;

/**
 * A set of questions that could be exported.
 *
 * @author Oleg Anastassov
 */
@Data
@Entity
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Status status = Status.DRAFT;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "quiz_questions",
      joinColumns = @JoinColumn(name = "quiz_id"),
      inverseJoinColumns = @JoinColumn(name = "question_id"))
  private Set<Question> questions;
}
