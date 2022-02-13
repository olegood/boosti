package boosti.service;

import java.util.Collection;
import java.util.Optional;

import boosti.domain.Question;

public interface QuestionService {

  Question save(Question question);

  Collection<Question> getAll();

  Optional<Question> deleteById(Long id);

  void deleteAllById(Collection<Long> ids);
}
