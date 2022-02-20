package boosti.web.api.export;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.isA;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import boosti.domain.Question;
import boosti.domain.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class EndPointQuestionsExportTest {

  @Mock QuestionRepository questionRepository;

  @InjectMocks EndPointQuestionsExport endPointQuestionsExport;

  @Test
  void shouldReturnNoContentIfNoQuestionsToExport() {
    // given
    when(questionRepository.findAllById(anyCollection())).thenReturn(emptyList());

    // when
    var result = endPointQuestionsExport.export(emptySet());

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NO_CONTENT));
  }

  @Test
  void shouldReturnOkWhenQuestionsCanBeExported() {
    // given
    var question = new Question();
    question.setText("<text>");

    when(questionRepository.findAllById(anyCollection())).thenReturn(List.of(question));

    // when
    var result = endPointQuestionsExport.export(Set.of(42L, 43L));

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), isA(Resource.class));
    assertThat(result.getBody(), is(notNullValue()));
  }
}
