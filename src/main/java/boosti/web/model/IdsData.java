package boosti.web.model;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdsData {

  private Collection<Long> ids;
}
