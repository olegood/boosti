package boosti.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import boosti.repo.QuestionRepository;
import boosti.domain.quiz.Quiz;
import boosti.repo.QuizRepository;
import boosti.domain.quiz.Status;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

  @Mock QuizRepository quizRepo;
  @Mock QuestionRepository questionRepo;

  @InjectMocks QuizServiceImpl quizService;

  @Captor ArgumentCaptor<Quiz> quizCaptor;

  @Test
  void shouldSaveQuizInitiallyInDraftStatus() {
    // given
    when(questionRepo.findAllById(anyCollection())).thenReturn(emptyList());

    // when
    quizService.save(emptySet());

    // then
    verify(quizRepo).save(quizCaptor.capture());

    var quiz = quizCaptor.getValue();
    assertThat(quiz.getStatus(), Matchers.is(Status.DRAFT));
  }

  @Test
  void shouldDelegateFindByIdToRepositoryCall() {
    // when
    quizService.getById(42L);

    // then
    verify(quizRepo, times(1)).findById(42L);
  }
}
