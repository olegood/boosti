package boosti.domain.security;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;

import java.util.Set;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;

public class UserRolesAttribute implements AttributeConverter<Set<String>, String> {

  @Override
  public String convertToDatabaseColumn(Set<String> roles) {
    if (isEmpty(roles)) {
      return null;
    }
    return roles.stream().map(String::toUpperCase).sorted().collect(joining(","));
  }

  @Override
  public Set<String> convertToEntityAttribute(String column) {
    if (!hasLength(column)) {
      return emptySet();
    }
    return Stream.of(column.split(",")).map(String::toUpperCase).collect(toSet());
  }
}
