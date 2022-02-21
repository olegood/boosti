package boosti.web.api;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import boosti.domain.CategoryRepository;
import boosti.service.conversion.target.CategoryAsSimpleRefData;
import boosti.web.model.SimpleRefData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class EndPointCategories {

  private final CategoryRepository repository;

  public EndPointCategories(CategoryRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public Collection<SimpleRefData> getAll() {
    return repository.findAll().stream()
        .map(CategoryAsSimpleRefData::new)
        .map(CategoryAsSimpleRefData::content)
        .collect(toList());
  }
}
