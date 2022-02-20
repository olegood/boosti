package boosti.web.api;

import java.util.Collection;

import boosti.domain.Question;
import boosti.domain.QuestionRepository;
import boosti.domain.quiz.Quiz;
import boosti.service.QuizService;
import boosti.web.model.IdsData;
import boosti.web.model.QuizData;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
public class EndPointQuizzes {

  private final QuizService quizService;
  private final QuestionRepository questionRepository;

  private final ModelMapper modelMapper;

  public EndPointQuizzes(
      QuizService quizService, QuestionRepository questionRepository, ModelMapper modelMapper) {
    this.quizService = quizService;
    this.questionRepository = questionRepository;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  public void save(@RequestBody IdsData data) {
    quizService.save(toEntity(data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuizData> getById(@PathVariable Long id) {
    var result = quizService.getById(id);
    return result
        .map(quiz -> ResponseEntity.ok(toData(quiz)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  private QuizData toData(Quiz quiz) {
    return modelMapper.map(quiz, QuizData.class);
  }

  private Collection<Question> toEntity(IdsData data) {
    return questionRepository.findAllById(data.getIds());
  }
}
