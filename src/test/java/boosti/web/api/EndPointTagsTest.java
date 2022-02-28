package boosti.web.api;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

import boosti.domain.Tag;
import boosti.domain.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EndPointTagsTest {

  @Mock TagRepository tagRepository;

  @InjectMocks EndPointTags endPointTags;

  @Test
  void shouldReturnEmptyDataIfNoTagsFound() {
    // given
    when(tagRepository.findAll()).thenReturn(emptyList());

    // when
    var result = endPointTags.getAllTags();

    // then
    assertThat(result, hasSize(0));
  }

  @Test
  void shouldReturnSingleDataIfOneTagFound() {
    // given
    when(tagRepository.findAll()).thenReturn(singletonList(new Tag()));

    // when
    var result = endPointTags.getAllTags();

    // then
    assertThat(result, hasSize(1));
  }
}
