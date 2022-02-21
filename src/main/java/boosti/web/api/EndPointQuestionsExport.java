package boosti.web.api;

import java.util.Set;

import boosti.service.QuestionService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPointQuestionsExport {

  private final QuestionService service;

  public EndPointQuestionsExport(QuestionService service) {
    this.service = service;
  }

  @PostMapping("/api/questions/export")
  public ResponseEntity<Resource> export(@RequestBody Set<Long> ids) {
    var questionsAsByteArray = service.getAllAsByteArray(ids);

    var resource = new ByteArrayResource(questionsAsByteArray);
    if (resource.contentLength() == 0) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
  }
}
