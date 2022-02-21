package boosti.web.api;

import java.util.Collection;

import boosti.domain.Question;
import boosti.domain.QuestionRepository;
import boosti.service.QuizService;
import boosti.service.conversion.target.QuizAsQuizData;
import boosti.web.model.IdsData;
import boosti.web.model.QuizData;
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

  public EndPointQuizzes(QuizService quizService, QuestionRepository questionRepository) {
    this.quizService = quizService;
    this.questionRepository = questionRepository;
  }

  @PostMapping
  public void save(@RequestBody IdsData data) {
    quizService.save(toEntity(data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuizData> getById(@PathVariable Long id) {
    return quizService
        .getById(id)
        .map(QuizAsQuizData::new)
        .map(QuizAsQuizData::content)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  private Collection<Question> toEntity(IdsData data) {
    return questionRepository.findAllById(data.getIds());
  }
}
