package boosti.web;

import static org.springframework.util.CollectionUtils.isEmpty;

import boosti.model.Question;
import boosti.service.QuestionsService;
import boosti.service.export.ExportService;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class QuestionsController {

  private final QuestionsService questionsService;
  private final ExportService exportService;

  public QuestionsController(QuestionsService questionsService, ExportService exportService) {
    this.questionsService = questionsService;
    this.exportService = exportService;
  }

  @PostMapping("/api/questions")
  public ResponseEntity<?> saveQuestion(@RequestBody Question question) {
    var result = questionsService.save(question);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PostMapping("/api/questions/export")
  public ResponseEntity<Resource> export(@RequestBody Set<Long> ids) {
    var questions = questionsService.getById(ids);
    if (isEmpty(questions)) {
      return ResponseEntity.noContent().build();
    }

    var resource = exportService.export(questions);
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
  }

  @GetMapping("/api/questions")
  public Set<Question> getByTopic(@RequestParam(required = false) Optional<String> topic) {
    return topic.map(questionsService::getByTopic).orElseGet(questionsService::getAll);
  }

  @GetMapping("/api/questions/all")
  public Map<String, Set<Question>> getAll() {
    return questionsService.getQuestionsWithTopics();
  }
}
