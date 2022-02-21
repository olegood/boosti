package boosti.service.conversion.target;

import boosti.domain.Category;
import boosti.service.conversion.SourceAsTarget;
import boosti.web.model.SimpleRefData;
import org.modelmapper.ModelMapper;

public class CategoryAsSimpleRefData extends SourceAsTarget<Category, SimpleRefData> {

  public CategoryAsSimpleRefData(Category source) {
    super(source);
  }

  @Override
  public SimpleRefData content() {
    return new ModelMapper().map(source, SimpleRefData.class);
  }
}
