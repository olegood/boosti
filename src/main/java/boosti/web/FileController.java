package boosti.web;

import boosti.service.QuestionParser;
import boosti.service.QuestionsService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Predicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FileController {

  private final QuestionsService questionsService;
  private final QuestionParser parser;

  public FileController(QuestionsService questionsService, QuestionParser parser) {
    this.questionsService = questionsService;
    this.parser = parser;
  }

  @PostMapping("/api/file/upload")
  public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) {
    try (var inputStream = file.getInputStream();
        var br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      checkSupportingFileType(file);
      parseContent(br);
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
    return ResponseEntity.ok().build();
  }

  private void parseContent(BufferedReader br) {
    br.lines()
        .filter((Predicate.not(String::isBlank)))
        .map(parser::parse)
        .forEach(questionsService::save);
  }

  private void checkSupportingFileType(MultipartFile file) {
    var hasValidExtension =
        Optional.ofNullable(file)
            .map(MultipartFile::getOriginalFilename)
            .map(String::toLowerCase)
            .filter(it -> it.endsWith("csv"))
            .isPresent();
    if (!hasValidExtension) {
      throw new RuntimeException("Unsupported file type.");
    }
  }
}
