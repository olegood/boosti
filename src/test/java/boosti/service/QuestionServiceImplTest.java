package boosti.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import boosti.domain.Question;
import boosti.domain.QuestionRepository;
import boosti.web.model.QuestionData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

  @Mock QuestionRepository repository;
  @InjectMocks QuestionServiceImpl questionService;

  @Test
  void shouldReturnAllQuestions() {
    // given
    var questions =
        Stream.of(
                from(41L, "Java", "What is JVM"),
                from(42L, "Java", "What is JRE"),
                from("Java", "What is Garbage Collector"),
                from("Maven", "What is BOM (Bill of materials)"))
            // todo: avoid using mapper here
            .map(data -> new ModelMapper().map(data, Question.class))
            .toList();

    when(repository.findAll()).thenReturn(questions);

    // when
    var result = questionService.getAll();

    // then
    assertThat(result, hasSize(4));
  }

  private static QuestionData from(Long id, String topic, String text) {
    return QuestionData.builder().withTopic(topic).withText(text).withId(id).build();
  }

  private static QuestionData from(String topic, String text) {
    return QuestionData.builder().withTopic(topic).withText(text).build();
  }

  @Test
  void shouldSaveQuestion() {
    // given
    var question = new Question();

    // when
    var result = questionService.save(question);

    // then
    verify(repository, times(1)).save(question);
  }

  @Test
  void shouldReturnQuestionsByTopic() {
    // given
    var topic = "Java";
    when(repository.findByTopic(topic)).thenReturn(singleton(new Question()));

    // when
    var result = questionService.getByTopic(topic);

    // then
    assertThat(result, hasSize(1));
    verify(repository, times(1)).findByTopic(topic);
  }

  @Test
  void shouldReturnEmptyListIfNoQuestionsFoundByRequestedTopic() {
    // given
    var topic = "Kotlin";
    when(repository.findByTopic(topic)).thenReturn(emptyList());

    // when
    var result = questionService.getByTopic(topic);

    // then
    assertThat(result, is(empty()));
    verify(repository, atLeastOnce()).findByTopic(topic);
  }

  @Test
  void shouldReturnQuestionAfterDeletion() {
    // given
    var question = new Question();
    when(repository.findById(anyLong())).thenReturn(Optional.of(question));

    var id = 41L;

    // when
    var result = questionService.deleteById(id);

    // then
    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).delete(question);

    assertTrue(result.isPresent());
    assertThat(result.get(), is(question));
  }

  @Test
  void shouldReturnEmptyOptionalIfNoQuestionsToDelete() {
    // given
    when(repository.findById(anyLong())).thenReturn(Optional.empty());

    var id = -1L;

    // when
    var result = questionService.deleteById(-1L);

    // then
    verify(repository, times(1)).findById(id);
    verify(repository, never()).delete(any(Question.class));

    assertFalse(result.isPresent());
    assertThat(result, is(Optional.empty()));
  }

  @Test
  void shouldNotDeleteQuestionsIfIdsEmpty() {
    // when
    questionService.deleteAllById(emptySet());

    // then
    verify(repository, never()).deleteAllById(anyIterable());
  }

  @Test
  void shouldDeleteQuestionsByIds() {
    // when
    questionService.deleteAllById(Set.of(42L, 43L));

    // then
    verify(repository, times(1)).deleteAllById(anyIterable());
  }
}
