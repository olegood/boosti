package boosti.domain;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

  Collection<Question> findByTopic(String topic);
}
