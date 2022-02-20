package boosti.service;

import java.util.Collection;
import java.util.Optional;

import boosti.domain.Question;
import boosti.domain.quiz.Quiz;

public interface QuizService {

  Quiz save(Collection<Question> questions);

  Optional<Quiz> getById(Long id);
}
