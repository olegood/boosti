package boosti.web.api;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import boosti.domain.CategoryRepository;
import boosti.web.model.SimpleRefData;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class EndPointCategories {

  private final CategoryRepository categoryRepo;

  public EndPointCategories(CategoryRepository categoryRepo) {
    this.categoryRepo = categoryRepo;
  }

  @GetMapping
  public Collection<SimpleRefData> getAll() {
    return categoryRepo.findAll().stream()
        .map(category -> new ModelMapper().map(category, SimpleRefData.class))
        .collect(toList());
  }
}
