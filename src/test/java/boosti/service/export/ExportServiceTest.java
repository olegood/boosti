package boosti.service.export;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import boosti.model.Question;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;

@ExtendWith(MockitoExtension.class)
class ExportServiceTest {

  @Mock ToPlainTextConverter converter;

  @InjectMocks ExportService exportService;

  @Test
  void shouldConvertToResourceWhenExport() {
    // given
    var questions = List.of(new Question("<topic>", "<text>"));
    when(converter.apply(anyCollection())).thenReturn(new ByteArrayResource(new byte[] {}));

    // when
    var result = exportService.export(questions);

    // then
    assertThat(result, Matchers.is(Matchers.notNullValue()));
    verify(converter, atMostOnce()).apply(anyCollection());
  }
}
