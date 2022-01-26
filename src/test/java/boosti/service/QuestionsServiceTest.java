package boosti.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import boosti.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionsServiceTest {

  QuestionsService service = new QuestionsService();

  @BeforeEach
  void setUp() {
    service.save(new Question("Java", "What is JVM"));
    service.save(new Question("Java", "What is JRE"));
    service.save(new Question("Java", "What is Garbage Collector"));

    service.save(new Question("Maven", "What is BOM (Bill of materials)"));
  }

  @Test
  void shouldReturnAllQuestions() {
    // when
    var result = service.getAll();

    // then
    assertThat(result, hasSize(4));
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
}
