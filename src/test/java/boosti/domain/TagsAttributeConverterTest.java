package boosti.domain;

import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TagsAttributeConverterTest {

  TagsAttributeConverter converter = new TagsAttributeConverter();

  @ParameterizedTest(name = "Tags {0} are converted into {1} column")
  @MethodSource("argumentsProvider")
  void shouldConvertToDatabaseColumn(Set<String> tags, String column) {
    // when
    var result = converter.convertToDatabaseColumn(tags);

    // then
    assertThat(result, is(column));
  }

  @ParameterizedTest(name = "Column {1} is converted into {0} tags")
  @MethodSource("argumentsProvider")
  void shouldConvertToEntityAttribute(Set<String> tags, String column) {
    // when
    var result = converter.convertToEntityAttribute(column);

    // then
    assertThat(result, is(tags));
  }

  /**
   * Pairs of tags collection and column.
   *
   * @return arguments for tests
   */
  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments(emptySet(), null),
        arguments(Set.of("java", "js"), "java,js"),
        arguments(Set.of("foo", "buzz", "bar"), "bar,buzz,foo"));
  }
}
