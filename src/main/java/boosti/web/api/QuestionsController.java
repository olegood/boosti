package boosti.web.api;

import java.util.Optional;
import java.util.Set;

import boosti.model.Question;
import boosti.service.QuestionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
    var result = questionsService.save(question);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping("/api/questions")
  public Set<Question> getByTopic(@RequestParam(required = false) Optional<String> topic) {
    return topic.map(questionsService::getByTopic).orElseGet(questionsService::getAll);
  }

  @DeleteMapping("/api/questions/{id}")
  public ResponseEntity<Question> delete(@PathVariable Long id) {
    Optional<Question> question = questionsService.delete(id);
    return question
        .map(value -> ResponseEntity.ok().body(value))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/api/questions")
  public ResponseEntity<Void> deleteAll() {
    return ResponseEntity.noContent().build();
  }
}
