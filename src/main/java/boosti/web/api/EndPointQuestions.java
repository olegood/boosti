package boosti.web.api;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Collection;
import java.util.Set;

import boosti.domain.Question;
import boosti.service.QuestionService;
import boosti.web.model.QuestionData;
import boosti.web.model.SimpleRefData;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

  @GetMapping("/{id}")
  public ResponseEntity<QuestionData> getQuestion(@PathVariable Long id) {
    var question = questionService.getById(id);
    return question
        .map(it -> ResponseEntity.ok().body(toDataWithLinks(it)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  private QuestionData toDataWithLinks(Question question) {
    var result = new ModelMapper().map(question, QuestionData.class);

    result.add(
        linkTo(methodOn(EndPointQuestions.class).getQuestion(result.getId())).withSelfRel(),
        linkTo(methodOn(EndPointQuestions.class).delete(result.getId())).withRel("deleteQuestion"));

    return result;
  }

  @Secured("ROLE_AUTHOR")
  @PostMapping
  public ResponseEntity<QuestionData> saveQuestion(@RequestBody QuestionData data) {
    var question = new ModelMapper().map(data, Question.class);

    var result = questionService.save(question);
    var body = new ModelMapper().map(result, QuestionData.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(body);
  }

  @Secured("ROLE_AUTHOR")
  @GetMapping
  public Collection<QuestionData> getAll() {
    return questionService.getAll().stream()
        .map(question -> new ModelMapper().map(question, QuestionData.class))
        .collect(toList());
  }

  @Secured("ROLE_AUTHOR")
  @DeleteMapping("/{id}")
  public ResponseEntity<QuestionData> delete(@PathVariable Long id) {
    return questionService
        .deleteById(id)
        .map(question -> new ModelMapper().map(question, QuestionData.class))
        .map(body -> ResponseEntity.ok().body(body))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Secured("ROLE_ROOT")
  @DeleteMapping("/all")
  public ResponseEntity<Void> deleteAll() {
    questionService.deleteAll();
    return ResponseEntity.noContent().build();
  }

  @Secured("ROLE_AUTHOR")
  @DeleteMapping
  public ResponseEntity<Void> deleteByIds(@RequestBody Set<Long> ids) {
    questionService.deleteAllById(ids);
    return ResponseEntity.noContent().build();
  }

  @Secured("ROLE_AUTHOR")
  @GetMapping("/{id}/tags")
  public Set<SimpleRefData> getQuestionTags(@PathVariable Long id) {
    return questionService.getById(id).map(Question::getTags).orElse(emptySet()).stream()
        .map(it -> new ModelMapper().map(it, SimpleRefData.class))
        .collect(toSet());
  }
}
