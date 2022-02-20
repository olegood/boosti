package boosti.web.api;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import boosti.domain.Category;
import boosti.domain.CategoryRepository;
import boosti.web.model.SimpleRefData;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class EndPointCategories {

  private final CategoryRepository repository;
  private final ModelMapper modelMapper;

  public EndPointCategories(CategoryRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public Collection<SimpleRefData> getAll() {
    return repository.findAll().stream().map(this::toData).collect(toList());
  }

  private SimpleRefData toData(Category category) {
    return modelMapper.map(category, SimpleRefData.class);
  }
}
