package boosti.domain.security;

import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserRolesAttributeTest {

  UserRolesAttribute converter = new UserRolesAttribute();

  @ParameterizedTest(name = "Roles {0} are converted into {1} column value")
  @MethodSource("argumentsProvider")
  void shouldConvertToDatabaseColumn(Set<String> roles, String column) {
    // when
    var result = converter.convertToDatabaseColumn(roles);

    // then
    assertThat(result, is(column));
  }

  @ParameterizedTest(name = "Column value {1} is converted into {0} roles")
  @MethodSource("argumentsProvider")
  void shouldConvertToEntityAttribute(Set<String> roles, String column) {
    // when
    var result = converter.convertToEntityAttribute(column);

    // then
    assertThat(result, is(roles));
  }

  /**
   * Pairs of roles collection and column.
   *
   * @return arguments for tests
   */
  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments(emptySet(), null),
        arguments(Set.of("ADMIN", "USER"), "ADMIN,USER"),
        arguments(Set.of("ROOT", "CONTENT_MANAGER", "AUDITOR"), "AUDITOR,CONTENT_MANAGER,ROOT"));
  }

  @Test
  void shouldConvertToUpperCaseFromDatabaseColumn() {
    // when
    var result = converter.convertToEntityAttribute("aDmIN,USeR");

    // then
    assertThat(result, is(Set.of("ADMIN", "USER")));
  }

  @Test
  void shouldConvertToUpperCaseFromEntityAttribute() {
    // when
    var result = converter.convertToDatabaseColumn(Set.of("aDmIN", "USeR"));

    // then
    assertThat(result, is("ADMIN,USER"));
  }
}
