package boosti.web;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import boosti.model.Question;
import boosti.service.QuestionsService;
import boosti.service.export.ExportService;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class QuestionsControllerTest {

  @Mock QuestionsService questionsService;
  @Mock ExportService exportService;

  @InjectMocks QuestionsController questionsController;

  @Test
  void shouldReturnCreatedWhenSaveQuestion() {
    // given
    var question = new Question("<topic>", "<text>");
    when(questionsService.save(question)).then(AdditionalAnswers.returnsFirstArg());

    // when
    var result = questionsController.saveQuestion(question);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(result.getBody(), is(question));
  }

  @Test
  void shouldReturnNoContentIfNoQuestionsToExport() {
    // given
    when(questionsService.getById(anyCollection())).thenReturn(emptyList());

    // when
    var result = questionsController.export(Set.of(42L, 43L));

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
  }

  @Test
  void shouldReturnOkWhenQuestionsCanBeExported() {
    // given
    when(questionsService.getById(anyCollection()))
        .thenReturn(singletonList(new Question("<topic>", "<text>")));

    // when
    var result = questionsController.export(Set.of(42L, 43L));

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
  }

  @Test
  void shouldReturnSuccessIfQuestionWasSuccessfullyDeleted() {
    // given
    when(questionsService.delete(anyLong()))
        .thenReturn(Optional.of(new Question("<topic>", "<text>")));

    // when
    var result = questionsController.delete(42L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(notNullValue()));
  }

  @Test
  void shouldReturnNotFoundIfQuestionWasNotDeleted() {
    // given
    when(questionsService.delete(anyLong())).thenReturn(Optional.empty());

    // when
    var result = questionsController.delete(-2L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
  }

  @Test
  void shouldReturnNoContentWhenDeleteAllQuestions() {
    // when
    var result = questionsController.deleteAll();

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
    assertThat(result.getBody(), is(nullValue()));
  }
}
