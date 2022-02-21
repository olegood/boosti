package boosti.service;

import java.util.Collection;
import java.util.Optional;

import boosti.domain.quiz.Quiz;

public interface QuizService {

  Quiz save(Collection<Long> ids);

  Optional<Quiz> getById(Long id);
}
