package boosti.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import boosti.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionsServiceTest {

  QuestionsService service = new QuestionsService();

  @BeforeEach
  void setUp() {
    Stream.of(
            new Question(41L, "Java", "What is JVM"),
            new Question(42L, "Java", "What is JRE"),
            new Question("Java", "What is Garbage Collector"),
            new Question("Maven", "What is BOM (Bill of materials)"))
        .forEach(service::save);
  }

  @Test
  void shouldReturnAllQuestions() {
    // when
    var result = service.getAll();

    // then
    assertThat(result, hasSize(4));
  }

  @Test
  void shouldReturnQuestionsByValidIds() {
    // when
    var result = service.getByIds(List.of(41L, 42L));

    // then
    assertThat(result, hasSize(2));
  }

  @Test
  void shouldReturnOneFoundQuestionsByPassedIds() {
    // when
    var result = service.getByIds(List.of(41L, -1L, -2L));

    // then
    assertThat(result, hasSize(1));
  }

  @Test
  void shouldReturnEmptyCollectionIfNoQuestionsFoundByIds() {
    // when
    var result = service.getByIds(List.of(-1L, -2L));

    // then
    assertThat(result, empty());
  }

  @Test
  void shouldReturnQuestionsByTopic() {
    // when
    var result = service.getByTopic("Java");

    // then
    assertThat(result, hasSize(3));
  }

  @Test
  void shouldReturnEmptyListIfNoQuestionsOnTopic() {
    // when
    var result = service.getByTopic("<unknown topic>");

    // then
    assertThat(result, is(empty()));
  }

  @Test
  void shouldReturnQuestionAfterDeletion() {
    // given
    var deletedQuestion = Optional.of(new Question(41L, "Java", "What is JVM"));

    // when
    var result = service.delete(41L);

    // then
    assertThat(result, is(deletedQuestion));
    assertThat(service.getAll(), hasSize(3));
  }

  @Test
  void shouldReturnEmptyOptionalIfNoQuestionsToDelete() {
    // when
    var result = service.delete(-1L);

    // then
    assertThat(result, is(Optional.empty()));
    assertThat(service.getAll(), hasSize(4));
  }

  @Test
  void shouldReturnEmptySetAfterDeleteAll() {
    // when
    var result = service.deleteAll();

    // then
    assertThat(result, empty());
    assertThat(service.getAll(), empty());
  }
}
