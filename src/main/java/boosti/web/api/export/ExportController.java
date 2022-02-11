package boosti.web.api.export;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import boosti.domain.Question;
import boosti.domain.QuestionRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController {

  private final QuestionRepository questionRepository;
  private final Function<Collection<Question>, byte[]> converter;

  public ExportController(
      QuestionRepository repository, Function<Collection<Question>, byte[]> converter) {
    this.questionRepository = repository;
    this.converter = converter;
  }

  @PostMapping("/api/questions/export")
  public ResponseEntity<Resource> export(@RequestBody Set<Long> ids) {
    var questions = questionRepository.findAllById(ids);

    var array = converter.apply(questions);
    if (array.length == 0) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(new ByteArrayResource(array));
  }
}
