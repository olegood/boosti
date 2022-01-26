package boosti.web;

import java.util.List;
import java.util.Optional;

import boosti.model.Question;
import boosti.service.QuestionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionsController {

  private final QuestionsService questionsService;

  public QuestionsController(QuestionsService questionsService) {
    this.questionsService = questionsService;
  }

  @GetMapping("/api/questions")
  public List<Question> getByTopic(@RequestParam(required = false) Optional<String> topic) {
    return topic.map(questionsService::getByTopic).orElseGet(questionsService::getAll);
  }
}
