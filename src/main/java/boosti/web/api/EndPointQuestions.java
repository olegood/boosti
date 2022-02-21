package boosti.web.api;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Set;

import boosti.service.QuestionService;
import boosti.service.conversion.target.QuestionAsQuestionData;
import boosti.service.conversion.target.QuestionDataAsQuestion;
import boosti.web.model.QuestionData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class EndPointQuestions {

  private final QuestionService questionService;

  public EndPointQuestions(QuestionService questionService) {
    this.questionService = questionService;
  }

  @PostMapping
  public ResponseEntity<QuestionData> saveQuestion(@RequestBody QuestionData data) {
    var question = new QuestionDataAsQuestion(data).content();

    var result = questionService.save(question);
    var body = new QuestionAsQuestionData(result).content();

    return ResponseEntity.status(HttpStatus.CREATED).body(body);
  }

  @GetMapping
  public Collection<QuestionData> getAll() {
    return questionService.getAll().stream()
        .map(QuestionAsQuestionData::new)
        .map(QuestionAsQuestionData::content)
        .collect(toList());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<QuestionData> delete(@PathVariable Long id) {
    return questionService
        .deleteById(id)
        .map(QuestionAsQuestionData::new)
        .map(QuestionAsQuestionData::content)
        .map(body -> ResponseEntity.ok().body(body))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/all")
  public ResponseEntity<Void> deleteAll() {
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteByIds(@RequestBody Set<Long> ids) {
    questionService.deleteAllById(ids);
    return ResponseEntity.noContent().build();
  }
}
