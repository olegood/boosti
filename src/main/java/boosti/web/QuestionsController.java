package boosti.web;

import java.util.Optional;
import java.util.Set;

import boosti.model.Question;
import boosti.service.QuestionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionsController {

  private final QuestionsService questionsService;

  public QuestionsController(QuestionsService questionsService) {
    this.questionsService = questionsService;
  }

  @PostMapping("/api/questions")
  public ResponseEntity<?> saveQuestion(@RequestBody Question question) {
    var result = questionsService.save(question);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping("/api/questions")
  public Set<Question> getByTopic(@RequestParam(required = false) Optional<String> topic) {
    return topic.map(questionsService::getByTopic).orElseGet(questionsService::getAll);
  }
}
