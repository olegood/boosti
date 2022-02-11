package boosti.web.api.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import boosti.domain.Question;
import boosti.service.QuestionService;
import boosti.service.parse.ContentParser;
import boosti.web.model.QuestionData;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

  private final QuestionService questionService;
  private final ContentParser parser;
  private final ModelMapper mapper;

  public FileController(QuestionService questionService, ContentParser parser, ModelMapper mapper) {
    this.questionService = questionService;
    this.parser = parser;
    this.mapper = mapper;
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

  private Question toEntity(QuestionData data) {
    return mapper.map(data, Question.class);
  }

  private void parseContent(BufferedReader br) {
    parser.parseFrom(br.lines().toList()).stream()
        .map(this::toEntity)
        .forEach(questionService::save);
  }

  private void checkSupportingFileType(MultipartFile file) {
    var hasValidExtension =
        Optional.ofNullable(file)
            .map(MultipartFile::getOriginalFilename)
            .map(String::toLowerCase)
            .filter(it -> it.endsWith("csv"))
            .isPresent();
    if (!hasValidExtension) {
      throw new UnsupportedFileTypeException("Unsupported file type.");
    }
  }
}
