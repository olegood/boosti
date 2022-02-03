package boosti.web.api.export;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import boosti.model.Question;
import boosti.service.QuestionsService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController {

  private final QuestionsService questionsService;
  private final Function<Collection<Question>, byte[]> converter;

  public ExportController(
      QuestionsService questionsService, Function<Collection<Question>, byte[]> converter) {
    this.questionsService = questionsService;
    this.converter = converter;
  }

  @PostMapping("/api/questions/export")
  public ResponseEntity<Resource> export(@RequestBody Set<Long> ids) {
    var questions = questionsService.getByIds(ids);

    var array = converter.apply(questions);
    if (array.length == 0) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(new ByteArrayResource(array));
  }
}
