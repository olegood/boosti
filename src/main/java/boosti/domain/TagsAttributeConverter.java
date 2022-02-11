package boosti.domain;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.joining;

import java.util.Set;
import javax.persistence.AttributeConverter;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class TagsAttributeConverter implements AttributeConverter<Set<String>, String> {

  @Override
  public String convertToDatabaseColumn(Set<String> tags) {
    if (CollectionUtils.isEmpty(tags)) {
      return null;
    }
    return tags.stream().sorted().collect(joining(","));
  }

  @Override
  public Set<String> convertToEntityAttribute(String column) {
    if (!StringUtils.hasLength(column)) {
      return emptySet();
    }
    return Set.of(column.split(","));
  }
}
