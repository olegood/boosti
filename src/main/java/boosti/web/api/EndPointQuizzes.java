package boosti.web.api;

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

  public EndPointQuizzes(QuizService quizService) {
    this.quizService = quizService;
  }

  @PostMapping
  public void save(@RequestBody IdsData data) {
    quizService.save(data.getIds());
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuizData> getById(@PathVariable Long id) {
    return quizService
        .getById(id)
        .map(quiz -> new ModelMapper().map(quiz, QuizData.class))
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
