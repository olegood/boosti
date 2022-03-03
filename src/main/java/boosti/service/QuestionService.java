package boosti.service;

import java.util.Collection;
import java.util.Optional;

import boosti.domain.Question;

public interface QuestionService {

  Question save(Question question);

  Optional<Question> getById(Long id);

  Collection<Question> getAll();

  Collection<Question> getAllById(Collection<Long> ids);

  void deleteAll();

  Optional<Question> deleteById(Long id);

  void deleteAllById(Collection<Long> ids);

  byte[] getAllAsByteArray(Collection<Long> ids);
}
