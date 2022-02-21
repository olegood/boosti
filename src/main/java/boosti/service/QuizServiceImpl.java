package boosti.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import boosti.domain.QuestionRepository;
import boosti.domain.quiz.Quiz;
import boosti.domain.quiz.QuizRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepo;
  private final QuestionRepository questionRepo;

  public QuizServiceImpl(QuizRepository quizRepo, QuestionRepository questionRepo) {
    this.quizRepo = quizRepo;
    this.questionRepo = questionRepo;
  }

  @Override
  public Quiz save(Collection<Long> ids) {
    var quiz = new Quiz();

    var questions = questionRepo.findAllById(ids);
    quiz.setQuestions(new HashSet<>(questions));

    return quizRepo.save(quiz);
  }

  @Override
  public Optional<Quiz> getById(Long id) {
    return quizRepo.findById(id);
  }
}
