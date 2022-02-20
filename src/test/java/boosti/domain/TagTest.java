package boosti.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

class TagTest {

  @Test
  void shouldHaveIdAndName() {
    // when
    var tag = new Tag();
    tag.setId(42L);
    tag.setName("jdk");

    // then
    assertThat(tag.getId(), is(notNullValue()));
    assertThat(tag.getName(), is(notNullValue()));
  }
}
