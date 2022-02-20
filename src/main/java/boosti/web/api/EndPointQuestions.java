package boosti.web.api;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Set;

import boosti.domain.Question;
import boosti.service.QuestionService;
import boosti.web.model.QuestionData;
import org.modelmapper.ModelMapper;
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
  private final ModelMapper modelMapper;

  public EndPointQuestions(QuestionService questionService, ModelMapper modelMapper) {
    this.questionService = questionService;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  public ResponseEntity<QuestionData> saveQuestion(@RequestBody QuestionData data) {
    var result = questionService.save(toEntity(data));
    return ResponseEntity.status(HttpStatus.CREATED).body(toData(result));
  }

  private Question toEntity(QuestionData data) {
    return modelMapper.map(data, Question.class);
  }

  private QuestionData toData(Question question) {
    return modelMapper.map(question, QuestionData.class);
  }

  @GetMapping
  public Collection<QuestionData> getAll() {
    return questionService.getAll().stream().map(this::toData).collect(toList());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<QuestionData> delete(@PathVariable Long id) {
    return questionService
        .deleteById(id)
        .map(value -> ResponseEntity.ok().body(toData(value)))
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
