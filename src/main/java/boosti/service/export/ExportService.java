package boosti.service.export;

import boosti.model.Question;
import java.util.Collection;
import java.util.function.Function;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ExportService {

  private final Function<Collection<Question>, Resource> converter;

  public ExportService(Function<Collection<Question>, Resource> converter) {
    this.converter = converter;
  }

  public Resource export(Collection<Question> questions) {
    return converter.apply(questions);
  }
}
