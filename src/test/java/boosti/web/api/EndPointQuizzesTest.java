package boosti.web.api;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import boosti.domain.quiz.Quiz;
import boosti.service.QuizService;
import boosti.web.model.IdsData;
import boosti.web.model.QuizData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class EndPointQuizzesTest {

  @Mock QuizService quizService;

  @InjectMocks EndPointQuizzes endPointQuizzes;

  @Test
  void shouldReturnNotFoundIfNoQuizzesFound() {
    // given
    when(quizService.getById(anyLong())).thenReturn(Optional.empty());

    // when
    var result = endPointQuizzes.getById(42L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
    assertThat(result.getBody(), is(nullValue()));
  }

  @Test
  void shouldReturnSuccessIfQuizFound() {
    // given
    when(quizService.getById(anyLong())).thenReturn(Optional.of(new Quiz()));

    // when
    var result = endPointQuizzes.getById(42L);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(notNullValue()));
    assertThat(result.getBody(), isA(QuizData.class));
  }

  @Test
  void shouldCallQuizServiceWhenSaveQuiz() {
    var data = IdsData.builder().withIds(emptyList()).build();

    // when
    endPointQuizzes.save(data);

    // then
    verify(quizService, times(1)).save(emptyList());
  }
}
