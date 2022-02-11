package boosti.web.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import boosti.domain.Question;
import boosti.service.QuestionService;
import boosti.web.model.QuestionData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

  @Mock QuestionService questionService;
  @Spy ModelMapper modelMapper;

  @InjectMocks QuestionController questionController;

  @Test
  void shouldReturnCreatedWhenSaveQuestion() {
    // given
    var question = QuestionData.builder().withTopic("<topic>").withText("<text>").build();
    when(questionService.save(any(Question.class))).then(AdditionalAnswers.returnsFirstArg());

    // when
    var result = questionController.saveQuestion(question);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(result.getBody(), is(question));
  }

  @Test
  void shouldReturnSuccessIfQuestionWasSuccessfullyDeleted() {
    // given
    when(questionService.deleteById(anyLong())).thenReturn(Optional.of(new Question()));

    // when
    var result = questionController.delete(42L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(notNullValue()));
    assertThat(result.getBody(), isA(QuestionData.class));
  }

  @Test
  void shouldReturnNotFoundIfQuestionWasNotDeleted() {
    // given
    when(questionService.deleteById(anyLong())).thenReturn(Optional.empty());

    // when
    var result = questionController.delete(-2L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
  }

  @Test
  void shouldReturnNoContentWhenDeleteAllQuestions() {
    // when
    var result = questionController.deleteAll();

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
    assertThat(result.getBody(), is(nullValue()));
  }

  @Test
  void shouldReturnNoContentWhenDeleteByIds() {
    // when
    var result = questionController.deleteByIds(Set.of(41L, 42L));

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
  }

  @Test
  void shouldCallQuestionsServiceGetByTopicWhenRequestByTopic() {
    // given
    var topic = "Java";

    // when
    var result = questionController.getByTopic(Optional.of(topic));

    // then
    verify(questionService, times(1)).getByTopic(topic);
    verify(questionService, never()).getAll();
  }

  @Test
  void shouldCallQuestionsServiceGetAllWhenRequestByEmptyTopic() {
    // when
    var result = questionController.getByTopic(Optional.empty());

    // then
    verify(questionService, times(1)).getAll();
    verify(questionService, never()).getByTopic(anyString());
  }
}
