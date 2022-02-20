package boosti.domain.quiz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class StatusTest {

  @Test
  void name() {
    // expect
    assertThat(Arrays.stream(Status.values()).toList(), hasSize(2));
  }
}
