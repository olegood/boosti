package boosti.web.api.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import boosti.domain.Question;
import boosti.service.QuestionService;
import boosti.web.api.EndPointFile;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
class EndPointFileTest {

  @Mock QuestionService questionService;

  @Spy @InjectMocks EndPointFile endPointFile;

  @Test
  void shouldReturnInternalErrorForUnsupportedFileExtension() {
    // given
    var file = fakeFile("not_valid_extension.err");

    // when
    var result = endPointFile.uploadFile(file);

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY));
    assertThat(result.getBody(), Matchers.is("Unsupported file type."));
  }

  @Test
  void shouldReturnInternalErrorForUnsupportedFileNameExtension() {
    // given
    var file = fakeFile("not_valid_filename_ends_with_csv");

    // when
    var result = endPointFile.uploadFile(file);

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY));
    assertThat(result.getBody(), Matchers.is("Unsupported file type."));
  }

  @Test
  void shouldReturnSuccessForSupportedFileExtension() {
    // given
    var file =
        fakeFile(
            "valid_extension.csv",
            // "<topic>,<text>"
            new byte[] {60, 116, 111, 112, 105, 99, 62, 44, 60, 116, 101, 120, 116, 62, 10});

    when(questionService.save(any(Question.class))).then(AdditionalAnswers.returnsFirstArg());

    // when
    var result = endPointFile.uploadFile(file);

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.OK));
  }

  @Test
  void shouldReturnInternalErrorForAnyParserException() {
    // given
    var fileWithUnacceptedContent = fakeFile("valid_extension.csv");

    // when
    var result = endPointFile.uploadFile(fileWithUnacceptedContent);

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY));
  }

  private MockMultipartFile fakeFile(String originalFileName) {
    return fakeFile(originalFileName, new byte[] {-1});
  }

  private MockMultipartFile fakeFile(String originalFileName, byte[] content) {
    return new MockMultipartFile("valid_name", originalFileName, "<content type>", content);
  }
}
