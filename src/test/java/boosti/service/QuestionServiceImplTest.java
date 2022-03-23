package boosti.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import boosti.domain.Question;
import boosti.repo.QuestionRepository;
import boosti.web.model.QuestionData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

  @Mock QuestionRepository questionRepo;
  @InjectMocks QuestionServiceImpl questionService;

  /**
   * This test has to be implemented in Groovy.
   *
   * <p>Avoid using 'helper' init methods: {@link #from(String)} and {@link #from(Long, String)}
   *
   * @see QuestionServiceImplSpec
   */
  @Deprecated
  @Disabled("Will be implemented in Groovy. See: QuestionServiceImplSpec.groovy")
  @Test
  void shouldReturnAllQuestions() {
    // given
    var questions =
        Stream.of(
                from(41L, "What is JVM"),
                from(42L, "What is JRE"),
                from("What is Garbage Collector"),
                from("What is BOM (Bill of materials)"))
            // todo: avoid using mapper here
            .map(data -> new ModelMapper().map(data, Question.class))
            .toList();

    when(questionRepo.findAll()).thenReturn(questions);

    // when
    var result = questionService.getAll();

    // then
    assertThat(result, hasSize(4));
  }

  @Deprecated
  private static QuestionData from(Long id, String text) {
    return QuestionData.builder().withText(text).withId(id).build();
  }

  @Deprecated
  private static QuestionData from(String text) {
    return QuestionData.builder().withText(text).build();
  }

  @Test
  void shouldCallRepositoryFindByIdWhenGetById() {
    // when
    questionService.getById(42L);

    // then
    verify(questionRepo, times(1)).findById(42L);
  }

  @Test
  void shouldCallRepositoryFindAllByIdWhenGetAllById() {
    // when
    questionService.getAllById(emptySet());

    // then
    verify(questionRepo, times(1)).findAllById(anyCollection());
  }

  @Test
  void shouldSaveQuestion() {
    // given
    var question = new Question();

    // when
    questionService.save(question);

    // then
    verify(questionRepo, times(1)).save(question);
  }

  @Test
  void shouldCallDeleteAllWhenDeleteAllQuestions() {
    // when
    questionService.deleteAll();

    // then
    verify(questionRepo, times(1)).deleteAll();
  }

  @Test
  void shouldReturnQuestionAfterDeletion() {
    // given
    var question = new Question();
    when(questionRepo.findById(anyLong())).thenReturn(Optional.of(question));

    var id = 41L;

    // when
    var result = questionService.deleteById(id);

    // then
    verify(questionRepo, times(1)).findById(id);
    verify(questionRepo, times(1)).delete(question);

    assertTrue(result.isPresent());
    assertThat(result.get(), is(question));
  }

  @Test
  void shouldReturnEmptyOptionalIfNoQuestionsToDelete() {
    // given
    when(questionRepo.findById(anyLong())).thenReturn(Optional.empty());

    var id = -1L;

    // when
    var result = questionService.deleteById(-1L);

    // then
    verify(questionRepo, times(1)).findById(id);
    verify(questionRepo, never()).delete(any(Question.class));

    assertFalse(result.isPresent());
    assertThat(result, is(Optional.empty()));
  }

  @Test
  void shouldNotDeleteQuestionsIfIdsEmpty() {
    // when
    questionService.deleteAllById(emptySet());

    // then
    verify(questionRepo, never()).deleteAllById(anyIterable());
  }

  @Test
  void shouldDeleteQuestionsByIds() {
    // when
    questionService.deleteAllById(Set.of(42L, 43L));

    // then
    verify(questionRepo, times(1)).deleteAllById(anyIterable());
  }

  @Test
  void shouldReturnEmptyByteArrayForEmptyQuestions() {
    // given
    when(questionService.getAllById(anyCollection())).thenReturn(emptyList());

    // when
    var result = questionService.getAllAsByteArray(emptySet());

    // then
    assertThat(result, is(new byte[] {}));
  }

  @Test
  void shouldReturnByteArrayContent() {
    // given
    var question = new Question();
    question.setText("<text>");

    when(questionService.getAllById(anyCollection())).thenReturn(singletonList(question));

    // when
    var result = questionService.getAllAsByteArray(Set.of(42L));

    // then
    assertThat(result, is(new byte[] {10, 60, 116, 101, 120, 116, 62}));
  }
}
