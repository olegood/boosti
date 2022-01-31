package boosti.web;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

import java.util.Set;

import boosti.model.Question;
import boosti.service.QuestionsService;
import boosti.service.export.ExportService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
  void shouldReturnNoContentIfNoQuestionsToExport() {
    // given
    when(questionsService.getById(anyCollection())).thenReturn(emptyList());

    // when
    var result = questionsController.export(Set.of(42L, 43L));

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.NO_CONTENT));
  }

  @Test
  void shouldReturnOkWhenQuestionsCanBeExported() {
    // given
    when(questionsService.getById(anyCollection()))
        .thenReturn(singletonList(new Question("<topic>", "<text>")));

    // when
    var result = questionsController.export(Set.of(42L, 43L));

    // then
    assertThat(result.getStatusCode(), Matchers.is(HttpStatus.OK));
  }
}
