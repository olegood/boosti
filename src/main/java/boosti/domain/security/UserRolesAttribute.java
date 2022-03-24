package boosti.domain.security;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;

import java.util.Set;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;

/**
 * Converts roles from database column to {@link User} field.
 *
 * @author Oleg Anastassov
 * @see javax.persistence.AttributeConverter
 * @deprecated since there is a roles hierarchy and no granular permissions, there is no need in
 *     storing roles as comma-separated values
 */
@Deprecated(forRemoval = true)
public class UserRolesAttribute implements AttributeConverter<Set<String>, String> {

  /**
   * Converts {@link Set} of {@link String} as roles into database column.
   *
   * <p>Database column storage format: string-like values in uppercase separated by comma. Values
   * are sorted alphabetically.
   *
   * <p>Example: "AUTHOR,ROOT"
   *
   * @param roles a {@link Set} of unique roles to convert
   * @return roles string values in uppercase separated by comma
   */
  @Override
  public String convertToDatabaseColumn(Set<String> roles) {
    if (isEmpty(roles)) {
      return null;
    }
    return roles.stream().map(String::toUpperCase).sorted().collect(joining(","));
  }

  /**
   * Converts database column stored value into entity's field.
   *
   * @param column stored value
   * @return {@link Set} of {@link String} values
   */
  @Override
  public Set<String> convertToEntityAttribute(String column) {
    if (!hasLength(column)) {
      return emptySet();
    }
    return Stream.of(column.split(",")).map(String::toUpperCase).collect(toSet());
  }
}
