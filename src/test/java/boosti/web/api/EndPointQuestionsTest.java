package boosti.web.api;

import static java.util.Collections.emptySet;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import boosti.domain.Question;
import boosti.domain.Tag;
import boosti.service.QuestionService;
import boosti.web.model.QuestionData;
import boosti.web.model.SimpleRefData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class EndPointQuestionsTest {

  @Mock QuestionService questionService;

  @InjectMocks EndPointQuestions endPointQuestions;

  @Test
  void shouldReturnCreatedWhenSaveQuestion() {
    // given
    var question = QuestionData.builder().withText("<text>").build();
    when(questionService.save(any(Question.class))).then(AdditionalAnswers.returnsFirstArg());

    // when
    var result = endPointQuestions.saveQuestion(question);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(result.getBody(), is(question));
  }

  @Test
  void shouldReturnSuccessIfQuestionWasSuccessfullyDeleted() {
    // given
    when(questionService.deleteById(anyLong())).thenReturn(Optional.of(new Question()));

    // when
    var result = endPointQuestions.delete(42L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(notNullValue()));
    assertThat(result.getBody(), isA(QuestionData.class));
  }

  @Test
  void shouldReturnNotFoundIfQuestionWasNotDeleted() {
    // given
    when(questionService.deleteById(anyLong())).thenReturn(empty());

    // when
    var result = endPointQuestions.delete(-2L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
  }

  @Test
  void shouldReturnNoContentWhenDeleteAllQuestions() {
    // when
    var result = endPointQuestions.deleteAll();

    // then
    verify(questionService, times(1)).deleteAll();
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
    assertThat(result.getBody(), is(nullValue()));
  }

  @Test
  void shouldReturnNoContentWhenDeleteByIds() {
    // when
    var result = endPointQuestions.deleteByIds(Set.of(41L, 42L));

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
  }

  @Test
  void shouldCallQuestionsServiceGetAllWhenRequestByEmptyTopic() {
    // when
    endPointQuestions.getAll();

    // then
    verify(questionService, times(1)).getAll();
  }

  @Test
  void shouldReturnDataWhenGetAll() {
    // given
    var id = 42L;
    var text = "What is JVM?";

    var question = new Question();
    question.setId(id);
    question.setText(text);
    when(questionService.getAll()).thenReturn(List.of(question));

    var data = QuestionData.builder().withId(id).withText(text).build();

    // when
    var result = endPointQuestions.getAll();

    // then
    verify(questionService, times(1)).getAll();
    assertThat(result, hasSize(1));
    assertThat(result, containsInAnyOrder(data));
  }

  @Test
  void shouldReturnEmptyTagsIfQuestionNotFound() {
    // given
    when(questionService.getById(anyLong())).thenReturn(empty());

    // when
    var result = endPointQuestions.getQuestionTags(42L);

    // then
    assertThat(result, is(emptySet()));
  }

  @Test
  void shouldReturnEmptyTagsIfQuestionHasNoTags() {
    // given
    when(questionService.getById(anyLong())).thenReturn(Optional.of(new Question()));

    // when
    var result = endPointQuestions.getQuestionTags(42L);

    // then
    assertThat(result, is(emptySet()));
  }

  @Test
  void shouldReturnSimpleRefDataIfQuestionHaveTags() {
    // given
    var question = new Question();
    question.setTags(Set.of(newTagWithName("java"), newTagWithName("kotlin")));

    when(questionService.getById(42L)).thenReturn(Optional.of(question));

    // when
    var result = endPointQuestions.getQuestionTags(42L);

    // then
    assertThat(result, hasSize(2));
    assertThat(result, containsInAnyOrder(newDataWithName("java"), newDataWithName("kotlin")));
  }

  private Tag newTagWithName(String name) {
    var tag = new Tag();
    tag.setName(name);
    return tag;
  }

  private SimpleRefData newDataWithName(String name) {
    return SimpleRefData.builder().withName(name).build();
  }
}
