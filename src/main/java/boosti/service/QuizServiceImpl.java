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

  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;

  public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
    this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
  }

  @Override
  public Quiz save(Collection<Long> ids) {
    var quiz = new Quiz();

    var questions = questionRepository.findAllById(ids);
    quiz.setQuestions(new HashSet<>(questions));

    return quizRepository.save(quiz);
  }

  @Override
  public Optional<Quiz> getById(Long id) {
    return quizRepository.findById(id);
  }
}
