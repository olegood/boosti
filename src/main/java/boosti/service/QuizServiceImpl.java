package boosti.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import boosti.domain.Question;
import boosti.domain.quiz.Quiz;
import boosti.domain.quiz.QuizRepository;
import boosti.domain.quiz.Status;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

  private final QuizRepository repository;

  public QuizServiceImpl(QuizRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(Collection<Question> questions) {
    var quiz = new Quiz();
    quiz.setStatus(Status.DRAFT);
    quiz.setQuestions(new HashSet<>(questions));
    repository.save(quiz);
  }

  @Override
  public Optional<Quiz> getById(Long id) {
    return repository.findById(id);
  }
}
