package boosti.web.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import boosti.model.Question;
import boosti.service.QuestionsService;
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
  void shouldReturnSuccessIfQuestionWasSuccessfullyDeleted() {
    // given
    when(questionsService.delete(anyLong()))
        .thenReturn(Optional.of(new Question("<topic>", "<text>")));

    // when
    var result = questionsController.delete(42L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(notNullValue()));
    assertThat(result.getBody(), isA(Question.class));
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

  @Test
  void shouldReturnNoContentWhenDeleteByIds() {
    // when
    var result = questionsController.deleteByIds(Set.of(41L, 42L));

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
  }

  @Test
  void shouldCallQuestionsServiceGetByTopicWhenRequestByTopic() {
    // given
    var topic = "Java";

    // when
    var result = questionsController.getByTopic(Optional.of(topic));

    // then
    verify(questionsService, times(1)).getByTopic(topic);
    verify(questionsService, never()).getAll();
  }

  @Test
  void shouldCallQuestionsServiceGetAllWhenRequestByEmptyTopic() {
    // when
    var result = questionsController.getByTopic(Optional.empty());

    // then
    verify(questionsService, times(1)).getAll();
    verify(questionsService, never()).getByTopic(anyString());
  }
}
