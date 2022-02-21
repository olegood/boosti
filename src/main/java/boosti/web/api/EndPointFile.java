package boosti.web.api;

import static java.util.function.Predicate.not;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Predicate;

import boosti.service.QuestionService;
import boosti.service.conversion.target.QuestionDataAsQuestion;
import boosti.service.conversion.target.StringCollectionAsQuestionDataCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class EndPointFile {

  private final QuestionService questionService;

  public EndPointFile(QuestionService questionService) {
    this.questionService = questionService;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) {
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
    var linesAsData = new StringCollectionAsQuestionDataCollection(br.lines().toList());
    linesAsData.content().stream()
        .map(QuestionDataAsQuestion::new)
        .map(QuestionDataAsQuestion::content)
        .forEach(questionService::save);
  }

  private void checkSupportingFileType(MultipartFile file) {
    Predicate<String> endsWithCsv = value -> value.endsWith(".csv");
    Optional.ofNullable(file)
        .map(MultipartFile::getOriginalFilename)
        .map(String::toLowerCase)
        .filter(not(endsWithCsv))
        .ifPresent(
            unsupportedFile -> {
              throw new IllegalArgumentException("Unsupported file type.");
            });
  }
}
