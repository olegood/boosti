package boosti.web.api.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import boosti.model.Question;
import boosti.service.QuestionsService;
import boosti.service.parse.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class FileControllerTest {

  @Mock MultipartFile file;
  @Mock Parser parser;
  @Mock QuestionsService questionsService;

  @Spy @InjectMocks FileController fileController;

  @BeforeEach
  void setUp() throws Exception {
    when(file.getInputStream())
        .thenReturn(new ByteArrayInputStream("this is sample".getBytes(StandardCharsets.UTF_8)));
  }

  @Test
  void shouldReturnInternalErrorForUnsupportedFileExtension() {
    // given
    when(file.getOriginalFilename()).thenReturn("not_valid_extension.err");

    // when
    var result = fileController.uploadFile(file);

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY));
    assertThat(result.getBody(), Matchers.is("Unsupported file type."));
  }

  @Test
  void shouldReturnSuccessForSupportedFileExtension() {
    // given
    when(file.getOriginalFilename()).thenReturn("valid_extension.csv");

    when(parser.parseFrom(anyCollection())).thenReturn(List.of(new Question("<topic>", "<text>")));
    when(questionsService.save(any(Question.class))).then(AdditionalAnswers.returnsFirstArg());

    // when
    var result = fileController.uploadFile(file);

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.OK));
  }

  @Test
  void shouldReturnInternalErrorForAnyParserException() {
    // given
    when(file.getOriginalFilename()).thenReturn("valid_extension.csv");

    when(parser.parseFrom(anyCollection())).thenThrow(RuntimeException.class);

    // when
    var result = fileController.uploadFile(file);

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY));
  }
}
