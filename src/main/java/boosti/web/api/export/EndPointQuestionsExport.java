package boosti.web.api.export;

import java.util.Set;

import boosti.domain.QuestionRepository;
import boosti.web.api.export.target.ByteArray;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPointQuestionsExport {

  private final QuestionRepository questionRepository;

  public EndPointQuestionsExport(QuestionRepository repository) {
    this.questionRepository = repository;
  }

  @PostMapping("/api/questions/export")
  public ResponseEntity<Resource> export(@RequestBody Set<Long> ids) {
    var questions = questionRepository.findAllById(ids);

    var questionsAsByteArray = new ByteArray(questions);
    var array = questionsAsByteArray.content();

    if (array.length == 0) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(new ByteArrayResource(array));
  }
}
